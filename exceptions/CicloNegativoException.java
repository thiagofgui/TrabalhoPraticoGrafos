package exceptions;

public class CicloNegativoException extends RuntimeException {
    public CicloNegativoException(String mensagem) {
        super(mensagem);
    }
}
