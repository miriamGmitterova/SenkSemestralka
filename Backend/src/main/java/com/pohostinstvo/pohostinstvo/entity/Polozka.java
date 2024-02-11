package com.pohostinstvo.pohostinstvo.entity;


import com.pohostinstvo.pohostinstvo.entity.enums.TypJedla;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "polozka")
public class Polozka {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nazovPolozky;
    private Double cena;
    private Double objem;
    private TypJedla typJedla;
}
