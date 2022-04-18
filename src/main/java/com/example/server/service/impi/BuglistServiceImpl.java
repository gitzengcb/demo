package com.example.server.service.impi;

import com.example.server.pojo.Buglist;
import com.example.server.mapper.BuglistMapper;
import com.example.server.service.IBuglistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
@Service
public class BuglistServiceImpl extends ServiceImpl<BuglistMapper, Buglist> implements IBuglistService {

}
