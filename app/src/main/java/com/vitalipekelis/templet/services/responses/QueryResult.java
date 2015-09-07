package com.vitalipekelis.templet.services.responses;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class QueryResult {

    @JsonProperty("query")
    public YQuery query;

}
