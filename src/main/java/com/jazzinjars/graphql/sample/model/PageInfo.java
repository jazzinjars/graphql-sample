package com.jazzinjars.graphql.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

    private String startCursor;
    private String endCursor;
    private boolean hasNextPage;

}
