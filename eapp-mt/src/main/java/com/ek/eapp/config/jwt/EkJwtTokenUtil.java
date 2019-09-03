package com.ek.eapp.config.jwt;

import com.ek.eapp.config.security.EkUserDetails;
import com.ek.eapp.util.EkRedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName: EkJwtTokenUtil
 * @Description: JWT工具类，配合Redis
 * @Author: qin_hqing
 * @Date: 2019-08-07
 * @Version: V2.0
 **/
@Component
public class EkJwtTokenUtil implements Serializable {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = -3301605591108950415L;
    // 权限缓存前缀
    public static final String REDIS_PREFIX_AUTH = "auth:";
    // 用户信息缓存前缀
    public static final String REDIS_PREFIX_USER = "user-details:";

    @Autowired
    private EkRedisUtil redisUtil;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.token_header}")
    private String tokenHeader;

    private Clock clock = DefaultClock.INSTANCE;

    /**
     * 生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        EkUserDetails ekUserDetails = (EkUserDetails) userDetails;
        Map<String, Object> claims = new HashMap<>();
        String token = doGenerateToken(claims, ekUserDetails.getLoginName());
        String key = generateKey(((EkUserDetails) userDetails).getLoginName());
        redisUtil.set(key, token, expiration);
        return token;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration);
    }

    /**
     * 校验token是否合法
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        final String logName = getUsernameFromToken(token);

        return StringUtils.isNotEmpty(token)
                && !isTokenExpired(token);

    }

    /**
     * 校验token是否合法
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        EkUserDetails user = (EkUserDetails) userDetails;
        final String logName = getUsernameFromToken(token);

        String key = generateKey(user.getLoginName());
        if (redisUtil.containsKey(key)){
            return StringUtils.isNotEmpty(token)
                    && token.equals(redisUtil.get(key))
                    && (logName.equals(user.getLoginName())
                    && !isTokenExpired(token));
        }

        return false;
    }

    /**
     * 根据token获取登录用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 添加EkUserDetails缓存
     * @param userDetails
     */
    public void putUserDetails(UserDetails userDetails){
        EkUserDetails user = (EkUserDetails) userDetails;
        String key = generateKey(user.getLoginName());
        redisUtil.set(key, user, expiration);
    }

    /**
     * 根据token获取EkUserDetails
     * @param token
     * @return
     */
    public UserDetails getUserDetails(String token){
        String logName = getUsernameFromToken(token);
        String key = generateKey(logName);
        if (redisUtil.containsKey(key)){
            return redisUtil.get(key, EkUserDetails.class);
        }
        return null;
    }

    public static String generateKey(String loginName) {
        return String.format("%s%s", REDIS_PREFIX_AUTH, loginName);
    }
}
