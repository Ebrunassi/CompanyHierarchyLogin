package com.personio.companyhierarchylogin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String token_type;
    private String access_token;
    private String expiration;
}
