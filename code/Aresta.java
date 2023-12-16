public class Aresta {
    Vertice origem;
    Vertice destino;
    double peso;
    String rotulo;

    public Aresta(Vertice origem, Vertice destino, double peso, String rotulo) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.rotulo = rotulo;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public String printPesoAresta() {
        return "Peso: " + peso;
    }

    @Override
    public String toString() {
        return "(" + origem + " -> " + destino + ")";
    }
}
