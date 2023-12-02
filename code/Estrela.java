import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Estrela {
    public static Map<Vertice, Double> calcularDistancias(Grafo grafo, Vertice origem, Vertice destino) {
        Map<Vertice, Double> distancias = new HashMap<>();
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        for (Vertice v : grafo.getVertices()) {
            distancias.put(v, Double.POSITIVE_INFINITY);
        }

        distancias.put(origem, 0.0);
        filaPrioridade.add(origem);

        while (!filaPrioridade.isEmpty()) {
            Vertice atual = filaPrioridade.poll();

            if (atual.equals(destino)) {
                break; // Chegamos ao destino, interrompe o algoritmo
            }

            for (Aresta aresta : grafo.getArestas(atual)) {
                Vertice vizinho = aresta.getDestino();
                double custo = aresta.getPeso();
                double novaDistancia = distancias.get(atual) + custo;

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    filaPrioridade.remove(vizinho);
                    filaPrioridade.add(vizinho);
                }
            }
        }

        return distancias;
    }
}