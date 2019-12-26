package com.fast.eatgo.domain;

import lombok.Builder;

@Builder
public class MenuItem {

    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
