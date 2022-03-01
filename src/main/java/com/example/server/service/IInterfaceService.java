package com.example.server.service;

import com.example.server.pojo.Interface;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-10
 */
public interface IInterfaceService extends IService<Interface> {

    int insertinter(Interface inputlist);

    int updateinset(Interface inputlist);

    int delectinter(Interface inputlist);

    List<Interface> selectinter(Interface inputlist);
}
