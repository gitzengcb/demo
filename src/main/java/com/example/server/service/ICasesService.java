package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Cases;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-11
 */
public interface ICasesService extends IService<Cases> {

    int insertcase(Cases cases);

    int updatecase(Cases cases);

    List<Cases> selectcase(Cases cases);

    int deletecase(Cases cases);

    List<Cases> selectgroupcase(List<Integer> list);

    List<Cases> selectvaguecase(Cases cases);

    Cases casestart(Integer id);
}
