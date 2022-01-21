package com.personio.companyhierarchylogin.model;

import com.personio.companyhierarchylogin.dto.AccountDTO;
import com.personio.companyhierarchylogin.utils.Cryptography;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personio_user")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public Account(AccountDTO accountDTO) {
        this.username = accountDTO.getUsername();
        this.password = Cryptography.getInstance().encode(accountDTO.getPassword());
    }
}
