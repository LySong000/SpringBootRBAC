package com.lysong.friday;

import com.lysong.friday.dao.PermissionDao;
import com.lysong.friday.dao.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FridayApplicationTests {
    @Resource
    RoleDao roleDao;

    @Resource
    PermissionDao permissionDao;

    @Test
    public void contextLoads() {
        System.out.println(permissionDao.findPermissionByRoleId(1));
    }

}
