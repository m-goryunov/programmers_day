package ru.yandex.programmers_day;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Client {

    private final String url;

    private final HttpClient client = HttpClient.newHttpClient();

    public Client(String url) {
        this.url = url;
    }

    public void sendDecodedString() throws IOException, InterruptedException {

        String json = "{\"decoded\": \"" + getAndDecodeString() + "\"}";

        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .uri(URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/task/2"))
                .header("AUTH_TOKEN", "e4dfc14a-9afb-4867-94d2-29351cc15431")
                .header("content-type", "application/json")
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);

        System.out.println("Код ответа: " + response.statusCode());
        System.out.println("Тело ответа: " + response.body());
    }

    private String getAndDecodeString() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/task/2"))
                .header("AUTH_TOKEN", "e4dfc14a-9afb-4867-94d2-29351cc15431")
                .header("content-type", "application/json")
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);

        Document doc = Jsoup.parse(response.body());
        Element codeElement = doc.select("code#message").first();
        String result = codeElement.text();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        String encoded = rootNode.get("encoded").asText();
        int offset = Integer.parseInt(rootNode.get("offset").asText());
        return decode(encoded, offset);
    }

    private String decode(String message, int offset) {
        offset = offset % 26;
        final byte spaceCode = (byte) 32;
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == spaceCode) {
                continue;
            }
            if (bytes[i] - (byte) offset < 65) {
                bytes[i] = (byte) (91 - (offset - (bytes[i] - 65)));
            } else {
                bytes[i] = (byte) (bytes[i] - (byte) offset);
            }
        }
        return new String(bytes, StandardCharsets.UTF_8);
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
