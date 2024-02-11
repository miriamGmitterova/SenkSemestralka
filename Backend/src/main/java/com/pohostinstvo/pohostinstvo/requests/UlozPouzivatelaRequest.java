package com.pohostinstvo.pohostinstvo.requests;

import com.pohostinstvo.pohostinstvo.entity.enums.PouzivatelskeRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UlozPouzivatelaRequest {

    private String meno;
    private String priezvisko;
    private String userName;

    private String heslo;

    private String email;
    private PouzivatelskeRole pouzivatelskeRole;
}
