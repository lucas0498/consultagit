package apis;

import exceptions.ErrorConsultGitHubException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsult {

    public void ConsultUser(String name) throws IOException, InterruptedException {
        try{
            String username = name;
            String endereco = "https://api.github.com/users/" + name;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco.replaceAll("\\s+", "-")))
                    .header("Accept", "application/vnd.github.v3+json")
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new ErrorConsultGitHubException("User not found.");
            }

            String json = response.body();
            System.out.println(json);
        } catch (IOException | InterruptedException e) {
            System.out.println("Opss… Houve um erro durante a consulta à API do GitHub.");
            e.printStackTrace();
        }
        catch(ErrorConsultGitHubException e){
            System.out.println(e.getMessage());
        }

    }
}
