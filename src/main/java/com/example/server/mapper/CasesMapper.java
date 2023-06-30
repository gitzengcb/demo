package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Cases;
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
public interface CasesMapper extends BaseMapper<Cases> {
    int insert(Cases cases);

    int update(Cases cases);

    List<Cases> select(Cases cases);

    int delete(Cases cases);

    List<Cases> groupcase(@Param("id") List<Integer> id);

    List<Cases> vaguecase(Cases cases);

    List<Cases> casestart(@Param("id") List<Integer> id);
}
