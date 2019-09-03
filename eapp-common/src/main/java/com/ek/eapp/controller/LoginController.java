package com.ek.eapp.controller;

import com.ek.eapp.config.jwt.EkJwtTokenUtil;
import com.ek.eapp.config.security.EkUserDetails;
import com.ek.eapp.entity.EkUser;
import com.ek.eapp.service.IEkUserService;
import com.ek.eapp.util.EkRedisUtil;
import com.ek.eapp.util.R;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-03
 * @Version: V2.0
 **/
@RestController
@RequestMapping("/api")
public class LoginController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEkUserService userService;
    @Autowired
    private EkJwtTokenUtil jwtTokenUtil;
    @Autowired
    private EkRedisUtil redisUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 获取 accessToken
     * @param request
     * @return R
     */
    @RequestMapping(value = "/get-token", method = RequestMethod.POST)
    public @ResponseBody
    R getAccessToken(HttpServletRequest request) {
        log.info("--------------------get access token------------------------------------");
        String loginName = request.getHeader("login_name");
        String corpId = request.getHeader("corp_id");
        String password = request.getHeader("password");

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(corpId) || StringUtils.isEmpty(password)){
            return R.error(403, "params error, [login_name, corp_id] is necessary.");
        }
//        password = "$2a$14$QMlyAe5rtD8iULUjyAthReGrsNgxa4NsC5FVOe.5e9ALDSNPNza/S";
        // org.springframework.security.authentication.InternalAuthenticationServiceException: Invalid bound statement (not found): com.ek.eapp.dao.EkUserDao.selectByloginName
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginName,password);
        //使用SpringSecurity拦截登陆请求 进行认证和授权
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("authentication.isAuthenticated() = {}", authentication.isAuthenticated());
        if (!authentication.isAuthenticated()){
            return R.error(403, "Authorization error.");
        }

        String token = "";
        String key = EkJwtTokenUtil.generateKey(loginName);
        if (redisUtil.containsKey(key)){
            log.info("contains redis key {}.", key);
            token = redisUtil.get(key);
        }else {
            log.warn("Don't contains redis key {}, generate one.", key);
            EkUser user = userService.selectByLoginName(loginName);
            EkUserDetails userDetails = EkUserDetails.init(user);
            token = jwtTokenUtil.generateToken(userDetails);
        }

        return R.ok().put("token", token);
    }

}
