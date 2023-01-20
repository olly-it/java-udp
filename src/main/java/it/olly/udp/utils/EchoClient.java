package it.olly.udp.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EchoClient {
	private DatagramSocket socket;
	private InetAddress address;

	private byte[] buf;

	public EchoClient(String host) throws SocketException, UnknownHostException {
		socket = new DatagramSocket();
		address = InetAddress.getByName(host); // "localhost"
	}

	public String sendEcho(String msg, int port) throws IOException {
		buf = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port); // 4445
		System.out.println("CLIENT SEND - host: " + address + ", port: " + port + ", data: " + msg);
		socket.send(packet);
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		return received;
	}

	public void close() {
		socket.close();
	}
}