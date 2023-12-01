import java.util.Map;

import exceptions.CicloNegativoException;
import exceptions.GrafoNaoConexoException;

public class MainBellmanFord {
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

        // Testando Bellman-Ford a partir de um vértice específico
        System.out.println("Bellman-Ford a partir de A para todos os vértices:");
        try {
            Map<Vertice, Double> resultadoBellmanFord = grafo.bellmanFordParaTodos(a);
            for (Map.Entry<Vertice, Double> entry : resultadoBellmanFord.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (GrafoNaoConexoException | CicloNegativoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println();
        // Testando Bellman-Ford por vértice
        System.out.println("Bellman-Ford por vértice:");

        try {
            for (Vertice origem : grafo.getVertices()) {
                Map<Vertice, Double> resultadoBellmanFordPorVertice = grafo.bellmanFordParaTodos(origem);
                System.out.println("Origem: " + origem);
                for (Map.Entry<Vertice, Double> entry : resultadoBellmanFordPorVertice.entrySet()) {
                    System.out.println("  " + entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (GrafoNaoConexoException | CicloNegativoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
