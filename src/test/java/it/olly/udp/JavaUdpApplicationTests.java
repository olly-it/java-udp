package it.olly.udp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.olly.udp.utils.EchoClient;

@SpringBootTest
class JavaUdpApplicationTests {

	@Test
	void contextLoads() throws Exception {
		EchoClient cli = new EchoClient("localhost");
		String msg = "ciao olly";
		String resp = cli.sendEcho(msg, 4445);
		System.out.println("i sent [" + msg + "] and received [" + resp + "]");
	}

}
