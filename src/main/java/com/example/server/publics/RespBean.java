package com.example.server.publics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 公共返回对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RespBean {
    private long code;
    private String message;
    private Map data;
    private boolean success;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean sucess(String message){
        return new RespBean(200,message,null,true);
    }
    public static RespBean sucess(String message, Map data){
        return new RespBean(200,message,data,true);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null,false);
    }
    public static RespBean error(String message, Map data){
        return new RespBean(500,message,data,false);
    }

}
