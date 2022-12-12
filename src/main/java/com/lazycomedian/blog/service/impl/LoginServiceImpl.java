package com.lazycomedian.blog.service.impl;

import com.lazycomedian.blog.dto.LoginDTO;
import com.lazycomedian.blog.dto.UserDetailDTO;
import com.lazycomedian.blog.exception.BizException;
import com.lazycomedian.blog.service.LoginService;
import com.lazycomedian.blog.service.RedisService;
import com.lazycomedian.blog.utils.JwtUtils;
import com.lazycomedian.blog.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户登录
 *
 * @author lazyComedian
 * @date 2022/12/03 17:40
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    @Override
    public LoginDTO login(LoginVO user) {

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(token);
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }

        if (Objects.isNull((authenticate))) {
            throw new BizException("用户名或密码错误！");
        }

        UserDetailDTO userDetail = (UserDetailDTO) (authenticate.getPrincipal());

        final String userId = userDetail.getUser().getId().toString();

        final String jwt = JwtUtils.createJWT(userId);

        LoginDTO result = new LoginDTO();
        result.setToken(jwt);

        // 系统用户相关所有信息存入redis
        redisService.set("login:" + userId, userDetail);

        return result;
    }

    @Override
    public Boolean logout() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailDTO user = (UserDetailDTO) authentication.getPrincipal();

        final Integer userId = user.getUser().getId();

        return redisService.del("login:" + userId);
    }
}
