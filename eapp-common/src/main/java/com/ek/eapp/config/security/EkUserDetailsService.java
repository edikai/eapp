package com.ek.eapp.config.security;

import com.ek.eapp.entity.EkUser;
import com.ek.eapp.service.IEkUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IEkUserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
//org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.ek.eapp.dao.EkUserDao.selectByLoginName
        log.info("------------------------------{}", loginName);
        EkUserDetails userDetails = null;
        EkUser dbUser = userService.selectByLoginName(loginName);

        if (null != dbUser){
            userDetails = new EkUserDetails();
            userDetails.setLoginName(dbUser.getLoginName());
            userDetails.setPassword(dbUser.getPassword());
            userDetails.setUserName(dbUser.getLoginName());
            userDetails.setUserId(StringUtils.join(dbUser.getId()));
        }else {
            log.error("{} is not exist.", loginName);
            throw new UsernameNotFoundException(String.format("%s is not exist.", loginName));
        }

        return userDetails;
    }

}
