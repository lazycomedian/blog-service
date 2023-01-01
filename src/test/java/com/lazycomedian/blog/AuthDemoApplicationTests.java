package com.lazycomedian.blog;

import com.lazycomedian.blog.service.SysMenuService;
import com.lazycomedian.blog.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AuthDemoApplicationTests {


    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testMenuList() {
        System.out.println(sysMenuService.findAll());
    }

}


