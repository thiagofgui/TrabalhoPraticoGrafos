package exceptions;

public class GrafoNaoConexoException extends RuntimeException {
    public GrafoNaoConexoException(String mensagem) {
        super(mensagem);
    }
}