package com.jazzinjars.graphql.sample;

import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.HandshakeRequest;
import java.util.Optional;

public class LibraryContextBuilder implements GraphQLContextBuilder {

    @Override
    public GraphQLContext build(HttpServletRequest request) {
	String user = request.getHeader("User");
	return new AuthContext(user, Optional.of(request), null);
    }

    @Override
    public GraphQLContext build(HandshakeRequest handshakeRequest) {
	return null;
    }

    @Override
    public GraphQLContext build() {
	return null;
    }
}
