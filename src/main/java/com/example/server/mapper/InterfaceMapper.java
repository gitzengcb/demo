package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Interface;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-10
 */
public interface InterfaceMapper extends BaseMapper<Interface> {
    int insert(Interface inputlist);

    int update(Interface inputlist);

    int delete(Interface inputlist);

    List<Interface> select(Interface inputlist);
}
