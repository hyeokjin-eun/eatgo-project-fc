package com.fast.eatgo.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    @NotEmpty
    @Setter
    private String email;

    @NotEmpty
    @Setter
    private String name;

    @Setter
    private String password;

    @NotNull
    @Setter
    private Long level;

    private Long restaurantId;

    public boolean isAdmin() {
        return this.level > 3;
    }

    public boolean isActive() {
        return this.level > 0;
    }

    public void deActive() {
        this.level = 0L;
    }

    public boolean isRestaurantOwner() {
        return this.level == 50L;
    }

    public void setRestaurantId(Long restaurantId) {
        this.level = 50L;
        this.restaurantId = restaurantId;
    }
}
