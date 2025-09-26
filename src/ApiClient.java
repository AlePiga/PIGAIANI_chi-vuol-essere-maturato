import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private static HttpClient client = HttpClient.newHttpClient();

    public void fetchQuestions(int amount, int category, String type, String difficulty){
        String url = "https://opentdb.com/api.php?amount=" + amount + "&category=" + category + "&difficulty=" + difficulty + "&type=" + type;
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resp = null; // huh?
        try{
            resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e){
            System.out.println("Errore nella richiesta API!!");
        }

        if(resp == null){
            throw new RuntimeException("Nessuna risposta dalla API!!");
        }

        Gson gson = new Gson();
        ApiResponse response = gson.fromJson(resp.body(), ApiResponse.class);
        for(ApiQuestion q : response.results){
            System.out.println(q.question);
            System.out.println("Risposta corretta: " + q.correct_answer);
        }

        return;
    }
}
