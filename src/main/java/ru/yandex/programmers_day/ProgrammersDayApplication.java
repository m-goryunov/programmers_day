package ru.yandex.programmers_day;

public class ProgrammersDayApplication {

	public static void main(String[] args) throws Exception {

		Client client = new Client("http://ya.praktikum.fvds.ru:8080/dev-day");
		client.sendDecodedString();
	}
}
