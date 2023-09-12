package ru.yandex.programmers_day;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    private static final char[] CHARACTERS = "0123456789ABCDEFabcdef".toCharArray();


    public void guessString() throws IOException, InterruptedException {
        int length = 8;
        char[] guess = new char[length];

        int low = 0;
        int high = CHARACTERS.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            guess[0] = CHARACTERS[mid];

            //тут делаем запрос и узнаем знак
            int x = getMoreOrLess(String.valueOf(guess));
            if (x == -1) {
                high = mid - 1;
            } else if (x == 1) {
                low = mid + 1;
            } else {
                return;
            }
        }
    }


    public int getMoreOrLess(String guess) throws IOException, InterruptedException {

        String json = "{\"password\": \"" + guess + "\"}";

        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .uri(URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/task/3"))
                .header("AUTH_TOKEN", "e4dfc14a-9afb-4867-94d2-29351cc15431")
                .header("content-type", "application/json")
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);

        JsonElement jsonElement = JsonParser.parseString(response.body());
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String prompt = jsonObject.get("prompt").getAsString();
        switch (prompt) {
            case "<pass":
                return -1;
            case ">pass":
                return 1;
            case "Хммм,а вы точно помните из каких символов состоит пароль?":
                return 2;
            default:
                System.out.println("Подбор завершен. " + guess);
                System.out.println("Код ответа: " + response.statusCode());
                System.out.println("Тело ответа: " + response.body());
                return 0;
        }
    }
}
