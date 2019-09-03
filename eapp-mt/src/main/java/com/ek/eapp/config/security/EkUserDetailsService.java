package com.ek.eapp.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EkUserDetailsService
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-06
 * @Version: V2.0
 **/

@Component
public class EkUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

        return EkUserDetails.ekUserDetails;
    }

}
