package com.example.server.service;

import com.example.server.pojo.Performtasks;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-12
 */
public interface IPerformtasksService extends IService<Performtasks> {

    int inserttasks(Performtasks performtasks);

    int updatetasks(Performtasks performtasks);

    List<Performtasks> selecttasks();

    int deletetasks(Performtasks performtasks);

    String selectscenegroupid(Integer id);
}
