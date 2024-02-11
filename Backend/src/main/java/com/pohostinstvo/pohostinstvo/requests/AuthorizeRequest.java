package com.pohostinstvo.pohostinstvo.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeRequest {

    private String username;
    private String password;
}
