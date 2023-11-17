package code;

public class Aresta {
    Vertice origem;
    Vertice destino;
    int peso;

    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public String printPesoAresta() {
        return "Peso: " + peso;
    }

    @Override
    public String toString() {
        return "(" + origem + " -> " + destino + ")";
    }
}
