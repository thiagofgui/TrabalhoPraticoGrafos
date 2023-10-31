package code;

public class Vertice {
    private int valor;
    private int peso;

    public Vertice(int valor) {
        this.valor = valor;
        this.peso = 0;
    }

    public Vertice(int valor, int peso) {
        this.valor = valor;
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return valor + "";
    }
}
