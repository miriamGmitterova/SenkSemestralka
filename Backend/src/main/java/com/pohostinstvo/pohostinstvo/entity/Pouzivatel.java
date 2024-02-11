package com.pohostinstvo.pohostinstvo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pohostinstvo.pohostinstvo.entity.enums.PouzivatelskeRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "pouzivatel")
public class Pouzivatel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String meno;
    private String priezvisko;
    private String userName;

    private String heslo;

    private String email;

    private Set<PouzivatelskeRole> role;

    @OneToMany(mappedBy = "vytvoril",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Prispevok> prispevky;

    @OneToMany(mappedBy = "vytvoril",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Rezervacia> rezervacie;


}
