package com.yingjun.ssm.core.goods.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yingjun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OrderDaoTest {

   // @Autowired
    //private OrderDao orderDao;

    @Test
    public void test() throws Exception {
    	System.out.println("按任意键退出");
		System.in.read();
    }
}