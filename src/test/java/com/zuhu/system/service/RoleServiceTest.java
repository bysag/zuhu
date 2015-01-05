package com.zuhu.system.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zuhu.system.global.GlobalConstant;
import com.zuhu.system.pojo.Role;


@ContextConfiguration(locations = {"classpath*:/applicationContext-test.xml"}) 
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Resource
    private RoleService roleService;

    //@Test
    public final void insert() {
        Role role = new Role();
        role.setCreater("yunan.zheng");
        role.setDescription("Test");
        role.setName("商店管理员");
        role.setStatus(1);
        int insertFlag = roleService.insert(role);
        assertEquals(1, insertFlag);
    }
    //@Test
    public final void update(){
        Role role = new Role();
        role.setId(1);
        role.setUpdated(new Date());
        role.setUpdater("zening.feng");
        role.setDescription("123567");
        role.setStatus(0);
        int updateFlag = roleService.update(role);
        assertEquals(1, updateFlag);
    }

    //@Test
    public final void getRole(){
        Role role = roleService.getRole(1);
        assertEquals(1, role.getId());
    }
    //@Test
    public final void getRoles(){
        List<Role> roles = roleService.getRoles(10, 0);
        assertEquals(3, roles.size());
    }
    @Test
    public final void getRolesByWhere(){
        Role role = new Role();
        role.setName("管理");
        List<Role> rolesByWhere = roleService.getRolesByWhere(role, 10, 0);
        assertEquals(3,rolesByWhere.size());
    }
}
