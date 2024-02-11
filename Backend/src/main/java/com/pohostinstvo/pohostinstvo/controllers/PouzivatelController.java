package com.pohostinstvo.pohostinstvo.controllers;


import com.pohostinstvo.pohostinstvo.entity.Pouzivatel;
import com.pohostinstvo.pohostinstvo.entity.enums.PouzivatelskeRole;
import com.pohostinstvo.pohostinstvo.requests.AuthorizeRequest;
import com.pohostinstvo.pohostinstvo.requests.UlozPouzivatelaRequest;
import com.pohostinstvo.pohostinstvo.services.PouzivatelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PouzivatelController {

    private final PouzivatelService pouzivatelService;

    @PostMapping("/pouzivatel/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozPouzivatela(@RequestBody UlozPouzivatelaRequest ulozPouzivatelaRequest) {
        pouzivatelService.ulozPouzivatela(ulozPouzivatelaRequest);

    }

    @GetMapping("/pouzivatel/zoznam")
    public List<Pouzivatel> getZoznamPouzivatelov() {
        return pouzivatelService.getZoznam();
    }


    @GetMapping("/pouzivatel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pouzivatel getPouzivatel(@PathVariable UUID id) {

        return pouzivatelService.getPouzivatel(id);
    }

    @DeleteMapping("/pouzivatel/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazPouzivatela(@PathVariable UUID id) {
        pouzivatelService.vymazPouzivatela(id);
    }

    @PutMapping("/pouzivatel/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upravPouzivatela(@PathVariable UUID id, UlozPouzivatelaRequest ulozPouzivatelaRequest) {

        pouzivatelService.upravPouzivatela(id, ulozPouzivatelaRequest);
    }


    @PostMapping("/pouzivatel/authorise")
    @ResponseStatus(HttpStatus.OK)
    public Pouzivatel auuthorize(@RequestBody AuthorizeRequest request) {

        return pouzivatelService.authorise(request);
    }
}
