package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.ReportMapper;
import com.example.server.pojo.Report;
import com.example.server.service.IReportService;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengchengbing
 * @since 2023-06-08
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {
    @Resource
    public ReportMapper reportMapper;

    @Description("新增报告")
    @Override
    public void reportinsert(Report report){
        reportMapper.reportinsert(report);
    }

    @Description("查询报告")
    @Override
    public Report selectport(Integer id){
        return reportMapper.select(id);
    }

    @Description("执行中变更报告")
    @Override
    public void update(Report report){
        reportMapper.update(report);
    }



}
