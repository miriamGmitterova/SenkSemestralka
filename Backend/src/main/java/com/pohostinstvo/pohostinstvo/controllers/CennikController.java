package com.pohostinstvo.pohostinstvo.controllers;


import com.pohostinstvo.pohostinstvo.entity.Polozka;
import com.pohostinstvo.pohostinstvo.requests.UlozPolozkuRequest;
import com.pohostinstvo.pohostinstvo.services.CennikService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CennikController {

    private final CennikService cennikService;


    @PostMapping("/cennik/uloz")
    public void ulozPolozku(@RequestBody UlozPolozkuRequest request) {
        cennikService.uloz(request);
    }


    @GetMapping("/cennik/zoznam")
    public List<Polozka> getZoznam() {
        return cennikService.getZoznam();
    }

    @DeleteMapping("/cennik/{id}")
    public void deletePolozka(@PathVariable UUID id) {
        cennikService.vymazPolozku(id);
    }

    @PutMapping("/cennik/{id}")
    public void upravPolozku(@PathVariable UUID id, @RequestBody UlozPolozkuRequest request) {
        cennikService.uprav(request, id);
    }

    @GetMapping("/cennik/{id}")
    public Polozka getCennik(@PathVariable UUID id) {
        return cennikService.getPolozka(id);
    }
}
