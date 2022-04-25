package com.example.server.service;

import com.example.server.pojo.Groups;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
public interface IGroupsService extends IService<Groups> {

    void insert(Groups groups);

    List<Groups> select();

   // List selectlistid(int id);
}
