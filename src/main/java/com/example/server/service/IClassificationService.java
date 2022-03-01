package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Classification;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-07
 */
public interface IClassificationService extends IService<Classification> {
    int insertClass(Classification classification);

    int updateClass(Classification classification);

    int deleteClass(Classification classification);

    List<Classification> selectClass();
}
