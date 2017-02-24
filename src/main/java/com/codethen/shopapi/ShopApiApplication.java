package com.codethen.shopapi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class ShopApiApplication extends Application<ShopApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new ShopApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "shopapi";
    }

    @Override
    public void initialize(Bootstrap<ShopApiConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ShopApiConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    }

}