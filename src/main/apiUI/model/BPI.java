package main.apiUI.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BPI{
    @JsonProperty("USD")
    public USD uSD;
    @JsonProperty("GBP")
    public GBP gBP;
    @JsonProperty("EUR")
    public EUR eUR;
}
