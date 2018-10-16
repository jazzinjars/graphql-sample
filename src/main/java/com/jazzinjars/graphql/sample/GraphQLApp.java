package com.jazzinjars.graphql.sample;

import com.coxautodev.graphql.tools.SchemaParser;
import com.jazzinjars.graphql.sample.exception.LibraryErrorHandler;
import com.jazzinjars.graphql.sample.model.Magazine;
import com.jazzinjars.graphql.sample.repository.LibraryRepository;
import com.jazzinjars.graphql.sample.resolver.BookResolver;
import com.jazzinjars.graphql.sample.resolver.LibraryQueryResolver;
import com.jazzinjars.graphql.sample.resolver.LocalDateCoercing;
import com.jazzinjars.graphql.sample.resolver.MagazineResolver;
import com.jazzinjars.graphql.sample.resolver.Mutation;
import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.SimpleGraphQLServlet;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;

@SpringBootApplication
@ServletComponentScan
public class GraphQLApp {

    private static final Logger logger = LogManager.getLogger(GraphQLApp.class);

    @Bean
    public LibraryRepository buildLibraryRepository() {
        return new LibraryRepository();
    }

    @Bean
    public ServletRegistrationBean graphQLServletRegistrationBean(LibraryRepository libraryRepository) throws Exception {
	GraphQLSchema schema = SchemaParser.newParser()
		.schemaString(IOUtils.resourceToString("/schema.graphqls", Charset.forName("UTF-8")))
		.resolvers(new LibraryQueryResolver(libraryRepository), new Mutation(libraryRepository), new BookResolver(libraryRepository), new MagazineResolver(libraryRepository))
		.scalars(new GraphQLScalarType("LocalDate", "LocalDate scalar", new LocalDateCoercing()))
		.dictionary(Magazine.class)
		.build()
		.makeExecutableSchema();

	LibraryErrorHandler errorHandler = new LibraryErrorHandler();
	GraphQLContextBuilder contextBuilder = new LibraryContextBuilder();

	return new ServletRegistrationBean(SimpleGraphQLServlet.builder(schema)
		.withExecutionStrategyProvider(new DefaultExecutionStrategyProvider(new AsyncExecutionStrategy()))
		.withGraphQLErrorHandler(errorHandler)
		.withGraphQLContextBuilder(contextBuilder).build(), "/schema");
    }

    public static void main(String[] args) {
	logger.info("==== RUNNING GRAPHQL SAMPLE APP ====");
	SpringApplication.run(GraphQLApp.class, args);
    }

}
