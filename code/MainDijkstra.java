import java.util.Map;

public class MainDijkstra {
    public static void main(String[] args) {
        // Criação de instância do grafo
        Grafo grafo = new Grafo(false);

        // Adição de vértices
        Vertice A = new Vertice("A");
        Vertice B = new Vertice("B");
        Vertice C = new Vertice("C");
        Vertice D = new Vertice("D");
        grafo.adicionarVertice(A);
        grafo.adicionarVertice(B);
        grafo.adicionarVertice(C);
        grafo.adicionarVertice(D);

        // Adição de arestas com pesos
        grafo.adicionarAresta(new Aresta(A, B, 2.0, "A"));
        grafo.adicionarAresta(new Aresta(A, C, 4.0, "B"));
        grafo.adicionarAresta(new Aresta(B, C, 1.0, "C"));
        grafo.adicionarAresta(new Aresta(B, D, 7.0, "D"));
        grafo.adicionarAresta(new Aresta(C, D, 3.0, "E"));

        // Execução do algoritmo de Dijkstra
        Map<Vertice, Map<Vertice, Double>> distanciasTodosParaTodos = grafo.dijkstraPorVertice();

        // Exibição das distâncias mais curtas de todos para todos
        System.out.println("Distâncias de todos para todos:");
        for (Vertice origem : distanciasTodosParaTodos.keySet()) {
            System.out.println("A partir de " + origem + ":");
            Map<Vertice, Double> distanciasOrigem = distanciasTodosParaTodos.get(origem);
            for (Vertice destino : distanciasOrigem.keySet()) {
                System.out.println(destino + ": " + distanciasOrigem.get(destino));
            }
            System.out.println();
        }

        // Vértice de origem para Dijkstra específico
        Vertice verticeOrigem = A;

        // Execução do algoritmo de Dijkstra de um vértice específico para todos
        Map<Vertice, Double> distanciasParaTodos = grafo.dijkstraParaTodos(verticeOrigem);

        // Exibição das distâncias mais curtas a partir do vértice de origem
        System.out.println("Distâncias a partir do vértice específico = " + verticeOrigem + ":");
        for (Vertice destino : distanciasParaTodos.keySet()) {
            System.out.println(destino + ": " + distanciasParaTodos.get(destino));
        }
    }
}
