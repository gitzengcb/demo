package com.example.server.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by KentZhong on 2018/10/18.
 */
@Component
public class ServerHosts {
//    Not works in @BeforeSuit
    public static String SERVER_HOST;
    public static  String HOST_HOST;


    @Value("${hbos.host}")
    public void setPermissionHost(String permissionHost){
        SERVER_HOST = permissionHost;
    }
    @Value("${hbos.hostst}")
    public void setHostHost(String hostst){
        HOST_HOST=hostst;
    };




}
