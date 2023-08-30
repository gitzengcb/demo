package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Testrecords;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2023-06-02
 */
public interface TestrecordsMapper extends BaseMapper<Testrecords> {

    List<Testrecords> selectList(Integer id);
}
