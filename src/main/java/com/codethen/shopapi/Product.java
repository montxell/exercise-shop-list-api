package com.codethen.shopapi;


public class Product {

    private int id;
    private String name;
    private double price;
    private boolean available;


    public Product() {}



    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public boolean isAvailable() {

        return available;
    }

    public void setAvailable(boolean available) {

        this.available = available;
    }

}
