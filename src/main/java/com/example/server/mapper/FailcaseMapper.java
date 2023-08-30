package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Failcase;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-18
 */
public interface FailcaseMapper extends BaseMapper<Failcase> {
    int insert(Failcase failcase);

    List<Failcase> selectid(Integer reportid);
}
