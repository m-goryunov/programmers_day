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
import java.util.HashMap;
import java.util.Map;

public class Client4 {

    private final HttpClient client = HttpClient.newHttpClient();

    public String decodeMessage(String encoded) {
        Map<Character, Character> dictionary = new HashMap<>();
        dictionary.put('À','А');
        dictionary.put('Á', 'Б');
        dictionary.put('Â', 'В');
        dictionary.put('Ã', 'Г');
        dictionary.put('Ä', 'Д');
        dictionary.put('Å', 'Е');
        dictionary.put('Æ', 'Ж');
        dictionary.put('Ç', 'З');
        dictionary.put('È', 'И');
        dictionary.put('É', 'Й');
        dictionary.put('Ê', 'К');
        dictionary.put('Ë', 'Л');
        dictionary.put('Ì', 'М');
        dictionary.put('Í', 'Н');
        dictionary.put('Î', 'О');
        dictionary.put('Ï', 'П');
        dictionary.put('Ð', 'Р');
        dictionary.put('Ñ', 'С');
        dictionary.put('Ò', 'Т');
        dictionary.put('Ó', 'У');
        dictionary.put('Ô', 'Ф');
        dictionary.put('Õ', 'Х');
        dictionary.put('Ö', 'Ц');
        dictionary.put('×', 'Ч');
        dictionary.put('Ø', 'Ш');
        dictionary.put('Ù', 'Щ');
        dictionary.put('Ú', 'Ъ');
        dictionary.put('Û', 'Ы');
        dictionary.put('Ü', 'Ь');
        dictionary.put('Ý', 'Э');
        dictionary.put('Þ', 'Ю');
        dictionary.put('ß', 'Я');
        dictionary.put('à', 'а');
        dictionary.put('á', 'б');
        dictionary.put('â', 'в');
        dictionary.put('ã', 'г');
        dictionary.put('ä', 'д');
        dictionary.put('å', 'е');
        dictionary.put('æ', 'ж');
        dictionary.put('ç', 'з');
        dictionary.put('è', 'и');
        dictionary.put('é', 'й');
        dictionary.put('ê', 'к');
        dictionary.put('ë', 'л');
        dictionary.put('ì', 'м');
        dictionary.put('í', 'н');
        dictionary.put('î', 'о');
        dictionary.put('ï', 'п');
        dictionary.put('ð', 'р');
        dictionary.put('ñ', 'с');
        dictionary.put('ò', 'т');
        dictionary.put('ó', 'у');
        dictionary.put('ô', 'ф');
        dictionary.put('õ', 'х');
        dictionary.put('ö', 'ц');
        dictionary.put('÷', 'ч');
        dictionary.put('ø', 'ш');
        dictionary.put('ù', 'щ');
        dictionary.put('ú', 'ъ');
        dictionary.put('û', 'ы');
        dictionary.put('ü', 'ь');
        dictionary.put('ý', 'э');
        dictionary.put('þ', 'ю');
        dictionary.put('ÿ', 'я');
        dictionary.put(' ', ' ');

        for (int i = 0; i < encoded.length(); i++) {
            encoded = encoded.replaceFirst(String.valueOf(encoded.charAt(i)), String.valueOf(dictionary.get(encoded.charAt(i))));
        }
        return encoded;
    }

    private String getCodedString() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/task/4"))
                .header("AUTH_TOKEN", "e4dfc14a-9afb-4867-94d2-29351cc15431")
                .header("content-type", "application/json")
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);

        Document doc = Jsoup.parse(response.body());
        Element codeElement = doc.select("code#congratulation").first(); //
        String result = codeElement.text();

        return decodeMessage(result);
    }

    public void sendDecodedString() throws IOException, InterruptedException {

        String json = "{\"congratulation\": \"" + getCodedString() + "\"}"; //надо поменять

        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        System.out.println("eto body");
        System.out.println(body);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .uri(URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/task/4"))
                .header("AUTH_TOKEN", "e4dfc14a-9afb-4867-94d2-29351cc15431")
                .header("content-type", "application/json")
                .build();

        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);

        System.out.println("Код ответа: " + response.statusCode());
        System.out.println("Тело ответа: " + response.body());
    }


}
