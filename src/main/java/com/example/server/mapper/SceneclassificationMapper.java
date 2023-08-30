package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Sceneclassification;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-11
 */
public interface SceneclassificationMapper extends BaseMapper<Sceneclassification> {
    int insert(Sceneclassification sceneclassification);

    int update(Sceneclassification sceneclassification);

    int delete(Sceneclassification sceneclassification);

    List<Sceneclassification> select(Sceneclassification sceneclassification);

    int addcase(Sceneclassification sceneclassification);

    Sceneclassification scenestart(int id);
}
