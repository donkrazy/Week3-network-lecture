package com.estsoft.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPEchoClient {
	
	private static final String SERVER_IP = "192.168.1.15";
	private static final int SERVER_PORT = 5050;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
			
			String message = "hello world";
			byte[] sendData = message.getBytes( "UTF-8" );
			DatagramPacket sendPacket = 
					new DatagramPacket(  
							sendData, 
							sendData.length, 
							new InetSocketAddress( SERVER_IP, SERVER_PORT ) );
		
			socket.send(  sendPacket );
			
		} catch( IOException ex ) {
			ex.printStackTrace();
		} finally {
			if( socket != null && socket.isClosed() == false ) {
				socket.close();
			}
		}
	}
}
