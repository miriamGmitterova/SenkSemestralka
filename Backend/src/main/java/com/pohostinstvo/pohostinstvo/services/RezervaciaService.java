package com.pohostinstvo.pohostinstvo.services;

import com.pohostinstvo.pohostinstvo.entity.Rezervacia;
import com.pohostinstvo.pohostinstvo.repositories.PouzivatelRepository;
import com.pohostinstvo.pohostinstvo.repositories.RezervaciaRepository;
import com.pohostinstvo.pohostinstvo.requests.UlozRezervaciuRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class RezervaciaService {

    private final RezervaciaRepository rezervaciaRepository;
    private final PouzivatelRepository pouzivatelRepository;

    public void uloz(@Valid UlozRezervaciuRequest request) {

        if (request.getDatum().before(Date.valueOf(LocalDateTime.now().toLocalDate()))) {
            throw new IllegalArgumentException("Dátum je v minulosti");
        }


        if (!pouzivatelRepository.existsById(request.getVytvoril())) {
            throw new NoSuchElementException("Chyba: pouzivatel s danym ID neexistuje");
        }

        Rezervacia rezervacia = new Rezervacia();

        rezervacia.setVytvorene(LocalDateTime.now());
        rezervacia.setDatum(request.getDatum());
        rezervacia.setVytvoril(pouzivatelRepository.findById(request.getVytvoril()).get());
        rezervacia.setPocetLudi(request.getPocetLudi());
        rezervacia.setCas(Time.valueOf(request.getCas()));
        rezervacia.setTypAkcie(request.getTypAkcie());
        rezervacia.setPoznamka(request.getPoznamka());

        rezervaciaRepository.save(rezervacia);
    }


    public List<Rezervacia> getZoznam() {
        return rezervaciaRepository.findAll();
    }

    public void vymaz(UUID id) {

        if (!rezervaciaRepository.existsById(id)) {
            throw new NoSuchElementException("Chyba: rezervacia s danym ID neexistuje");
        }

        rezervaciaRepository.deleteById(id);
    }

    public Rezervacia getRezervacia(UUID id) {
        if (!rezervaciaRepository.existsById(id)) {
            throw new NoSuchElementException("Chyba: rezervacia s danym ID neexistuje");
        }

        return rezervaciaRepository.findById(id).get();
    }

    public void uprav(UUID id, UlozRezervaciuRequest request) {
        if (!rezervaciaRepository.existsById(id)) {
            throw new NoSuchElementException("Chyba: rezervacia s danym ID neexistuje");
        }

        Rezervacia rezervacia = rezervaciaRepository.findById(id).get();

        rezervacia.setCas(rezervacia.getCas());
        rezervacia.setDatum(request.getDatum());
        rezervacia.setPocetLudi(request.getPocetLudi());
        rezervacia.setPoznamka(request.getPoznamka());


        rezervaciaRepository.save(rezervacia);
    }
}
