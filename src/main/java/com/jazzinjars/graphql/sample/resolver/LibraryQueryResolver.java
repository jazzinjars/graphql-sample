package com.jazzinjars.graphql.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jazzinjars.graphql.sample.repository.LibraryRepository;
import com.jazzinjars.graphql.sample.model.Author;
import com.jazzinjars.graphql.sample.model.Book;
import com.jazzinjars.graphql.sample.model.BookFilter;
import com.jazzinjars.graphql.sample.model.Publication;

import java.util.List;

public class LibraryQueryResolver implements GraphQLQueryResolver {

    private LibraryRepository libraryRepository;

    public LibraryQueryResolver(LibraryRepository libraryRepository) {
	this.libraryRepository = libraryRepository;
    }

    public List<Book> books(BookFilter filter) {
	return libraryRepository.findBooks(filter);
    }

    public List<Publication> publications() {
	return libraryRepository.findPublications();
    }

    public Book book(Long id) {
	return libraryRepository.findBookById(id).orElse(null);
    }

    public List<Author> authors() {
	return libraryRepository.getAuthors();
    }

    public Author author(Long id) {
	return libraryRepository.findAuthorById(id).orElse(null);
    }
}
