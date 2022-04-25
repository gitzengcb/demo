package com.example.server.mapper;

import com.example.server.pojo.Buglist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
public interface BuglistMapper extends BaseMapper<Buglist> {

    void update(Buglist buglist);

    List<Buglist> select(Map map);
}
