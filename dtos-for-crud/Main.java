// Main.java
public class Main {
    public static void main(String[] arrayDeArgumentos) {
        UsuarioCreateRequest requisicaoCriacao = new UsuarioCreateRequest("Mateus", "mateus@email.com", "senha123");
        System.out.println("DTO de Criacao: " + requisicaoCriacao);

        UsuarioUpdateRequest requisicaoAtualizacao = new UsuarioUpdateRequest("Mateus Schverz");
        System.out.println("DTO de Atualizacao: " + requisicaoAtualizacao);

        UsuarioResponse respostaUsuario = new UsuarioResponse(1L, requisicaoAtualizacao.nome(), requisicaoCriacao.email());
        System.out.println("DTO de Resposta: " + respostaUsuario);
    }
}

record UsuarioCreateRequest(
    String nome,
    String email,
    String senha
) {}

record UsuarioUpdateRequest(
    String nome
) {}

record UsuarioResponse(
    Long id,
    String nome,
    String email
) {}
