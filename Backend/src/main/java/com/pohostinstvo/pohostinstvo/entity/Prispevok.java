package com.pohostinstvo.pohostinstvo.entity;


import com.pohostinstvo.pohostinstvo.entity.enums.PrispevokTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "prispevok")
@Entity
public class Prispevok {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime vytvorene;

    @ManyToOne()
    @JoinColumn(name = "vytvoril_id")
    private Pouzivatel vytvoril;

    private String nadpis;

    @Column(columnDefinition = "TEXT")
    private String text;

    private PrispevokTag tag;

    @Column(columnDefinition = "TEXT")
    private String obrazok;


}
