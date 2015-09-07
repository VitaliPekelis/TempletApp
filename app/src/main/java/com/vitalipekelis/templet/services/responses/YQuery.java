package com.vitalipekelis.templet.services.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vitali.pekelis on 07/09/2015.
 */
public class YQuery {

   @JsonProperty("count")
    public int count;

    @JsonProperty("created")
    public String created;

    @JsonProperty("lang")
    public String lang;

    @JsonProperty("results")
    public Results results;

}
