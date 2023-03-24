import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HtmlAnalyzer {

    public static void main(String[] args)
    {
        String url = args[0];
        String resposta = lerHTML(url);
        System.out.println(resposta);

    }
    public static String lerHTML(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        }catch(Exception e) {
            throw new RuntimeException("Erro ao ler o conteúdo da URL: " + e.getMessage());
        }
    }
}