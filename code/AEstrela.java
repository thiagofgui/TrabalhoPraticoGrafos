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
        Map<Vertice, Double> custoG = new HashMap<>(); // custo acumulado do ponto inicial até o nó atual
        Map<Vertice, Double> custoH = new HashMap<>(); // estimativa heurística do custo do nó até o destino final
        Map<Vertice, Vertice> pai = new HashMap<>(); // mapeamento de nós e seus pais no caminho

        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(
                v -> custoG.getOrDefault(v, Double.MAX_VALUE) + custoH.getOrDefault(v, Double.MAX_VALUE)));
        Set<Vertice> visitados = new HashSet<>();

        custoG.put(origem, 0.0);
        custoH.put(origem, estimativaHeuristica(grafo, origem, destino, custoG)); // Heurística inicial para o nó de
                                                                                  // origem
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

        return null; // Não foi encontrado um caminho
    }

    private static double estimativaHeuristica(Grafo grafo, Vertice origem, Vertice destino,
            Map<Vertice, Double> custoG) {
        double custoAcumulado = custoG.getOrDefault(origem, 0.0);
        double pesoVertice = origem.getPeso(); // Peso do vértice de origem

        // Modificação aqui: incluir o peso do vértice no cálculo da heurística
        return custoAcumulado + pesoVertice + estimativaHeuristicaArestas(grafo, origem, destino);
    }

    // Nova função para calcular a heurística baseada nas arestas
    private static double estimativaHeuristicaArestas(Grafo grafo, Vertice origem, Vertice destino) {
        // Exemplo: somar os pesos das arestas entre os vértices de origem e destino
        // Certifique-se de ter um método getPeso() na classe Aresta
        List<Aresta> arestas = grafo.getArestas(origem); // Substitua pelo método correto
        double somaPesosArestas = arestas.stream().mapToDouble(Aresta::getPeso).sum();

        return somaPesosArestas;
    }

    // Reconstrói o caminho a partir do mapa de pais
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