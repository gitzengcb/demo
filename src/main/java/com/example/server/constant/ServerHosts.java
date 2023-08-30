package com.example.server.constant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by KentZhong on 2018/10/18.
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServerHosts {
    private static final long serialVersionUID = 1L;
    //    Not works in @BeforeSuit
    public static String SERVER_HOST;
    //public static  String HOST_HOST;
    private String username;
    private String password;
    private String hosturl;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHosturl() {
        return hosturl;
    }

    public void setHosturl(String hosturl) {
        this.hosturl = hosturl;
    }

    @Value("${hbos.host}")
    public void setPermissionHost(String permissionHost){
        SERVER_HOST = permissionHost;
    }
//    @Value("${hbos.hostst}")
//    public void setHostHost(String hostst){
//        HOST_HOST=hostst;
//    };




}
