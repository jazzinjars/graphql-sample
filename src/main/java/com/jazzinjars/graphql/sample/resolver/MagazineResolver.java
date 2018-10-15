package com.jazzinjars.graphql.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.jazzinjars.graphql.sample.model.Magazine;
import com.jazzinjars.graphql.sample.repository.LibraryRepository;

public class MagazineResolver implements GraphQLResolver<Magazine> {

    private LibraryRepository libraryRespository;

    public MagazineResolver(LibraryRepository libraryRespository) {
	this.libraryRespository = libraryRespository;
    }
}
