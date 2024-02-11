package com.pohostinstvo.pohostinstvo.services;

import com.pohostinstvo.pohostinstvo.entity.Prispevok;
import com.pohostinstvo.pohostinstvo.repositories.PouzivatelRepository;
import com.pohostinstvo.pohostinstvo.repositories.PrispevokRepository;
import com.pohostinstvo.pohostinstvo.requests.UlozPrispevokRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrispevokService {

    private final PrispevokRepository prispevokRepository;
    private final PouzivatelRepository pouzivatelRepository;

    public void uloz(UlozPrispevokRequest request) {
        Prispevok prispevok = new Prispevok();

        prispevok.setText(request.getText());
        prispevok.setNadpis(request.getNadpis());
        prispevok.setVytvorene(LocalDateTime.now());
        prispevok.setTag(request.getTag());
        prispevok.setVytvoril(pouzivatelRepository.findById(request.getVytvoril()).get());
        prispevok.setObrazok(request.getObrazok());

        prispevokRepository.save(prispevok);
    }

    public void vymaz(UUID id) {

        prispevokRepository.deleteById(id);

    }

    public Prispevok get(UUID id) {

        return prispevokRepository.findById(id).get();
    }

    public void uprav(UlozPrispevokRequest request, UUID id) {
        Prispevok prispevok = prispevokRepository.findById(id).get();

        prispevok.setText(request.getText());
        prispevok.setNadpis(request.getNadpis());
        prispevok.setTag(request.getTag());

        prispevokRepository.save(prispevok);
    }

    public List<Prispevok> getZoznam() {
        return prispevokRepository.findAllByOrderByVytvoreneDesc();
    }
}
