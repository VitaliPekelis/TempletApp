package com.vitalipekelis.templet.services.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vitali.pekelis on 07/09/2015.
 */
public class YRate {

    @JsonProperty("id")
    public String id_;
    @JsonProperty("Name")
    public String name_;
    @JsonProperty("Rate")
    public String rate;
    @JsonProperty("Date")
    public String date;
    @JsonProperty("Time")
    public String time;
    @JsonProperty("Ask")
    public String ask;
    @JsonProperty("Bid")
    public String bid;
}
