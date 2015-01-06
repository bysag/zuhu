
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

import com.zuhu.system.pojo.Manager;

@ContextConfiguration(locations = {"classpath*:/applicationContext-test.xml"}) 
@RunWith(SpringJUnit4ClassRunner.class)
public class ManagerServiceTest {
    
    @Resource
    public ManagerService managerService;

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

    //@Test
    public final void addManager() {
        Manager manager = new Manager();
        manager.setCode("yunan.zheng");
        manager.setName("郑允安");
        manager.setCreater("yunan.zheng");
        manager.setCreated(new Date());
        manager.setStatus(1);
        manager.setPassword("123456");
        Long [] roleIds = new Long[3];
        roleIds[0]=1l;
        roleIds[1]=2l;
        roleIds[2]=3l;
        manager.setRoleId(roleIds);
        int result =  managerService.addManager(manager);
        assertEquals(1, result);
    }
    
    
    //@Test
    public final void getManagers() {
        List<Manager> managers = managerService.getManagers(10,0);
        assertEquals(1, managers.size());
    }
    
    //@Test
    public final void getManager() {
        Manager manager = managerService.getManager("yunan.zheng");
        assertEquals("yunan.zheng", manager.getCode());
    }
   
    @Test
    public final void update() {
        Manager manager = new Manager();
        manager.setId(4);
        manager.setName("yunan.zheng-1");
        manager.setStatus(0);
        manager.setCode("yunan.zheng");
        manager.setPassword("8889988");
        Long [] roleIds = new Long[3];
        roleIds[0]=3l;
        roleIds[1]=2l;
        manager.setRoleId(roleIds);
        manager.setUpdater("yunan.zheng");
        assertEquals(1, managerService.updateManager(manager));
    }
    

}
