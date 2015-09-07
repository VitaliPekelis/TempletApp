package com.vitalipekelis.templet.services.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vitali.pekelis on 07/09/2015.
 */
public class Results {
    @JsonProperty("rate")
    public List<YRate> rateList;
}
