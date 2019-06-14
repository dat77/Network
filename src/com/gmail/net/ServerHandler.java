package com.gmail.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerHandler {

	public static void localListener() {
		try {
			ServerSocket s = new ServerSocket(8080);
			Socket incoming = s.accept();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
					PrintWriter out = new PrintWriter(incoming.getOutputStream(), true)) {
				String line;
				int cx = 0;
				out.println("type help for help");
				while ((line = br.readLine().trim()) != null && !line.equals("exit")) {
					cx++;
					switch (line) {
					case "help":
						out.println("Server answer #" + cx + ":\n\ruse command: system|user|java|exit");
						break;
					case "system":
						out.println("Server answer #" + cx + ":\n\r" + getSystemInfo());
						break;
					case "user":
						out.println("Server answer #" + cx + ":\n\r" + getUserInfo());
						break;
					case "java":
						out.println("Server answer #" + cx + ":\n\r" + getJavaInfo());
						break;
					default:
						out.println("Server answer #" + cx + ": " + line);
						break;
					}
					out.flush();
				}
			} finally {
				incoming.close();
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getSystemInfo() {
		return "os.name: " + System.getProperty("os.name", "<unknown>") + "\n\r" + "os.version: "
				+ System.getProperty("os.version", "<unknown>") + "\n\r" + "os.arch: "
				+ System.getProperty("os.arch", "<unknown>");
	}
	
	private static String getUserInfo() {
		return "user.name: "
				+ System.getProperty("user.name", "<unknown>");
		
	}
	
	private static String getJavaInfo() {
		return "java.version: "
				+ System.getProperty("java.version", "<unknown>");
		
	}
	private static String getStandartResponse() {
		return "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type: text/html\r\n" + "Content-Length: "
				+ "\r\n" + "Connection: close\r\n\r\n";
	}
	
	

}
