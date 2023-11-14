package code;

public class Aresta {
    Vertice origem;
    Vertice destiVertice;
    int peso;

    public Aresta(Vertice origem, Vertice destiVertice, int peso) {
        this.origem = origem;
        this.destiVertice = destiVertice;
        this.peso = peso;
    }

    public String printPesoAresta() {
        return "Peso: " + peso;
    }

    @Override
    public String toString() {
        return "(" + origem + " -> " + destiVertice + ")";
    }
}
