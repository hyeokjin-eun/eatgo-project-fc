package com.fast.eatgo.inter;

import com.fast.eatgo.application.MenuItemService;
import com.fast.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PatchMapping("/restaurant/{restaurantId}/menuItems")
    public String bulkUpdate(@PathVariable Long restaurantId, @RequestBody List<MenuItem> menuItems) {
        menuItemService.bulkUpdate(restaurantId, menuItems);
        return "";
    }
}
