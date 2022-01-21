//package com.personio.companyhierarchylogin.controller;
//
//import com.personio.companyhierarchylogin.dto.AccountDTO;
//import com.personio.companyhierarchylogin.model.Account;
//import com.personio.companyhierarchylogin.repository.AccountRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Optional;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@WebMvcTest
//@AutoConfigureMockMvc(addFilters = false)
////@DataJpaTest    // Will use the H2 memory database to execute the tests
//public class LoginControllerTest {
//
//    public static String PERSONIO_API = "/personio/register";
//
//    @Autowired
//    MockMvc mvc;
//    @MockBean
//    AccountRepository accountRepository;
//
//    @Test
//    @DisplayName("Register an account successfully")
//    public void registerAnAccountSuccessfully() throws Exception{
//
//        String body = "{\n" +
//                "\t\"username\":\"personio\",\n" +
//                "\t\"password\":\"personio\"\n" +
//                "}";
//        AccountDTO accountDTO = new AccountDTO("personio","personio");
//        Account account = new Account(null,"personio","personio");
//        Account accountSaved = new Account(1L,"personio","personio");
//
//
//        BDDMockito.given(accountRepository.findByUsername("personio")).willReturn(Optional.ofNullable(null));
//        BDDMockito.given(accountRepository.save(account)).willReturn(accountSaved);
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(PERSONIO_API)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(body);
//
//        mvc.perform(request)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("message").value("Account registered successfully"));
//
//    }
//
//}
