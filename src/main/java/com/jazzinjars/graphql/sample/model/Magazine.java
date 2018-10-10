package com.jazzinjars.graphql.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Magazine extends Publication {

    private Long id;
    private String name;
    private Long pages;
}
