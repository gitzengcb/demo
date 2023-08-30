package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2023-06-08
 */
public interface ReportMapper extends BaseMapper<Report> {

    Report select(@Param("id") Integer id);

    void reportinsert(Report report);

    void update(Report report);
}
