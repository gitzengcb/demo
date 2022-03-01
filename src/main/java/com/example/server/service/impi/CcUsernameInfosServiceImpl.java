package com.example.server.service.impi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.config.security.JwtTokenUtil;
import com.example.server.mapper.CcUsernameInfosMapper;
import com.example.server.pojo.CcUsernameInfos;
import com.example.server.publics.RespBean;
import com.example.server.service.ICcUsernameInfosService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengchengbing
 * @since 2021-11-30
 */
@Service
public class CcUsernameInfosServiceImpl extends ServiceImpl<CcUsernameInfosMapper, CcUsernameInfos> implements ICcUsernameInfosService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    @Autowired(required = false)
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired(required = false)
    private CcUsernameInfosMapper ccUsernameInfosMapper;
    @Autowired(required = false)
    private RedisTemplate<String,Override> redisTemplate;

    /**
     * 登陆之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        //登陆
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //加密判断方法
//        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
//            return RespBean.error("用户名或密码错误");
//        }

        //非加密判断
        if (StringUtils.isNotEmpty(password)){
            if (null == userDetails || !password.equals(userDetails.getPassword())) {
                return RespBean.error("用户名或密码错误");
            }
        }else {
            return RespBean.error("密码不能为空");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员");

        }

        //更新security登陆用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.sucess("登陆成功",tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public CcUsernameInfos getAdminByUserName(String username) {
        return ccUsernameInfosMapper.selectOne(new QueryWrapper<CcUsernameInfos>().eq("username", username).eq("enabled", true));
    }

//    @Override
//    public List<CcUsernameInfos> getAdminByUserName(){
//        Integer id = ((CcUsernameInfos) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//        ValueOperations<String, Override> valueOperations = redisTemplate.opsForValue();
//        List<CcUsernameInfos> list = (List<CcUsernameInfos>) valueOperations.get("menu_" + id);
//        if (CollectionUtils.isEmpty(list)){
//            list=CcUsernameInfosMapper.getAdminByUserName(id);
//
//        }
//    }

}
