package it.olly.udp.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer extends Thread {

	private DatagramSocket socket;
	private boolean running;
	private byte[] buf = new byte[256];
	private int serverPort;

	public EchoServer(int serverPort) throws SocketException {
		this.serverPort = serverPort;
		socket = new DatagramSocket(serverPort); // 4445
	}

	public void exit() {
		running = false;
		socket.close();
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				System.out.println("SERVER LISTENING FOR COMMANDS AT: " + serverPort);
				socket.receive(packet);
				// get info
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("SERVER RECEIVED - address:" + address + ", port:" + port + " data:" + received
						+ " <- reply the same");

				// reply with the same message but with _ as 1st char
				buf[0] = "_".getBytes()[0];
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
			} catch (Exception e) {
				if (running)
					e.printStackTrace();
			}
		}
	}
}
