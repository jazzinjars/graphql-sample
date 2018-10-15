package com.jazzinjars.graphql.sample.resolver;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateCoercing implements Coercing<LocalDate, String> {

    private DateTimeFormatter dateTimeFormatter;

    public LocalDateCoercing() {
	this(DateTimeFormatter.ISO_DATE);
    }

    public LocalDateCoercing(DateTimeFormatter formatter) {
	this.dateTimeFormatter = formatter;
    }

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
	try {
	    LocalDate date = (LocalDate) dataFetcherResult;
	    return date.format(dateTimeFormatter);
	} catch (Exception e) {
	    throw new CoercingSerializeException(e);
	}
    }

    @Override
    public LocalDate parseValue(Object input) throws CoercingParseValueException {
	return parse(input);
    }

    @Override
    public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
	return parse(input);
    }

    private LocalDate parse(Object input) {
	try {
	    String string = (String) input;
	    return LocalDate.parse(string, dateTimeFormatter);
	} catch (Exception e) {
	    throw new CoercingParseValueException(e);
	}
    }
}
