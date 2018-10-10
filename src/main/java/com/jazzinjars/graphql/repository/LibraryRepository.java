package com.jazzinjars.graphql.repository;

import com.jazzinjars.graphql.sample.model.Author;
import com.jazzinjars.graphql.sample.model.Book;

import java.util.Collection;

public class LibraryRepository {

    private long sequence;
    private Collection<Book> books;
    private Collection<Author> authors;
}
