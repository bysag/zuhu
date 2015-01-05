/**
 * 
 */
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
import com.zuhu.system.pojo.Func;


/**
 * @author yunan.zheng
 *
 */
@ContextConfiguration(locations = {"classpath*:/applicationContext-test.xml"}) 
@RunWith(SpringJUnit4ClassRunner.class)
public class FuncServiceTest {
    
    @Resource
    private FuncService funcService;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    //@Test
    public void insert(){
        Func func = new Func();
        Date date = new Date();
        func.setCreated(date);
        func.setCreater(GlobalConstant.FUNC_SYSTEM_ADMIN);
        func.setDescription("系统功能");
        func.setName("系统功能");
        func.setSort(0);
        func.setParentId(-1);
        func.setType(1);
        func.setUpdated(date);
        func.setUri("/system/list");
        int size = funcService.insert(func);
        assertEquals(1, size);
    }
    
  
    //@Test
    public void getFunc(){
        long funcId = 1l;
        Func func = funcService.getFunc(funcId);
        assertNotNull(func);
        assertEquals("系统功能", func.getName());
    }

    //@Test
    public void update(){
        Func func = new Func();
        func.setId(1l);
        func.setSort(5);
        int size = funcService.update(func);
        assertEquals(1, size);
    }
    
    @Test
    public void selectAllBylimitOffset(){
         List<Func> funcs = funcService.getFuncs(2, 0);
         assertEquals(2, funcs.size());
    }
    
    //@Test
    public void selectAllByWhere(){
        Func func  = new Func();
        func.setName("系统");
        func.setId(1l);
        List<Func> funcsByWhere = funcService.getFuncsByWhere(func, 10, 0);
        assertEquals(1, funcsByWhere.size());
    }
    
    

}
