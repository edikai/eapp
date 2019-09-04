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

    // BCryptPasswordEncoder 参数越大，加密级别越高，加密速度越慢
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
    @Override
    public String encode(CharSequence charSequence) {
        String pwd = passwordEncoder.encode(charSequence);
        log.debug("charSequence = {}, passwordEncoder.encode = {}", charSequence, pwd);
        return pwd;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        boolean mch = passwordEncoder.matches(charSequence, s);
        log.debug("charSequence={}, s={}, matches={}", charSequence, s, mch);
        return mch;
    }

    public static void main(String[] args) {
        EkPasswordEncoder encoder = new EkPasswordEncoder();
        System.out.println(encoder.encode("itc123"));
        System.out.println(encoder.encode("itc123"));

        encoder.matches("itc123", "$2a$05$jocSspsqrXyLwPsf6awLtuBSR6zdyOpWR.mGdPcxiBbqvuSD9Eqse");
    }

}
