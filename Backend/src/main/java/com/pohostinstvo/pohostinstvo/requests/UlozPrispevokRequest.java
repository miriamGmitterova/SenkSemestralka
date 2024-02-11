package com.pohostinstvo.pohostinstvo.requests;

import com.pohostinstvo.pohostinstvo.entity.Pouzivatel;
import com.pohostinstvo.pohostinstvo.entity.enums.PrispevokTag;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UlozPrispevokRequest {



    private UUID vytvoril;

    private String nadpis;
    private String text;
    private PrispevokTag tag;
    private String obrazok;
}
