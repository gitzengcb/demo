package com.example.server.controller;

import com.example.server.pojo.AdminLoginParam;
import com.example.server.pojo.CcUsernameInfos;
import com.example.server.publics.RespBean;
import com.example.server.service.ICcUsernameInfosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;


@Api(tags = "LoginController")
@RestController
public class LoginController {
    /**
     * @adminService
     */
    @Autowired
    private ICcUsernameInfosService ccUsernameInfosService;


    @ApiOperation(value = "登陆之返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        RespBean respBean= ccUsernameInfosService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), request);
        System.out.println(respBean.getData());
        return respBean;
    }

    @ApiOperation(value = "获取登陆用户信息")
    @GetMapping("/admin/info")
    public RespBean getAdminInfo(@RequestParam Principal principal){
        if (null==principal){
            return null;
        }
        String username=principal.getName();
        HashMap<String,CcUsernameInfos> hashMap = new HashMap();
        System.out.println("查询用户名："+username);
        CcUsernameInfos ccUsernameInfos=ccUsernameInfosService.getAdminByUserName(username);
        ccUsernameInfos.setPassword(null);
        hashMap.put("yes",ccUsernameInfos);
        System.out.println(ccUsernameInfos);
        return RespBean.sucess("成功",hashMap);


    }

    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.sucess("退出成功");
    }

}
