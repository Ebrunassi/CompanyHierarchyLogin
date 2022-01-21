package com.personio.companyhierarchylogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @NotNull(message = "The username must not be null")
    private String username;
    @NotNull(message = "The password must not be null")
    private String password;
}
