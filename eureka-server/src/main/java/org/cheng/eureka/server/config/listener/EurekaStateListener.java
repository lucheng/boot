package org.cheng.eureka.server.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;

@Component
@ComponentScan
public class EurekaStateListener {
	private final static Logger LOGGER = LoggerFactory.getLogger(EurekaStateListener.class);
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	@Value("${spring.mail.properties.set-to}")
	private String mailSetTo;
	
	@Autowired
    private JavaMailSender jms;
	
	@EventListener(condition = "#event.replication==false")
	public void listen(EurekaInstanceCanceledEvent event) {
		String msg = "服务" + event.getAppName() + "\n" + event.getServerId() + "已下线";
		LOGGER.info(msg);
//		this.send(msg);
	}

	@EventListener(condition = "#event.replication==false")
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		String msg = "服务" + instanceInfo.getAppName() + "\n" + instanceInfo.getHostName() + ":" + instanceInfo.getPort()
				+ " \nip: " + instanceInfo.getIPAddr() + "进行注册";
		LOGGER.info(msg);
//		this.send(msg);
	}

	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		LOGGER.info("服务{}进行续约", event.getServerId() + "  " + event.getAppName());
	}

	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {
		LOGGER.info("注册中心启动,{}", System.currentTimeMillis());
	}

	@EventListener
	public void listen(EurekaServerStartedEvent event) {
		LOGGER.info("注册中心服务端启动,{}", System.currentTimeMillis());
	}

	private void send(String msg) {
		// 用于封装邮件信息的实例
		SimpleMailMessage smm = new SimpleMailMessage();
		// 由谁来发送邮件
		smm.setFrom(mailUsername);
		// 邮件主题
		smm.setSubject("eureka-server");
		// 邮件内容
		smm.setText(msg);
		// 接受邮件
		smm.setTo(mailSetTo.split(","));
		try {
			jms.send(smm);
		} catch (Exception e) {
			LOGGER.info(msg + "错误", e);
		}
	}
}
