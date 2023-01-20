package it.olly.udp.service;

import org.springframework.stereotype.Service;

import it.olly.udp.utils.EchoServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class UDPServerService {
	private EchoServer es;

	@PostConstruct
	public void postInit() throws Exception {
		es = new EchoServer(4445);
		es.start();
	}

	@PreDestroy
	public void beforDestroy() {
		System.out.println("shutting down socket");
		es.exit();
	}
}
