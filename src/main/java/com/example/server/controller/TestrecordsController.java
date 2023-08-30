package com.example.server.controller;


import com.example.server.pojo.*;
import com.example.server.publics.RespBean;
import com.example.server.service.ITestrecordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengchengbing
 * @since 2023-06-02
 */
@RestController
//@RequestMapping("/testrecords")
public class TestrecordsController {

    @Autowired
    ITestrecordsService testrecordsService;
    private static Logger logger = LoggerFactory.getLogger(TestrecordsController.class);
   //插入测试结果
    public void assertresultinsert(String print, Cases cases, variable variablelist, Report report){
        Testrecords testrecords = variablelist.getTestrecords();
        testrecords.setCaseid(cases.getId());
        testrecords.setSceneid(cases.getSceneid());
        testrecords.setPerformtasksid(cases.getPerformtasksid());
        testrecords.setCreatetime(LocalDateTime.now());
        testrecords.setAssertionresults(cases.getAssertionresults());
        testrecords.setReturnresults(print);
        testrecords.setReportid(report.getId());
        //插入报告记录
        testrecordsService.insert(testrecords);

    }

    public List<Testrecords> selectreport(Integer id){
        List<Testrecords> testrecordslist=testrecordsService.selectreport(id);
        return testrecordslist;
    }

    @GetMapping("/selectreport")
    public RespBean selectreports(@RequestBody Performtasks performtasks) {
        if (performtasks.getId().toString().isEmpty()) {
            return RespBean.error("");
        }
        return RespBean.sucess("查询成功");
    }



}
