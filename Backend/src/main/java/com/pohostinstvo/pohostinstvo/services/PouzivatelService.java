package com.pohostinstvo.pohostinstvo.services;

import com.pohostinstvo.pohostinstvo.entity.Pouzivatel;
import com.pohostinstvo.pohostinstvo.entity.enums.PouzivatelskeRole;
import com.pohostinstvo.pohostinstvo.repositories.PouzivatelRepository;
import com.pohostinstvo.pohostinstvo.requests.AuthorizeRequest;
import com.pohostinstvo.pohostinstvo.requests.UlozPouzivatelaRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PouzivatelService {

    private final PouzivatelRepository pouzivatelRepository;

    public void ulozPouzivatela(UlozPouzivatelaRequest ulozPouzivatelaRequest) {

        kontrolaNovehoPouzivatela(ulozPouzivatelaRequest);


        Pouzivatel pouzivatel = new Pouzivatel();
        pouzivatel.setEmail(ulozPouzivatelaRequest.getEmail());
        pouzivatel.setPriezvisko(ulozPouzivatelaRequest.getPriezvisko());
        pouzivatel.setMeno(ulozPouzivatelaRequest.getMeno());
        pouzivatel.setUserName(ulozPouzivatelaRequest.getUserName());


        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        pouzivatel.setHeslo(passwordEncoder.encode(ulozPouzivatelaRequest.getHeslo()));
        HashSet<PouzivatelskeRole> role = new HashSet<>();
        role.add(ulozPouzivatelaRequest.getPouzivatelskeRole());
        pouzivatel.setRole(role);


        pouzivatelRepository.save(pouzivatel);
    }


    public Pouzivatel getPouzivatel(UUID id) {

        if (!pouzivatelRepository.existsById(id)) {
            throw new NoSuchElementException("Chyba: pouzivatel s danym ID neexistuje");
        }

        return pouzivatelRepository.findById(id).get();
    }

    public void vymazPouzivatela(UUID id) {
        if (!pouzivatelRepository.existsById(id)) {
            throw new NoSuchElementException("Chyba: pouzivatel s danym ID neexistuje");
        }

        pouzivatelRepository.deleteById(id);
    }

    public void upravPouzivatela(UUID id, UlozPouzivatelaRequest ulozPouzivatelaRequest) {

        if (!pouzivatelRepository.existsById(id)) {
            throw new NoSuchElementException( "Chyba: pouzivatel s danym ID neexistuje");
        }

        Pouzivatel pouzivatel =  pouzivatelRepository.findById(id).get();

        pouzivatel.setEmail(ulozPouzivatelaRequest.getEmail());
        pouzivatel.setPriezvisko(ulozPouzivatelaRequest.getPriezvisko());
        pouzivatel.setMeno(ulozPouzivatelaRequest.getMeno());
        pouzivatel.setHeslo(ulozPouzivatelaRequest.getHeslo());
        pouzivatel.setUserName(ulozPouzivatelaRequest.getUserName());

        pouzivatelRepository.save(pouzivatel);
    }

    public List<Pouzivatel> getZoznam() {
        return pouzivatelRepository.findAll();
    }

    private void kontrolaNovehoPouzivatela(UlozPouzivatelaRequest request) {
        Boolean existujuciMail =  pouzivatelRepository.findAll().stream().anyMatch(pouzivatel -> pouzivatel.getEmail().equals(request.getEmail()));

        if (existujuciMail) {
            throw new ValidationException("Pouzivatel s danym mailom uz existuje");
        }


        Boolean existujuceUsername =  pouzivatelRepository.findAll().stream().anyMatch(pouzivatel -> pouzivatel.getUserName().equals(request.getUserName()));

        if (existujuceUsername) {
            throw new ValidationException("Pouzivatel s danym UserName uz existuje");
        }

    }

    public Pouzivatel authorise(AuthorizeRequest request) {

        List<Pouzivatel> zoznamPouzivatelov =  pouzivatelRepository.findAll();

        for (Pouzivatel p : zoznamPouzivatelov) {
            if (p.getUserName().equals(request.getUsername())) {
                return p;
            }

        }
        return null;
    }
}
