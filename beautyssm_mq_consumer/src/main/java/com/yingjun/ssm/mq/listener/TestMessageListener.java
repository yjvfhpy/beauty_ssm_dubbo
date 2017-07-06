/**
 * 
 */
package com.yingjun.ssm.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Description:MQ消费者测试 跨应用
 * @author micalliu
 * @date 2017年7月4日
 */
@Component
public class TestMessageListener implements SessionAwareMessageListener<Message> {

	/* 
	 * 
	 */
	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		 ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
		 System.out.println(msg.getText());
	}
	
}
