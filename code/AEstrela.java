import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AEstrela {
    public static List<Vertice> estrela(Grafo grafo, Vertice origem, Vertice destino) {
        Map<Vertice, Double> custoG = new HashMap<>();
        Map<Vertice, Double> custoH = new HashMap<>();
        Map<Vertice, Vertice> pai = new HashMap<>();

        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(
                v -> custoG.getOrDefault(v, Double.MAX_VALUE) + custoH.getOrDefault(v, Double.MAX_VALUE)));
        Set<Vertice> visitados = new HashSet<>();

        custoG.put(origem, 0.0);
        custoH.put(origem, estimativaHeuristica(grafo, origem, destino, custoG));
        filaPrioridade.offer(origem);

        while (!filaPrioridade.isEmpty()) {
            Vertice atual = filaPrioridade.poll();

            if (atual.equals(destino)) {
                return reconstruirCaminho(pai, destino);
            }

            visitados.add(atual);

            for (Aresta aresta : grafo.getArestas(atual)) {
                Vertice vizinho = aresta.getDestino();
                double custoAtualParaVizinho = custoG.getOrDefault(atual, 0.0) + aresta.getPeso();

                if (!visitados.contains(vizinho)
                        && custoAtualParaVizinho < custoG.getOrDefault(vizinho, Double.MAX_VALUE)) {
                    pai.put(vizinho, atual);
                    custoG.put(vizinho, custoAtualParaVizinho);
                    custoH.put(vizinho, estimativaHeuristica(grafo, vizinho, destino, custoG));
                    filaPrioridade.offer(vizinho);
                }
            }
        }

        return null;
    }

    private static double estimativaHeuristica(Grafo grafo, Vertice origem, Vertice destino,
            Map<Vertice, Double> custoG) {
        double custoAcumulado = custoG.getOrDefault(origem, 0.0);
        double pesoVertice = origem.getPeso();

        return custoAcumulado + pesoVertice + estimativaHeuristicaArestas(grafo, origem, destino);
    }

    private static double estimativaHeuristicaArestas(Grafo grafo, Vertice origem, Vertice destino) {
        List<Aresta> arestas = grafo.getArestas(origem);
        double somaPesosArestas = arestas.stream().mapToDouble(Aresta::getPeso).sum();

        return somaPesosArestas;
    }

    private static List<Vertice> reconstruirCaminho(Map<Vertice, Vertice> pai, Vertice destino) {
        List<Vertice> caminho = new ArrayList<>();
        Vertice atual = destino;

        while (pai.containsKey(atual)) {
            caminho.add(atual);
            atual = pai.get(atual);
        }

        Collections.reverse(caminho);
        return caminho;
    }
}