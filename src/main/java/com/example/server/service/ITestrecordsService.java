package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Testrecords;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2023-06-02
 */
public interface ITestrecordsService extends IService<Testrecords> {

    void insert(Testrecords testrecords);

    List<Testrecords> selectreport(Integer performtasksid);
}
