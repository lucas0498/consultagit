package consult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorConsultGitHubException;
import models.User;
import models.UserGitHub;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GitHubConsult {
    List<User> listUsers = new ArrayList<>();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    public void ConsultUser() throws IOException, InterruptedException {
        var reading = new Scanner(System.in);
        String name = "";
        while (!name.equalsIgnoreCase("sair")) {
            System.out.println("Digite o usuário que deseja consultar: ");
            name = reading.nextLine();
            if (name.equalsIgnoreCase("sair")) {
                break;
            }
        try {
            String url = "https://api.github.com/users/" + name;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url.replaceAll("\\s+", "-")))
                    .header("Accept", "application/vnd.github.v3+json")
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new ErrorConsultGitHubException("Usuário não encontrado");
            }
            String json = response.body();
            UserGitHub userGitHub = gson.fromJson(json, UserGitHub.class);
            User user1 = new User(userGitHub);
            listUsers.add(user1);
            System.out.println(user1);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ops… Ocorreu um erro durante uma consulta da API do GitHub");
            e.printStackTrace();
        } catch (ErrorConsultGitHubException e) {
            System.out.println(e.getMessage());
            }
        }
    }
    public void SalvarArquivo() throws IOException {
        System.out.println(listUsers);
        FileWriter writing = new FileWriter("consult.json");
        writing.write(gson.toJson(listUsers));
        writing.close();
        System.out.println("Arquivo salvo com sucesso!");
    }
}
