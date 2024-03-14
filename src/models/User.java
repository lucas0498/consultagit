package models;

public class User {

    private String name;
    private String login;
    private String id;
    private String html_url;
    private String url;
    private String public_repos;
    private String followers;
    private String following;

    public User(UserGitHub userGitHub){
        this.name = userGitHub.name();
        this.login = userGitHub.login();
        this.id = userGitHub.id();
        this.html_url = userGitHub.html_url();
        this.url = userGitHub.url();
        this.public_repos = userGitHub.public_repos();
        this.followers = userGitHub.followers();
        this.following = userGitHub.following();
    }
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }


    public String getId() {
        return id;
    }

    public String getHtml_url() {
        return html_url;
    }


    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "(Nome: " + name + '\n' +
                "Login: " + login + '\n' +
                "id: " + id + '\n' +
                "Link do Perfil: " + html_url + '\n' +
                "Repositórios Publicos: " + public_repos + '\n' +
                "Seguidores: " + followers + '\n' +
                "Seguindo: " + following + '\n' +
                "Ver mais: " + url +")";
    }
}
