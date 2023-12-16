import java.util.Map;

import exceptions.GrafoNaoConexoException;

public class MainDijkstra {
    public static void main(String[] args) {
        // Criando um grafo de exemplo
        Grafo grafo = new Grafo(false);

        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");

        Aresta arestaAB = new Aresta(a, b, 1.0, "A");
        Aresta arestaBC = new Aresta(b, c, 2.0, "B");
        Aresta arestaAC = new Aresta(a, c, 4.0, "C");

        grafo.adicionarVertice(a);
        grafo.adicionarVertice(b);
        grafo.adicionarVertice(c);

        grafo.adicionarAresta(arestaAB);
        grafo.adicionarAresta(arestaBC);
        grafo.adicionarAresta(arestaAC);

        // Testando Dijkstra a partir de um vértice específico
        System.out.println("Dijkstra a partir de A:");
        try {
            Map<Vertice, Double> resultadoDijkstra = grafo.dijkstra(a);
            for (Map.Entry<Vertice, Double> entry : resultadoDijkstra.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (GrafoNaoConexoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Testando Dijkstra por vértice
        System.out.println("\nDijkstra por vértice:");
        try {
            Map<Vertice, Map<Vertice, Double>> resultadoDijkstraPorVertice = grafo.dijkstraPorVertice();
            for (Map.Entry<Vertice, Map<Vertice, Double>> entry : resultadoDijkstraPorVertice.entrySet()) {
                System.out.println("Origem: " + entry.getKey());
                Map<Vertice, Double> distancias = entry.getValue();
                for (Map.Entry<Vertice, Double> distanciaEntry : distancias.entrySet()) {
                    System.out.println("  " + distanciaEntry.getKey() + ": " + distanciaEntry.getValue());
                }
            }
        } catch (GrafoNaoConexoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
