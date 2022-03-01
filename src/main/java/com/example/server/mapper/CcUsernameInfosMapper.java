package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.CcUsernameInfos;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Description;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2021-11-30
 */
public interface CcUsernameInfosMapper extends BaseMapper<CcUsernameInfos> {
//    public List<CcUsernameInfos> userdetails(@Param("id") List<Integer> id);
    @Description("通过id查询用户")
    List<CcUsernameInfos> userdetails(@Param("id") List<Integer> id);


}
