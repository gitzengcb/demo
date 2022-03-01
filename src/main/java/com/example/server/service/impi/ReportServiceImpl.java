package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.ReportMapper;
import com.example.server.pojo.Report;
import com.example.server.service.IReportService;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengchengbing
 * @since 2021-12-28
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {
    @Resource
    private ReportMapper reportMapper;

    @Description("新增报告数据")
    @Override
    public int insertreport(Report report) {
        return reportMapper.insert(report);
    }


    @Description("查询报告数据")
    @Override
    public List<Report> selectreportlist(Map map) {
        return reportMapper.selectByMap(map);
    }

}
