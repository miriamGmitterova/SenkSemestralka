package com.pohostinstvo.pohostinstvo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pohostinstvo.pohostinstvo.entity.enums.TypAkcie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "rezervacia")
public class Rezervacia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime vytvorene;

    @ManyToOne()
    @JoinColumn(name = "vytvoril_id")
    private Pouzivatel vytvoril;

    private Date datum;
    private Time cas;

    private Integer pocetLudi;

    private TypAkcie typAkcie;

    @Column(columnDefinition = "TEXT")
    private String poznamka;

}
