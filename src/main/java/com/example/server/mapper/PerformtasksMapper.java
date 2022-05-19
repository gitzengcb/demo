package com.example.server.mapper;

import com.example.server.pojo.Performtasks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-12
 */
public interface PerformtasksMapper extends BaseMapper<Performtasks> {
    int insert(Performtasks performtasks);

    int update(Performtasks performtasks);

    List<Performtasks> select();

    int delete(Performtasks performtasks);

    String selectscenegroup(Integer id);
}
