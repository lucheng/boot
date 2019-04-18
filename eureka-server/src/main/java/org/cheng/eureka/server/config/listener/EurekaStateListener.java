package org.cheng.eureka.server.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;

@Component
@ComponentScan
public class EurekaStateListener {
	private final static Logger logger = LoggerFactory.getLogger(EurekaStateListener.class);

	@EventListener(condition = "#event.replication==false")
	public void listen(EurekaInstanceCanceledEvent event) {
		String msg = "服务" + event.getAppName() + "\n" + event.getServerId() + "已下线";
		logger.info(msg);
		this.send(msg);
	}

	@EventListener(condition = "#event.replication==false")
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		String msg = "服务" + instanceInfo.getAppName() + "\n" + instanceInfo.getHostName() + ":" + instanceInfo.getPort()
				+ " \nip: " + instanceInfo.getIPAddr() + "进行注册";
		logger.info(msg);
		this.send(msg);
	}

	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		logger.info("服务{}进行续约", event.getServerId() + "  " + event.getAppName());
	}

	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {
		logger.info("注册中心启动,{}", System.currentTimeMillis());
	}

	@EventListener
	public void listen(EurekaServerStartedEvent event) {
		logger.info("注册中心服务端启动,{}", System.currentTimeMillis());
	}

	private void send(String msg) {
	}
}
