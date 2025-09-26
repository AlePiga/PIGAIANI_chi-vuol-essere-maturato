import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        ApiClient client = new ApiClient();
        client.fetchQuestions(1, 12, "multiple", "easy");
    }
}
