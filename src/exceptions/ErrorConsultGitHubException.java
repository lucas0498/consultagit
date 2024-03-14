package exceptions;

public class ErrorConsultGitHubException extends RuntimeException {
    private String mensagem;
    public ErrorConsultGitHubException(String mensagem) {

        this.mensagem = mensagem;

    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
