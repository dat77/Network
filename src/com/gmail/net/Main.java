package com.gmail.net;

import java.io.File;
import java.util.Arrays;

import com.gmail.io.FileHandler;

public class Main {

	public static void main(String[] args) {
		/**
		 * 1. Проверить доступность сайтов указанных в отдельном файле.
		 */
		String[] hosts = FileHandler.readFromFileLineByLine(new File("hosts.txt"));
		for (String host : hosts) {
			System.out.println(UrlHandler.hostAvailable(host));
		}

		/**
		 * 3. Напишите программу, которая выведет в файл все ссылки, которые содержатся
		 * в html документе, который будет прислан в результате запроса на произвольный
		 * URL.
		 */
		hosts = UrlHandler.scrapeLinksFromHtml("https://github.com");
		Arrays.stream(hosts).forEach(System.out::println);

		/**
		 * 2. Написать сервер, который будет отправлять пользователю информацию о
		 * системе и номер запроса.
		 */
		
		ServerHandler.localListener();

	}

}
