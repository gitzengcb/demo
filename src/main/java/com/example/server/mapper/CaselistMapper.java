package com.example.server.mapper;

import com.example.server.pojo.Caselist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-11
 */
public interface CaselistMapper extends BaseMapper<Caselist> {
    int insert(Caselist caselist);

    int update(Caselist caselist);

    List<Caselist> select(Caselist caselist);

    int delete(Caselist caselist);

    List<Caselist> groupcase(@Param("id") List<Integer> id);

    List<Caselist> vaguecase(Caselist caselist);

    Caselist casestart(@Param("id") Integer id);
}
