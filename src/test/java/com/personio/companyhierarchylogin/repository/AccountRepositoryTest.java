package com.personio.companyhierarchylogin.repository;

import com.personio.companyhierarchylogin.model.Account;
import com.personio.companyhierarchylogin.utils.Cryptography;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest    // Will use the H2 memory database to execute the tests
public class AccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Must create an account successfully")
    public void createAccountSuccessfully(){

        Account employee = new Account(null,"personio","12341");     // Scenario

        entityManager.persist(employee);                        // Save the data in h2 memory database
        Account saved = accountRepository.save(employee);

        assertThat(saved.getId()).isNotNull();                  // Account id must not be null
    }

    @Test
    @DisplayName("Must find an account in database successfully")
    public void findAccountByUsernameSuccessfully(){

        Account employee = new Account(null,"personio","12341");     // Scenario

        entityManager.persist(employee);                                            // Save the data in h2 memory database
        Optional<Account> founded = accountRepository.findByUsername("personio");

        assertThat(founded.get()).isNotNull();                                      // Account must not be null
        assertThat(founded.get().getUsername()).isEqualToIgnoringCase("personio");  // The username must be equal that the used to search
        assertThat(founded.get().getId()).isNotNull();                              // Account id must not be null
    }

    @Test
    @DisplayName("Not found an account by the name")
    public void notFoundEmployeeByName(){
        Optional<Account> founded = accountRepository.findByUsername("personio");
        assertThat(founded.isEmpty()).isTrue();                                     // The option must not have an element inside of it
    }


}

