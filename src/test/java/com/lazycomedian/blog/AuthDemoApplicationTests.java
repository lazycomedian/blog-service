package com.lazycomedian.blog;

import com.lazycomedian.blog.entity.SysAdminEntity;
import com.lazycomedian.blog.mapper.SysAdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class AuthDemoApplicationTests {

    @Autowired
    SysAdminMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void testMapper() {
        final List<SysAdminEntity> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

    @Test
    void testPassword() {
        final String lazycomedian1 = passwordEncoder.encode("admin888");
        final String lazycomedian2 = passwordEncoder.encode("admin888");

        System.out.println(lazycomedian1);
        System.out.println(lazycomedian2);

        final boolean flag1 = passwordEncoder.matches("lazycomedian", lazycomedian1);
        final boolean flag2 = passwordEncoder.matches("lazycomedian", lazycomedian2);

        System.out.println(flag1);
        System.out.println(flag2);

    }
}
