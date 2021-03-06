package com.fast.eatgo.inter;

import com.fast.eatgo.application.RegionService;
import com.fast.eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("")
    public List<Region> list() {
        return regionService.getRegions();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Region resource) throws URISyntaxException {
        Region region = regionService.addRegion(resource.getName());
        String url = "/region/" + region.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
