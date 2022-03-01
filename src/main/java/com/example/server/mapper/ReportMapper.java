package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Report;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2021-12-28
 */
public interface ReportMapper extends BaseMapper<Report> {
    int insert(Report report);
    List<Report> selectByMap(Map map);
}
