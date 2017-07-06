/**
 * 
 */
package com.yingjun.ssm.mq.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:mq customer
 * @author micalliu
 * @date 2017年7月5日
 */
public class MqMain {
	
	private static ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) throws Exception {
		context = new ClassPathXmlApplicationContext(new String[] { "application.xml" });
		context.start();
		System.out.println("mq customer started success.....");
		System.in.read();
	}
}
