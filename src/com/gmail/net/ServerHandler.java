package com.gmail.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {

	public static void localListener() {
		try {
			ServerSocket s = new ServerSocket(8080);
			Socket incoming = s.accept();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
				PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);
				boolean exit = false;
				while (!exit) {
					String line;
					while ((line = br.readLine()) != null) {
						switch (line.trim()) {
						case "system":

							break;
						case "exit":
							exit = true;
							break;
						default:
							out.println("Server answer: " + line);
							break;
						}
					}

				}
			} finally {
				incoming.close();
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("END");
	}

}
