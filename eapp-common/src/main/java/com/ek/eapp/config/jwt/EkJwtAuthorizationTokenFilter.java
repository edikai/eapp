package com.ek.eapp.config.jwt;

import com.ek.eapp.config.security.EkUserDetails;
import com.ek.eapp.entity.EkUser;
import com.ek.eapp.service.IEkUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: EkJwtAuthorizationTokenFilter
 * @Description: JWT拦截器，对token进行验证
 * @Author: qin_hqing
 * @Date: 2019-08-07
 * @Version: V2.0
 **/
@Component
public class EkJwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.token_header}")
    private String EK_TOKEN_HEADER;
    @Value("${jwt.token_prefix}")
    private String EK_TOKEN_PREFIX;

    @Autowired
    private EkJwtTokenUtil jwtTokenUtil;
    @Autowired
    private IEkUserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(this.EK_TOKEN_HEADER);
        log.info("{} : {}", this.EK_TOKEN_HEADER, authHeader);
        if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(this.EK_TOKEN_PREFIX)){
            final String authToken = StringUtils.substring(authHeader, this.EK_TOKEN_PREFIX.length());
            String logName = StringUtils.isNoneEmpty(authToken) ? jwtTokenUtil.getUsernameFromToken(authToken) : null;

            if (StringUtils.isNotEmpty(logName) && SecurityContextHolder.getContext().getAuthentication() == null){
                EkUser user = userService.selectByLoginName(logName);
                EkUserDetails userDetails = new EkUserDetails();
                userDetails.setPassword(user.getPassword());
                userDetails.setLoginName(user.getLoginName());
                userDetails.setUserName(user.getUsername());
                userDetails.setUserId(StringUtils.join(user.getId()));

                if (jwtTokenUtil.validateToken(authToken, userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
