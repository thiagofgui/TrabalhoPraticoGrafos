import java.util.HashMap;
import java.util.Map;

public class FloydWarshall {
    public static Map<Vertice, Map<Vertice, Double>> calcularDistancias(Grafo grafo) {
        Map<Vertice, Map<Vertice, Double>> distancias = new HashMap<>();

        for (Vertice v : grafo.getVertices()) {
            distancias.put(v, new HashMap<>());
            for (Vertice u : grafo.getVertices()) {
                if (v.equals(u)) {
                    distancias.get(v).put(u, 0.0);
                } else {
                    distancias.get(v).put(u, Double.POSITIVE_INFINITY);
                }
            }
        }

        for (Vertice v : grafo.getVertices()) {
            for (Aresta aresta : grafo.getArestas(v)) {
                Vertice u = aresta.getDestino();
                distancias.get(v).put(u, aresta.getPeso());
            }
        }

        for (Vertice k : grafo.getVertices()) {
            for (Vertice i : grafo.getVertices()) {
                for (Vertice j : grafo.getVertices()) {
                    if (distancias.get(i).get(k) + distancias.get(k).get(j) < distancias.get(i).get(j)) {
                        distancias.get(i).put(j, distancias.get(i).get(k) + distancias.get(k).get(j));
                    }
                }
            }
        }

        for (Vertice v : grafo.getVertices()) {
            if (distancias.get(v).get(v) < 0) {
                System.out.println("Há um ciclo de peso negativo, impossibilitando o algoritmo");
                return new HashMap<>();
            }
        }

        return distancias;
    }
}
