package com.pohostinstvo.pohostinstvo.services;

import com.pohostinstvo.pohostinstvo.entity.Polozka;
import com.pohostinstvo.pohostinstvo.repositories.CennikRepository;
import com.pohostinstvo.pohostinstvo.requests.UlozPolozkuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CennikService {

    private final CennikRepository repository;

    public void uloz(UlozPolozkuRequest request) {

        Polozka polozka = new Polozka();
        polozka.setCena(request.getCena());
        polozka.setObjem(request.getObjem());
        polozka.setTypJedla(request.getTypJedla());
        polozka.setNazovPolozky(request.getNazovPolozky());

        repository.save(polozka);
    }

    public List<Polozka> getZoznam() {
        return repository.findAll();
    }

    public void vymazPolozku(UUID id) {
        repository.deleteById(id);

    }

    public Polozka getPolozka(UUID id) {
        return repository.findById(id).get();
    }

    public void uprav(UlozPolozkuRequest request, UUID id) {
        Polozka polozka = repository.findById(id).get();

        polozka.setCena(request.getCena());
        polozka.setObjem(request.getObjem());
        polozka.setTypJedla(request.getTypJedla());
        polozka.setNazovPolozky(request.getNazovPolozky());

        repository.save(polozka);

    }
}
