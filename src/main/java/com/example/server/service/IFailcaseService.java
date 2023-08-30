package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Failcase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-18
 */
public interface IFailcaseService extends IService<Failcase> {

    int insert(Failcase failcase);

    List<Failcase> select(Integer reportid);
}
