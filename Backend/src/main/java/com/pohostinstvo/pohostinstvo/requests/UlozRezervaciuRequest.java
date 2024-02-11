package com.pohostinstvo.pohostinstvo.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pohostinstvo.pohostinstvo.entity.enums.TypAkcie;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Validated
public class UlozRezervaciuRequest {


    private UUID vytvoril;

    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date datum;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime cas;

    private Integer pocetLudi;

    private TypAkcie typAkcie;
    private String poznamka;
}
