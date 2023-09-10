package ru.yandex.programmers_day;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
/*SpringApplication.run(ProgrammersDayApplication.class, args);*/
public class ProgrammersDayApplication {

	public static void main(String[] args) {

		Client client = new Client("http://ya.praktikum.fvds.ru:8080/dev-day");
		client.register();

	}

}
