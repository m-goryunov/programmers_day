package ru.yandex.programmers_day;

//@SpringBootApplication
/*SpringApplication.run(ProgrammersDayApplication.class, args);*/
public class ProgrammersDayApplication {

	public static void main(String[] args) throws Exception {

		Client client = new Client("http://ya.praktikum.fvds.ru:8080/dev-day");
		client.register();

	}
}
