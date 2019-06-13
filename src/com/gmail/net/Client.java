package com.gmail.net;

import java.net.Socket;

public class Client implements Runnable {
	
	private Socket clientSocket;
	private String serverAnswer;
	private Thread thread; 

	
	public Client(Socket clientSocket, String serverAnswer) {
		this.clientSocket = clientSocket;
		this.serverAnswer = serverAnswer;
		thread = new Thread(this);
		thread.start();
	}


	@Override
	public void run() {

	}

}
