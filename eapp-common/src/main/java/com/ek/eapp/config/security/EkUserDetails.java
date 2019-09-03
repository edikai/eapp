package com.ek.eapp.config.security;

import com.ek.eapp.entity.EkUser;
import org.apache.commons.lang3.StringUtils;
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

    private String loginName;
    private String password;
    private String userName;
    private String userId;
    private Set<? extends GrantedAuthority> authorities;

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

    public static EkUserDetails init(EkUser user) {
        EkUserDetails userDetails = new EkUserDetails();
        userDetails.setLoginName(user.getLoginName());
        userDetails.setPassword(user.getPassword());
        userDetails.setUserName(user.getLoginName());
        userDetails.setUserId(StringUtils.join(user.getId()));

        return userDetails;
    }
}
