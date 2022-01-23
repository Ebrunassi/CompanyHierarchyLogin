package com.personio.companyhierarchylogin.security.encoder;

import com.personio.companyhierarchylogin.utils.Cryptography;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword){
        String rsaPassword = Cryptography.getInstance().encode(rawPassword.toString());
        return rsaPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword){
        if(encodedPassword == null || encodedPassword.isEmpty())
            return false;
        if(rawPassword == null || rawPassword.length() <= 0)
            return false;
        return encodedPassword.equals(encode(rawPassword));
    }
}
