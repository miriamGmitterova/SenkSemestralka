package com.pohostinstvo.pohostinstvo.controllers;

import com.pohostinstvo.pohostinstvo.entity.Prispevok;
import com.pohostinstvo.pohostinstvo.requests.UlozPrispevokRequest;
import com.pohostinstvo.pohostinstvo.services.PrispevokService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PrispevokController {

    private final PrispevokService prispevokService;

    @PostMapping("/prispevok/uloz")
    @ResponseStatus(HttpStatus.CREATED)
    public void ulozPrispevok(@RequestBody UlozPrispevokRequest request) {
        prispevokService.uloz(request);
    }

    @GetMapping("/prispevok/zoznam")
    public List<Prispevok> getZoznamPrispevkov() {
        return prispevokService.getZoznam();
    }

    @DeleteMapping("/prispevok/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vymazPrispevok(@PathVariable UUID id) {
        prispevokService.vymaz(id);
    }

    @GetMapping("/prispevok/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Prispevok getPrispevok(@PathVariable UUID id) {
       return prispevokService.get(id);
    }

    @PutMapping("/prispevok/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upravPrispevok(@RequestBody UlozPrispevokRequest request, @PathVariable UUID id) {
        prispevokService.uprav(request, id);
    }
}
