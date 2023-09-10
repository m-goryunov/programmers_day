package ru.yandex.programmers_day;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
/*SpringApplication.run(ProgrammersDayApplication.class, args);*/
public class ProgrammersDayApplication {

	public static void main(String[] args) {
		Gson gson = new Gson();

		Client client = new Client("http://ya.praktikum.fvds.ru:8080/dev-day");
		client.register();
/*		String json = gson.toJson("{\"name\": \"тест123\",\n" +
				" \"gitHubUrl\":\"https://github.com/m-goryunsadsadov/progrfdhsfg\",\n" +
				" \"participants\": [{\"email\": \"goryunovfdsfsdf93@yandex.ru\", \"cohort\": \"java_40\", \"firstName\": \"Maxdsaim\", \"lastName\":\"Gorydasdunov\"},\n" +
				"\t\t\t\t\t\t\t\t\t{\"email\": \"mrfreemedsadn555@gmail.com\", \"cohort\": \"java_1\", \"firstName\": \"Nikdasdita\", \"lastName\":\"Anokdasdhin\"},\n" +
				"\t\t\t\t\t\t\t\t\t{\"email\": \"schishdsadasckin.dennis@yandex.ru\", \"cohort\": \"java_56\", \"firstName\": \"Densdadis\", \"lastName\":\"Shidasdshkin\"}\n" +
				"\t\t\t\t\t\t\t\t ]\n" +
				"}");
		System.out.println(json);
		System.out.println("+++++++++++++++++++++++");
		System.out.println(gson.fromJson(json, String.class));*/

	}

}
