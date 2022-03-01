package com.example.server.service;

import com.example.server.pojo.Sceneclassification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-11
 */
public interface ISceneclassificationService extends IService<Sceneclassification> {

    int insertscene(Sceneclassification sceneclassification);

    int updatescene(Sceneclassification sceneclassification);

    int delectscene(Sceneclassification sceneclassification);

     List<Sceneclassification> selectscene(Sceneclassification sceneclassification);

    int addcase(Sceneclassification sceneclassification);

    Sceneclassification scenestart(int parseInt);
}
