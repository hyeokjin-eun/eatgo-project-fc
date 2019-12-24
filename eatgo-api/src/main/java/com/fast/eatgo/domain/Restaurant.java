package com.fast.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Restaurant {

    private Long id;

    private String name;

    private String address;

    public Object getInformation() {
        return name + " In " + address;
    }
}
