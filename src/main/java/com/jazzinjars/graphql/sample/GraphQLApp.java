package com.jazzinjars.graphql.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphQLApp {

    private static final Logger logger = LogManager.getLogger(GraphQLApp.class);

    public static void main(String[] args) {
	logger.info("==== RUNNING GRAPHQL SAMPLE APP ====");
	//https://picodotdev.github.io/blog-bitix/2017/11/ejemplo-de-graphql-para-una-interfaz-de-un-servicio-con-spring-boot-y-java/
	SpringApplication.run(GraphQLApp.class, args);
    }

}
