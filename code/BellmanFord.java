import java.util.HashMap;
import java.util.Map;

public class BellmanFord {
    public static Map<Vertice, Double> calcularDistancias(Grafo grafo, Vertice origem) {
        int vertices = grafo.quantidadeVertice();
        Map<Vertice, Double> distancias = new HashMap<>();

        for (Vertice vertice : grafo.getVertices()) {
            distancias.put(vertice, Double.POSITIVE_INFINITY);
        }

        distancias.put(origem, 0.0);

        for (int i = 0; i < vertices - 1; i++) {
            for (Vertice u : grafo.getVertices()) {
                for (Aresta aresta : grafo.getArestas(u)) {
                    Vertice v = aresta.getDestino();
                    double pesoAresta = aresta.getPeso();

                    if (distancias.get(u) != Double.POSITIVE_INFINITY
                            && distancias.get(u) + pesoAresta < distancias.get(v)) {
                        distancias.put(v, distancias.get(u) + pesoAresta);
                    }
                }
            }
        }

        for (Vertice u : grafo.getVertices()) {
            for (Aresta aresta : grafo.getArestas(u)) {
                Vertice v = aresta.getDestino();
                double pesoAresta = aresta.getPeso();

                if (distancias.get(u) != Double.POSITIVE_INFINITY
                        && distancias.get(u) + pesoAresta < distancias.get(v)) {
                    System.out.println("Há um ciclo de peso negativo, impossibilitando o algoritmo");
                    return new HashMap<>();
                }
            }
        }

        return distancias;
    }

    public static boolean relaxamento(Aresta aresta, Map<Vertice, Double> distancias) {
        Vertice origem = aresta.getOrigem();
        Vertice destino = aresta.getDestino();
        double pesoAresta = aresta.getPeso();

        if (distancias.get(origem) + pesoAresta < distancias.get(destino)) {
            distancias.put(destino, distancias.get(origem) + pesoAresta);
            return true;
        }

        return false;
    }
}
