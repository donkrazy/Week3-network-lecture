package com.estsoft.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {

	public static void main(String[] args) {
		
		DatagramSocket socket = null;
		
		try {
			//1. 소켓 생성
			socket = new DatagramSocket( 5050 );
			
			//2. 수신 대기
			consoleLog( "수신대기..." );
			DatagramPacket receivePacket = new DatagramPacket( new byte[ 1024 ], 1024 );
			socket.receive( receivePacket );
			
			//3. 수신
			String message = new String( receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8" );
			consoleLog( "received :" + message );
			
			//4. 전송
			byte[] sendData = message.getBytes( "UTF-8" );
			DatagramPacket sendPacket = new DatagramPacket( sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort() );
			socket.send( sendPacket );
			
		} catch ( IOException e ) {
			consoleLog( "error: " + e );
		} finally {
			if( socket != null && socket.isClosed() == false ) {
				socket.close();
			}
		}
		
	}
	
	public static void consoleLog( String message ) {
		System.out.println( "[UDP Echo Server] " + message );
	}

}
