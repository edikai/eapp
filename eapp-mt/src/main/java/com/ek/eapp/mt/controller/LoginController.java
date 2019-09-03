package com.ek.eapp.mt.controller;

import com.ek.eapp.config.jwt.EkJwtTokenUtil;
import com.ek.eapp.config.security.EkUserDetails;
import com.ek.eapp.util.EkRedisUtil;
import com.ek.eapp.util.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EkJwtTokenUtil jwtTokenUtil;
    @Autowired
    private EkRedisUtil redisUtil;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return R
     */
    @RequestMapping(value = "/get-token", method = RequestMethod.POST)
    public @ResponseBody
    R queryAll(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        log.info("--------------------login------------------------------------");
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            log.info(headers.nextElement());
        }
        String loginName = request.getHeader("login_name");
        String corpId = request.getHeader("corp_id");
//        String userId = request.getHeader("user_id");
        String password = request.getHeader("password");

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(corpId) || StringUtils.isEmpty(password)){
            return R.error(403, "params error, [login_name, corp_id] is necessary.");
        }
        if (!(loginName.equals(EkUserDetails.LOGIN_NAME) && password.equals(EkUserDetails.PASSWORD))){
            return R.error(403, "Authorization error.");
        }

        EkUserDetails userDetails = EkUserDetails.init(loginName, corpId);

        String token = "";
        String key = EkJwtTokenUtil.generateKey(loginName);
        if (redisUtil.containsKey(key)){
            log.info("contains redis key {}.", key);
            token = redisUtil.get(key);
        }else {
            log.warn("Don't contains redis key {}, generate one.", key);
            token = jwtTokenUtil.generateToken(userDetails);
        }

        return R.ok().put("token", token);
//        return ServiceResult.success(R.ok());
    }

}
