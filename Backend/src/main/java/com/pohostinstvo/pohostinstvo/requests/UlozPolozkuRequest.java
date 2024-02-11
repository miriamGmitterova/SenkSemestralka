package com.pohostinstvo.pohostinstvo.requests;

import com.pohostinstvo.pohostinstvo.entity.enums.TypJedla;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UlozPolozkuRequest {
    private String nazovPolozky;
    private Double cena;
    private Double objem;
    private TypJedla typJedla;
}
