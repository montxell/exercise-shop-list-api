package com.codethen.shopapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;


public class ShopApiConfiguration extends Configuration {

    private String environment;

    @JsonProperty
    public String getEnvironment() {
        return environment;
    }

    @JsonProperty
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}