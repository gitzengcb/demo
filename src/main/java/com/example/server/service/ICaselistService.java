package com.example.server.service;

import com.example.server.pojo.Caselist;
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
public interface ICaselistService extends IService<Caselist> {

    int insertcase(Caselist caselist);

    int updatecase(Caselist caselist);

    List<Caselist> selectcase(Caselist caselist);

    int deletecase(Caselist caselist);

    List<Caselist> selectgroupcase(List<Integer> list);

    List<Caselist> selectvaguecase(Caselist caselist);

    List<Caselist> casestart(Integer id);
}
