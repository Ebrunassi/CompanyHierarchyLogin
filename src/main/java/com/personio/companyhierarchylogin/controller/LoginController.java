package com.personio.companyhierarchylogin.controller;

import com.personio.companyhierarchylogin.dto.AccountDTO;
import com.personio.companyhierarchylogin.dto.ResponseDTO;
import com.personio.companyhierarchylogin.model.Account;
import com.personio.companyhierarchylogin.repository.AccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Tag(name = "Login Controller")
@RestController
@RequestMapping("/personio")
@EnableAutoConfiguration
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;
    private ModelMapper modelMapper = new ModelMapper();
    Logger logger = LogManager.getLogger();

    @Operation(description = "Receives a JSON containing the username and password of a new user in order to be saved in database",
    responses = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "There is already one user with the same username")
    })
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ResponseDTO> registerAccount(@RequestBody @Valid AccountDTO accountDTO){
        logger.info("Registering new account with login '{}'", accountDTO.getUsername());
        Optional<Account> account = accountRepository.findByUsername(accountDTO.getUsername());
        Account requestAccount = new Account(accountDTO);
        if(account.isPresent()) {
            logger.error("There is already an account registered in database with username '{}'", account.get().getUsername());
            return new ResponseEntity<ResponseDTO>(
                    new ResponseDTO("There is already an account registered with username '" + account.get().getUsername() + "'")
                    , HttpStatus.BAD_REQUEST);
        }else
            accountRepository.save(requestAccount);
        logger.info("Account registered successfully!");
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Account registered successfully"), HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RuntimeException handleValidationExceptions(MethodArgumentNotValidException exception){

        return new RuntimeException(exception.getMessage());
    }
}
