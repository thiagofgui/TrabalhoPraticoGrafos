package code;

public class Vertice {
    String id;
    int peso;

    public Vertice(String id, int peso) {
        this.id = id;
        this.peso = peso;
    }

    public String printPesoVertice() {
        return "Peso: " + peso;
    }

    @Override
    public String toString() {
        return id;
    }
}
