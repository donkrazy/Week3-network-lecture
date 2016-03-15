package com.estsoft.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPEchoClient {
	private static final String SERVER_IP = "192.168.56.1";
	private static final int SERVER_PORT = 5050;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket();

			//1. 전송
			String message = "Hello World";
			byte[] sendData = message.getBytes( "UTF-8" );
			
			DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, new InetSocketAddress( SERVER_IP, SERVER_PORT ) );
			socket.send( sendPacket );
			
			//2. 수신 대기
			consoleLog( "수신 대기..." );
			DatagramPacket receivePacket = new DatagramPacket( new byte[ 1024 ], 1024 );
			socket.receive( receivePacket );
			
			//3. 수신
			message = new String( receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8" );
			consoleLog( "received: " + message );
			
		} catch ( IOException ex ) {
			consoleLog( "error: " + ex );
		} finally {
			if( socket != null && socket.isClosed() == false ) {
				socket.close();
			}
		}
	}
	
	public static void consoleLog( String message ) {
		System.out.println( "[UDP Echo Client] " + message );
	}
}
