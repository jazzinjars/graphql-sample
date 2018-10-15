package com.jazzinjars.graphql.sample.exception;

import com.jazzinjars.graphql.sample.GraphQLApp;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryErrorHandler implements GraphQLErrorHandler {

    private static final Logger logger = LogManager.getLogger(LibraryErrorHandler.class);

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
	List<GraphQLError> clientErrors = errors.stream()
		.filter(this::isClientError)
		.collect(Collectors.toList());

	List<GraphQLError> serverErrors = errors.stream()
		.filter(e -> !isClientError(e))
		.map(GraphQLErrorAdapter::new)
		.collect(Collectors.toList());

	serverErrors.forEach(error -> {
	    logger.error("Error executing query ({}): {}", error.getClass().getSimpleName(), error.getMessage());
	});

	List<GraphQLError> e = new ArrayList<>();
	e.addAll(clientErrors);
	e.addAll(serverErrors);
	return e;
    }

    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
	return errors.stream()
		.filter(this::isClientError)
		.collect(Collectors.toList());
    }

    protected boolean isClientError(GraphQLError error) {
	return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
    }
}
