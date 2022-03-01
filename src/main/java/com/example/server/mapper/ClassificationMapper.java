package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Classification;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-07
 */
public interface ClassificationMapper extends BaseMapper<Classification> {
    int insert(Classification classification);
    int update(Classification classification);
    int delete(Classification classification);
    List<Classification> select();
}
