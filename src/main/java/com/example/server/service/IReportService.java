package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Report;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2023-06-08
 */
public interface IReportService extends IService<Report> {

    void reportinsert(Report report);

    Report selectport(Integer id);

    void update(Report report);
}
