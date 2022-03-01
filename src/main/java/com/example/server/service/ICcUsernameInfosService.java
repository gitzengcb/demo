package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.CcUsernameInfos;
import com.example.server.publics.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2021-11-30
 */
public interface ICcUsernameInfosService extends IService<CcUsernameInfos> {
    /**
     * 登陆之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */

    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */

    CcUsernameInfos getAdminByUserName(String username);
}
