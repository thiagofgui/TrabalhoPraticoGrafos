import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    public static Map<Vertice, Double> calcularDistancias(Grafo grafo, Vertice origem) {
        Map<Vertice, Double> distancias = new HashMap<>();
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(
                (v1, v2) -> Double.compare(distancias.get(v1), distancias.get(v2)));

        for (Vertice v : grafo.getVertices()) {
            distancias.put(v, Double.POSITIVE_INFINITY);
            filaPrioridade.add(v);
        }

        distancias.put(origem, 0.0);

        while (!filaPrioridade.isEmpty()) {
            Vertice atual = filaPrioridade.poll();

            for (Aresta aresta : grafo.getArestas(atual)) {
                double novaDistancia = distancias.get(atual) + aresta.getPeso();
                if (aresta.getPeso() < 0) {
                    System.out.println("Há um ciclo de peso negativo, impossibilitando o algoritmo");
                    return new HashMap<>();
                } else if (novaDistancia < distancias.get(aresta.getDestino())) {
                    distancias.put(aresta.getDestino(), novaDistancia);
                    filaPrioridade.remove(aresta.getDestino());
                    filaPrioridade.add(aresta.getDestino());
                }
            }
        }

        return distancias;
    }
}
