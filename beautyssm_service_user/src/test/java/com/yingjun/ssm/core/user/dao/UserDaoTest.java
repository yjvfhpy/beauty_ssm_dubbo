package com.yingjun.ssm.core.user.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author micalliu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class UserDaoTest {
	
	@Test
	public void test() throws Exception {
		System.out.println("按任意键退出");
		System.in.read();
	}
}