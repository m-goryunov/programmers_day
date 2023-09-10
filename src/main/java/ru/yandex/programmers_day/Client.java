package ru.yandex.programmers_day;


import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Client {

    //private final String API_TOKEN;
    private final String url;

    Gson gson = new Gson();


    public Client(String url) {
        this.url = url;
        //this.API_TOKEN = getApiToken();
    }


/*    private String getApiToken() {
        HttpClient client = HttpClient.newHttpClient();
        URI url1 = URI.create(url+ "/register");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url1)
                .header("MAIN_ANSWER", "42")
                .POST()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Ошибка при получении токена. Код: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("При обработке возникла ошибка.");
        }
    }*/


/*    public void register(String key, String json) {
        HttpClient client = HttpClient.newHttpClient();
        URI url1 = URI.create(url + "/save/" + key + "/?API_TOKEN=" + API_TOKEN);

        try {
            final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(url1)
                    .POST(body)
                    .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Ошибка при загрузке данных на сервер. Код: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("При обработке возникла ошибка.");
        }


    }*/

    public void register() {
        HttpClient client = HttpClient.newHttpClient();
        URI url1 = URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/register");

        User user1 = User.builder()
                .email("email1@yandex.ru")
                .cohort("java_24")
                .firstName("name1")
                .lastName("lastname1")
                .build();
        User user2 = User.builder()
                .email("email2@yandex.ru")
                .cohort("java_25")
                .firstName("name2")
                .lastName("lastname2")
                .build();
        User user3 = User.builder()
                .email("email3@yandex.ru")
                .cohort("java_26")
                .firstName("name3")
                .lastName("lastname2")
                .build();


        List<User> users = List.of(user1,user2,user3);

        Register register = Register.builder()
                .name("Тест321")
                .gitHubUrl("https://github.com/m-goryunsadsadov/progrfddsadf")
                .participants(users)
                .build();

        String json = gson.toJson(register);
        System.out.println(json);

/*        String json = gson.toJson("{\"name\": \"тест123\",\n" +
                " \"gitHubUrl\":\"https://github.com/m-goryunsadsadov/progrfdhsfg\",\n" +
                " \"participants\": [{\"email\": \"goryunovfdsfsdf93@yandex.ru\", \"cohort\": \"java_40\", \"firstName\": \"Maxdsaim\", \"lastName\":\"Gorydasdunov\"},\n" +
                "{\"email\": \"mrfreemedsadn555@gmail.com\", \"cohort\": \"java_1\", \"firstName\": \"Nikdasdita\", \"lastName\":\"Anokdasdhin\"},\n" +
                "{\"email\": \"schishdsadasckin.dennis@yandex.ru\", \"cohort\": \"java_56\", \"firstName\": \"Densdadis\", \"lastName\":\"Shidasdshkin\"}\n" +
                "]\n" +
                "}");*/


        try {
            final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(url1)
                    .POST(body)
                    .header("MAIN_ANSWER", "42")
                    .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Ошибка при загрузке данных на сервер. Код: " + response.statusCode() + response.body());
            }

            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("При обработке возникла ошибка.");
        }


    }

/*    public String load(String key) {
        HttpClient client = HttpClient.newHttpClient();
        URI url1 = URI.create(url + "/load/" + key + "/?API_TOKEN=" + API_TOKEN);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url1)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Ошибка при загрузке данных из сервера. Код: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("При обработке возникла ошибка.");
        }
    }*/
}







