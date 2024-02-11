package com.pohostinstvo.pohostinstvo.controllers;


import com.pohostinstvo.pohostinstvo.entity.Rezervacia;
import com.pohostinstvo.pohostinstvo.requests.UlozRezervaciuRequest;
import com.pohostinstvo.pohostinstvo.services.RezervaciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RezervaciaController {

    private final RezervaciaService rezervaciaService;



    @PostMapping("/rezervacia/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozRezervaciu(@RequestBody UlozRezervaciuRequest request) {
        rezervaciaService.uloz(request);
    }

    @GetMapping("/rezervacia/zoznam")
    @ResponseStatus(HttpStatus.OK)
    public List<Rezervacia> getZoznamRezervacii() {
        return rezervaciaService.getZoznam();
    }

    @DeleteMapping("/rezervacia/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void vymazRezervaciu(@PathVariable UUID id) {
        rezervaciaService.vymaz(id);
    }

    @PutMapping("/rezervacia/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void upravRezervaciu(@PathVariable UUID id, @RequestBody UlozRezervaciuRequest request) {
        rezervaciaService.uprav(id, request);
    }

    @GetMapping("/rezervacia/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rezervacia getRezervaciu(@PathVariable UUID id) {
        return rezervaciaService.getRezervacia(id);
    }



}
