package com.example.server.service;

import com.example.server.pojo.Buglist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
public interface IBuglistService extends IService<Buglist> {

    int insert(Buglist buglist);

    void buglistupdate(Buglist buglist);

    List<Buglist> select(Map map);
}
