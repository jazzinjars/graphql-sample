package com.jazzinjars.graphql.sample;

import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibraryContextBuilder implements GraphQLContextBuilder {

    @Override
    public GraphQLContext build(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
	String user = request.get().getHeader("User");
	return new AuthContext(user, request, response);
    }

}
