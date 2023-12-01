import java.util.Map;

import exceptions.CicloNegativoException;

public class MainBellmanFord {
    public static void main(String[] args) {
        // Crie um grafo de exemplo
        Grafo grafo = criarGrafoDeExemplo();

        // Escolha um vértice de origem para o algoritmo de Bellman-Ford
        Vertice origem = grafo.getVertices().get(3); // Vértice D como origem

        try {
            // Teste o Bellman-Ford de um vértice específico para todos os outros vértices
            System.out.println("Bellman-Ford a partir de " + origem + ":");
            Map<Vertice, Double> distanciasParaTodos = grafo.bellmanFordParaTodos(origem);
            imprimirDistancias(distanciasParaTodos);

            // Teste o Bellman-Ford de todos os vértices para todos os outros vértices
            for (int i = 0; i < args.length; i++) {
                System.out.println("\nBellman-Ford por vértice:");
                Map<Vertice, Map<Vertice, Double>> distanciasDeTodosParaTodos = grafo.bellmanFordParaTodos();
                imprimirDistanciasDeTodosParaTodos(distanciasDeTodosParaTodos);
            }

        } catch (CicloNegativoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static Grafo criarGrafoDeExemplo() {
        Grafo grafo = new Grafo(true);

        Vertice A = new Vertice("A");
        Vertice B = new Vertice("B");
        Vertice C = new Vertice("C");
        Vertice D = new Vertice("D");

        grafo.adicionarVertice(A);
        grafo.adicionarVertice(B);
        grafo.adicionarVertice(C);
        grafo.adicionarVertice(D);

        Aresta arestaAB = new Aresta(A, B, 1, "A");
        Aresta arestaBC = new Aresta(B, C, -2, "B");
        Aresta arestaCD = new Aresta(C, D, 3, "C");
        Aresta arestaDA = new Aresta(D, A, 4, "D");

        grafo.adicionarAresta(arestaAB);
        grafo.adicionarAresta(arestaBC);
        grafo.adicionarAresta(arestaCD);
        grafo.adicionarAresta(arestaDA);

        return grafo;
    }

    // Método auxiliar para imprimir as distâncias de todos os vértices para todos
    // os outros vértices
    private static void imprimirDistanciasDeTodosParaTodos(Map<Vertice, Map<Vertice, Double>> distancias) {
        for (Map.Entry<Vertice, Map<Vertice, Double>> entry : distancias.entrySet()) {
            System.out.println("Origem: " + entry.getKey());
            Map<Vertice, Double> distanciasIndividuais = entry.getValue();
            for (Map.Entry<Vertice, Double> distanciaEntry : distanciasIndividuais.entrySet()) {
                System.out.println("  " + distanciaEntry.getKey() + ": " + distanciaEntry.getValue());
            }
        }
    }

    // Método auxiliar para imprimir as distâncias calculadas pelo Bellman-Ford
    private static void imprimirDistancias(Map<Vertice, Double> distancias) {
        for (Map.Entry<Vertice, Double> entry : distancias.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
