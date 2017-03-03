package com.codethen.shopapi;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
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
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(ShopApiConfiguration configuration, Environment environment) {

        // Dropwizard lee lo que hay en configuration
        String envName = configuration.getEnvironment();
        System.out.println("Environment: " + envName);

        ProductDAO productDAO = new ProductDAO();

        ProductResource productResource = new ProductResource(productDAO);
        environment.jersey().register(productResource);

    }

}