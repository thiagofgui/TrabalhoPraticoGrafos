import java.util.Map;

import exceptions.CicloNegativoException;
import exceptions.GrafoNaoConexoException;

public class MainFloydWarshall {
    public static void main(String[] args) {
        Grafo meuGrafo = new Grafo(true);

        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Vertice v3 = new Vertice("C");
        Vertice v4 = new Vertice("D");
        Vertice v5 = new Vertice("E");
        Vertice v6 = new Vertice("F");
        Vertice v7 = new Vertice("G");
        Vertice v8 = new Vertice("H");
        Vertice v9 = new Vertice("I");
        Vertice v10 = new Vertice("J");

        meuGrafo.adicionarVertice(v1);
        meuGrafo.adicionarVertice(v2);
        meuGrafo.adicionarVertice(v3);
        meuGrafo.adicionarVertice(v4);
        meuGrafo.adicionarVertice(v5);
        meuGrafo.adicionarVertice(v6);
        meuGrafo.adicionarVertice(v7);
        meuGrafo.adicionarVertice(v8);
        meuGrafo.adicionarVertice(v9);
        meuGrafo.adicionarVertice(v10);

        meuGrafo.adicionarAresta(new Aresta(v1, v2, 2.0, "A"));
        meuGrafo.adicionarAresta(new Aresta(v2, v3, -1.0, "B"));
        meuGrafo.adicionarAresta(new Aresta(v3, v4, 3.0, "C"));
        meuGrafo.adicionarAresta(new Aresta(v1, v5, 4.0, "D"));
        meuGrafo.adicionarAresta(new Aresta(v5, v6, 1.0, "E"));
        meuGrafo.adicionarAresta(new Aresta(v6, v7, -2.0, "F"));
        meuGrafo.adicionarAresta(new Aresta(v7, v8, 3.0, "G"));
        meuGrafo.adicionarAresta(new Aresta(v8, v9, 2.0, "H"));
        meuGrafo.adicionarAresta(new Aresta(v9, v10, 1.0, "I"));
        meuGrafo.adicionarAresta(new Aresta(v10, v1, 4.0, "J"));

        try {
            Map<Vertice, Map<Vertice, Double>> distancias = meuGrafo.floydWarshall();

            // Verifica ciclos negativos
            for (Vertice v : meuGrafo.getVertices()) {
                if (distancias.get(v).get(v) < 0) {
                    throw new CicloNegativoException("Ciclo Negativo detectado no grafo.");
                }
            }

            // Imprime as distâncias mínimas entre todos os pares de vértices
            for (Vertice origem : distancias.keySet()) {
                System.out.println("Distâncias a partir de " + origem + ":");
                Map<Vertice, Double> distanciasOrigem = distancias.get(origem);
                for (Vertice destino : distanciasOrigem.keySet()) {
                    double distancia = distanciasOrigem.get(destino);
                    System.out.println("  Para " + destino + ": "
                            + (distancia == Double.POSITIVE_INFINITY ? "Infinito" : distancia));
                }
            }

        } catch (GrafoNaoConexoException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
