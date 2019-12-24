package com.fast.eatgo.domain;

public class Restaurant {

    private String name;

    private String address;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Object getInformation() {
        return name + " In " + address;
    }
}
