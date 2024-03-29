package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Groups;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
public interface GroupsMapper extends BaseMapper<Groups> {

    List<Groups> select();

    //List selectList(int id);
}
