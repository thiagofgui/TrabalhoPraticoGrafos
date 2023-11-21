package code;

public class Vertice {
    String id;
    int peso;

    public Vertice(String id, int peso) {
        this.id = id;
        this.peso = peso;
    }

    public Vertice(String id) {
        this.id = id;
        this.peso = 0;
    }

    public String printPesoVertice() {
        return "Peso: " + peso;
    }

    @Override
    public String toString() {
        return id;
    }
}
