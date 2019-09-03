package com.ek.eapp.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger log = LoggerFactory.getLogger(this.getClass());

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);
    @Override
    public String encode(CharSequence charSequence) {
        log.info("charSequence = {}", charSequence);
        log.info("passwordEncoder.encode = {}", passwordEncoder.encode(charSequence));
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println(String.format("charSequence=%s, s=%s", charSequence, s));
        System.out.println(String.format("passwordEncoder.matches=%s", passwordEncoder.matches(charSequence, s)));
        return passwordEncoder.matches(charSequence, s);
    }

    public static void main(String[] args) {
        EkPasswordEncoder encoder = new EkPasswordEncoder();
        System.out.println(encoder.encode("itc123"));
    }

}
