package com.graphql.graphqldog.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogNotFoundException extends RuntimeException implements GraphQLError {
    private final Map<String, Object> extensions = new HashMap<>();

    public DogNotFoundException(String message, Integer dogId) {
        super(message);
        this.extensions.put("Invalid dog id", dogId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;

    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
