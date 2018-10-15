package com.jazzinjars.graphql.sample.model;

import graphql.relay.Relay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEdge {

    private Comment comment;
    private String cursor;

    public CommentEdge(Comment comment) {
	this.comment = comment;
	this.cursor = toGlobalId(comment);
    }

    public Comment getNode() {
	return comment;
    }

    public String getCursor() {
	return cursor;
    }

    public static String toGlobalId(Comment comment) {
	return new Relay().toGlobalId(Comment.class.getName(), comment.getId().toString());
    }

    public static Long fromGlobalId(String cursor) {
	String id = new Relay().fromGlobalId(cursor).getId();
	return Long.parseLong(id);
    }
}
