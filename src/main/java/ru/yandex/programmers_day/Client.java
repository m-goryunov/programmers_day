package ru.yandex.programmers_day;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    private final String url;

    private final HttpClient client = HttpClient.newHttpClient();

    public Client(String url) {
        this.url = url;
    }

    public void register() throws IOException, InterruptedException {
        URI url = URI.create(this.url + "/register");

        String json = "{" +
                "  \"name\": \"Джуны на галере\"," +
                "  \"gitHubUrl\": \"https://github.com/m-goryunov/programmers_day\"," +
                "  \"participants\": [" +
                "    {" +
                "      \"email\": \"mrfreemen555@gmail.com\"," +
                "      \"cohort\": \"java_25\"," +
                "      \"firstName\": \"Nikita\"," +
                "      \"lastName\": \"Anokhin\"" +
                "    }," +
                "    {" +
                "      \"email\": \"goryunov93@yandex.ru\"," +
                "      \"cohort\": \"java_16\"," +
                "      \"firstName\": \"Maksim\"," +
                "      \"lastName\": \"Goryunov\"" +
                "    }," +
                "    {" +
                "      \"email\": \"schishckin.dennis@yandex.ru\"," +
                "      \"cohort\": \"java_27\"," +
                "      \"firstName\": \"Denis\"," +
                "      \"lastName\": \"Shishkin\"" +
                "    }," +
                "    {" +
                "      \"email\": \"kira23kira@yandex.ru\"," +
                "      \"cohort\": \"java_33\"," +
                "      \"firstName\": \"Sergey\"," +
                "      \"lastName\": \"Mazurov\"" +
                "    }" +
                "  ]" +
                "}";

        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .uri(url)
                .header("MAIN_ANSWER", "42")
                .header("content-type", "application/json")
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        
        System.out.println("Код ответа: " + response.statusCode());
        System.out.println("Тело ответа: " + response.body());
    }
}







