package ru.yandex.programmers_day;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    //private final String API_TOKEN;
    private final String url;


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
        URI url1 = URI.create(url + "/register");

/*        String json = "{\"name\": \"Джуны на галере\",\n" +
                " \"gitHubUrl\":\"https://github.com/m-goryunov/programmers_day\",\n" +
                " \"participants\": [{\"email\": \"goryunov93@yandex.ru\", \"cohort\": \"java_18\", \"firstName\": \"Maxim\", \"lastName\":\"Goryunov\"},\n" +
                "\t\t\t\t\t\t\t\t\t{\"email\": \"mrfreemen555@gmail.com\", \"cohort\": \"java_25\", \"firstName\": \"Nikita\", \"lastName\":\"Anokhin\"},\n" +
                "\t\t\t\t\t\t\t\t\t{\"email\": \"schishckin.dennis@yandex.ru\", \"cohort\": \"java_27\", \"firstName\": \"Denis\", \"lastName\":\"Shishkin\"}\n" +
                "\t\t\t\t\t\t\t\t ]\n" +
                "}";*/

        String json = "{\"name\": \"тест123\",\n" +
                " \"gitHubUrl\":\"https://github.com/m-goryunsadsadov/progrfdhsfg\",\n" +
                " \"participants\": [{\"email\": \"goryunovfdsfsdf93@yandex.ru\", \"cohort\": \"java_40\", \"firstName\": \"Maxdsaim\", \"lastName\":\"Gorydasdunov\"},\n" +
                "\t\t\t\t\t\t\t\t\t{\"email\": \"mrfreemedsadn555@gmail.com\", \"cohort\": \"java_1\", \"firstName\": \"Nikdasdita\", \"lastName\":\"Anokdasdhin\"},\n" +
                "\t\t\t\t\t\t\t\t\t{\"email\": \"schishdsadasckin.dennis@yandex.ru\", \"cohort\": \"java_56\", \"firstName\": \"Densdadis\", \"lastName\":\"Shidasdshkin\"}\n" +
                "\t\t\t\t\t\t\t\t ]\n" +
                "}";

        try {
            final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(url1)
                    .POST(body)
                    .header("MAIN_ANSWER", "42")
                    .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
/*            if (response.statusCode() != 200) {
                throw new RuntimeException("Ошибка при загрузке данных на сервер. Код: " + response.statusCode());
            }*/

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







