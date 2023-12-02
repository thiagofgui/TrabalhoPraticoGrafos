import java.util.*;

import exceptions.CicloNegativoException;

public class BellmanFord {
    public static Map<Vertice, Double> calcularDistancias(Grafo grafo, Vertice origem) {
        Map<Vertice, Double> distancias = new HashMap<>();

        for (Vertice v : grafo.getVertices()) {
            distancias.put(v, Double.POSITIVE_INFINITY);
        }
        distancias.remove(origem);
        distancias.put(origem, 0.0);

        int quantidadeVertices = grafo.quantidadeVertice();

        for (int i = 1; i <= quantidadeVertices; i++) {
            for (Vertice vertice : grafo.getVertices()) {
                for (Aresta aresta : grafo.getListaAdjacencia().get(vertice)) {
                    relaxamento(aresta, distancias);
                }
            }
        }

        // Verificar ciclos negativos
        for (Vertice vertice : grafo.getVertices()) {
            for (Aresta aresta : grafo.getListaAdjacencia().get(vertice)) {
                if (relaxamento(aresta, distancias)) {
                    // Encontrou ciclo negativo
                    throw new CicloNegativoException("O grafo possui um ciclo negativo.");
                }
            }
        }

        return distancias;
    }

    public static boolean relaxamento(Aresta aresta, Map<Vertice, Double> distancias) {
        Vertice u = aresta.getOrigem();
        Vertice v = aresta.getDestino();
        double pesoAresta = aresta.getPeso();

        if (distancias.get(u) + pesoAresta < distancias.get(v)) {
            distancias.put(v, distancias.get(u) + pesoAresta);
            return true; // Relaxamento ocorreu
        }

        return false; // Relaxamento não ocorreu
    }
}
