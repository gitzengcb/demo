package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Report;
import org.apache.ibatis.annotations.Options;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2021-12-28
 */
public interface IReportService extends IService<Report> {
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//获取主键id
    int insertreport(Report report);


    List<Report> selectreportlist(Map<String, LocalDateTime> map);
}
