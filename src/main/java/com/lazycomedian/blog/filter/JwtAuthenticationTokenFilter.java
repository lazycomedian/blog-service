package com.lazycomedian.blog.filter;

import com.lazycomedian.blog.dto.UserDetailDTO;
import com.lazycomedian.blog.exception.BizException;
import com.lazycomedian.blog.service.RedisService;
import com.lazycomedian.blog.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            // 放行，让后面的过滤器抛异常
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        String userId;
        try {
            final Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new BizException("token不合法！");
        }

        // redis获取用户信息
        UserDetailDTO user = (UserDetailDTO) redisService.get("login:" + userId);
        if (Objects.isNull(user)) {
            throw new BizException("当前用户未登录！");
        }

        // 第三个参数为权限
        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // 存入context
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
