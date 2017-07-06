/**
 * 
 */
package com.yingjun.ssm.starter;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:启动类
 * @author micalliu
 * @date 2017年7月4日
 */
public class UserMain {

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws IOException {
		context = new ClassPathXmlApplicationContext(new String[] { "application.xml" });
		context.start();
		System.out.println("user service started success......");
		System.in.read();
	}
}
