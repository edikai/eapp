package com.ek.eapp.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @ClassName: EkUserDetails
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-06
 * @Version: V2.0
 **/

public class EkUserDetails implements UserDetails, Serializable {

    public static final String LOGIN_NAME = "admin";
    public static final String PASSWORD = "itc123";
    public static final String USER_NAME = "admin";
    public static final String USER_ID = "1";

    public static EkUserDetails ekUserDetails;
    private String loginName;
    private String password;
    private String userName;
    private String userId;
    private Set<? extends GrantedAuthority> authorities;

    private EkUserDetails() {
    }

    public static EkUserDetails init(String loginName, String userId) {
        EkUserDetails user = new EkUserDetails();
        user.setLoginName(loginName);
        user.setPassword(userId);
        user.setUserId(userId);
        user.setUserName(loginName);

        ekUserDetails = user;

        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
