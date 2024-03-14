package main;

import consult.GitHubConsult;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GitHubConsult consult = new GitHubConsult();
        consult.ConsultUser();
        consult.SalvarArquivo();
    }
}
