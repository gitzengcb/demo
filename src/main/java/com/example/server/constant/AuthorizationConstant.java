package com.example.server.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author kentzhong
 */
@Component
public class AuthorizationConstant {

    public static String USER_NAME;

    public static String PASSWORD;

    @Value("${hbos.user}")
    public void setUserName(String userName) { USER_NAME = userName; }
    @Value("${hbos.password}")
    public void setPassword(String password) { PASSWORD = password; }

}
