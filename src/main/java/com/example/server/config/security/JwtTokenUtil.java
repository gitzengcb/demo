package com.example.server.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* JwtToken工具类，根据用户信息生成token
* */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     *根据用户信息生成token--
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);

    }
    /**
     * 根据荷载生成JWT TOKEN
     * @param claims
     * @return
     */
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
    /**
     *
     */
    public String getUserNameFormToken(String Token){
        String username;
        try{
            Claims claims=getClaimsFormToken(Token);
            username=claims.getSubject();
        }catch (Exception e){
            username=null;
        }
        return username;
    }

    /**
     * 判断token是否有效
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFormToken(token);
        return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }
    /**
     * 判断token是否可以刷新
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }
    /**
     * 刷新token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * 判断token是否失效
     * @param token
     * @return
     */

    private boolean isTokenExpired(String token) {
        Date expireDate= getExpiredDateFormToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取失效时间
     * @param token
     * @return
     */

    private Date getExpiredDateFormToken(String token) {
        Claims claims=getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 从Token中获取荷载
     * @param token
     * @return
     */

    private Claims getClaimsFormToken(String token) {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }



    /**
     * 生成Token的失效时间
     * @return
     */

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

}
