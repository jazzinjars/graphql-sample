package com.jazzinjars.graphql.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jazzinjars.graphql.sample.AuthContext;
import com.jazzinjars.graphql.sample.model.Book;
import com.jazzinjars.graphql.sample.repository.LibraryRepository;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLMutationResolver {

    private LibraryRepository libraryRepository;

    public Mutation(LibraryRepository libraryRepository) {
	this.libraryRepository = libraryRepository;
    }

    public Book addBook(String title, Long author, DataFetchingEnvironment env) throws Exception {
	return libraryRepository.addBook(title, author, env.<AuthContext>getContext());
    }
}
