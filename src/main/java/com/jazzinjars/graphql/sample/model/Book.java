package com.jazzinjars.graphql.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Publication {

    private Long id;
    private String title;
    private Author author;
    private LocalDate date;
    private List<Comment> comments;
}
