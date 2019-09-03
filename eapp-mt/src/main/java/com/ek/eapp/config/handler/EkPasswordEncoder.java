package com.ek.eapp.config.handler;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EkPasswordEncoder
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-06
 * @Version: V2.0
 **/
@Component
public class EkPasswordEncoder implements PasswordEncoder {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);
    @Override
    public String encode(CharSequence charSequence) {
        System.out.println(charSequence);
        System.out.println(passwordEncoder.encode(charSequence));
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println(String.format("charSequence=%s, s=%s", charSequence, s));
        System.out.println(String.format("passwordEncoder.matches=%s", passwordEncoder.matches(charSequence, s)));
        return passwordEncoder.matches(charSequence, s);
    }

}
