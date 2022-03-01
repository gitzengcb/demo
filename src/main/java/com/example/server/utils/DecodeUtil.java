package com.example.server.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by xukun.lxk on 2020/2/14.
 */
public class DecodeUtil {

    public static String decode(String decodedStr) {
        if (!StringUtils.isEmpty(decodedStr)) {
            return Base64Plus.decodeBase64(decodedStr);
        } else {
            return "";
        }
    }
}
