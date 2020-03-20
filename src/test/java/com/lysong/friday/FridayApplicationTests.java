package com.lysong.friday;

import com.lysong.friday.dao.PermissionDao;
import com.lysong.friday.dao.RoleDao;
import com.lysong.friday.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FridayApplicationTests {
    @Resource
    RoleDao roleDao;

    @Resource
    PermissionDao permissionDao;

    @Resource
    private UserDao userDao;

    @Test
    public void contextLoads() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

}
