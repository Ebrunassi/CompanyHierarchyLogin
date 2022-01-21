package com.personio.companyhierarchylogin.controller;

import com.personio.companyhierarchylogin.dto.AccountDTO;
import com.personio.companyhierarchylogin.dto.ResponseDTO;
import com.personio.companyhierarchylogin.model.Account;
import com.personio.companyhierarchylogin.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/personio")
@EnableAutoConfiguration
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ResponseDTO> registerAccount(@RequestBody @Valid AccountDTO accountDTO){
        Optional<Account> account = accountRepository.findByUsername(accountDTO.getUsername());
        Account requestAccount = new Account(accountDTO);
        if(account.isPresent())
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("There is already an account registered with this name"), HttpStatus.BAD_REQUEST);
        else
            accountRepository.save(requestAccount);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Account registered successfully"), HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RuntimeException handleValidationExceptions(MethodArgumentNotValidException exception){

        return new RuntimeException(exception.getMessage());
    }
}
