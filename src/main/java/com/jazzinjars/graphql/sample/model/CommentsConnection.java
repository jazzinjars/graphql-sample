package com.jazzinjars.graphql.sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CommentsConnection {

    private List<CommentEdge> edges;
    private PageInfo pageInfo;

    public CommentsConnection(List<Comment> comments, PageInfo pageInfo) {
	this.edges = comments.stream().map(edge -> new CommentEdge(edge)).collect(Collectors.toList());
	this.pageInfo = pageInfo;
    }
}
