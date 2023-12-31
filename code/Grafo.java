import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import exceptions.GrafoNaoConexoException;

public class Grafo {
    private Map<Vertice, List<Aresta>> listaAdjacencia;
    private boolean direcionado;
    private int quantidadeVertice = 0;
    private int quantidadeAresta = 0;

    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
        this.listaAdjacencia = new HashMap<>();
    }

    public Grafo() {
        this.direcionado = true;
        this.listaAdjacencia = new HashMap<>();
    }

    public boolean verificaGrafoVazio() {
        return this.quantidadeVertice == 0;
    }

    public boolean verificaGrafoCompleto() {
        int tamanho = listaAdjacencia.size();

        if (this.direcionado) {
            int arestasEsperadas = tamanho * (tamanho - 1);
            return this.quantidadeAresta == arestasEsperadas;
        } else {
            int arestasEsperadas = tamanho * (tamanho - 1) / 2;
            return this.quantidadeAresta == arestasEsperadas;
        }

    }

    public void adicionarVertice(Vertice Vertice) {
        listaAdjacencia.put(Vertice, new ArrayList<>());
        this.quantidadeVertice++;
    }

    public void adicionarAresta(Aresta aresta) {
        listaAdjacencia.get(aresta.origem).add(aresta);

        if (!direcionado) {
            listaAdjacencia.get(aresta.destino)
                    .add(new Aresta(aresta.destino, aresta.origem, aresta.peso, aresta.rotulo));
        }
        this.quantidadeAresta++;
    }

    public List<Aresta> getArestas(Vertice Vertice) {
        return listaAdjacencia.get(Vertice);
    }

    public List<Vertice> getVertices() {
        return new ArrayList<>(listaAdjacencia.keySet());
    }

    public Map<Vertice, List<Aresta>> getListaAdjacencia() {
        return listaAdjacencia;
    }

    public double[][] getMatrizAdjacencia() {
        int tamanho = listaAdjacencia.size();
        double[][] matrizAdjacencia = new double[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizAdjacencia[i][j] = 0;
            }
        }

        List<Vertice> vertices = getVertices();

        for (int i = 0; i < tamanho; i++) {
            List<Aresta> arestas = getArestas(vertices.get(i));

            for (Aresta aresta : arestas) {
                int j = vertices.indexOf(aresta.destino);

                matrizAdjacencia[i][j] = aresta.peso;

                if (!direcionado) {
                    matrizAdjacencia[j][i] = aresta.peso;
                }
            }
        }

        return matrizAdjacencia;
    }

    public void removerAresta(Aresta aresta) {
        listaAdjacencia.get(aresta.origem).remove(aresta);

        if (!direcionado) {
            listaAdjacencia.get(aresta.destino).removeIf(a -> a.destino.equals(aresta.origem));
        }
        this.quantidadeAresta--;
    }

    public boolean arestaExiste(Vertice vertice, Vertice vertice2) {
        for (Entry<Vertice, List<Aresta>> entry : listaAdjacencia.entrySet()) {
            if (entry.getKey() == vertice) {
                List<Aresta> novaLista = entry.getValue();
                for (Aresta aresta : novaLista) {
                    if (aresta.destino == vertice2 || aresta.origem == vertice2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String printMatriz() {
        double[][] matrizAdjacencia = getMatrizAdjacencia();
        StringBuilder sb = new StringBuilder();

        sb.append("Matriz de Adjacência: \n");
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                sb.append(matrizAdjacencia[i][j]);
                if (j < matrizAdjacencia[i].length - 1) {
                    sb.append(" ");
                }
            }
            if (i < matrizAdjacencia.length - 1) {
                sb.append("\n");
            }
        }
        String matrizString = sb.toString();
        return matrizString;
    }

    public boolean incidenciaArestaVertice(Aresta aresta, Vertice vertice) {
        List<Aresta> arestas = listaAdjacencia.get(vertice);
        return arestas != null && arestas.contains(aresta);
    }

    public int quantidadeAresta() {
        return quantidadeAresta;
    }

    public int quantidadeVertice() {
        return quantidadeVertice;
    }

    public List<Vertice> buscaEmProfundidade(Vertice origem) {
        List<Vertice> resultado = new ArrayList<>();
        Set<Vertice> visitados = new HashSet<>();
        Stack<Vertice> pilha = new Stack<>();

        pilha.push(origem);
        visitados.add(origem);

        while (!pilha.isEmpty()) {
            Vertice atual = pilha.pop();
            resultado.add(atual);

            for (Aresta aresta : listaAdjacencia.get(atual)) {
                Vertice vizinho = aresta.getDestino();

                if (!visitados.contains(vizinho)) {
                    pilha.push(vizinho);
                    visitados.add(vizinho);
                }
            }
        }

        return resultado;
    }

    public List<Vertice> buscaEmLargura(Vertice origem) {
        List<Vertice> resultado = new ArrayList<>();
        Set<Vertice> visitados = new HashSet<>();
        Queue<Vertice> fila = new LinkedList<>();

        fila.offer(origem);
        visitados.add(origem);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            resultado.add(atual);

            for (Aresta aresta : listaAdjacencia.get(atual)) {
                Vertice vizinho = aresta.getDestino();

                if (!visitados.contains(vizinho)) {
                    fila.offer(vizinho);
                    visitados.add(vizinho);
                }
            }
        }

        return resultado;
    }

    /*
     * Método para verificar se um grafo é conexo ou não
     */
    public boolean verificarConexo() {
        if (verificaGrafoVazio()) {
            throw new GrafoNaoConexoException("O grafo está vazio e, portanto, não é conexo.");
        }

        Map<Vertice, Boolean> visitados = new HashMap<>();
        for (Vertice vertice : listaAdjacencia.keySet()) {
            visitados.put(vertice, false);
        }

        for (Vertice vertice : listaAdjacencia.keySet()) {
            if (!visitados.get(vertice)) {
                Queue<Vertice> fila = new LinkedList<>();
                fila.offer(vertice);
                visitados.put(vertice, true);

                while (!fila.isEmpty()) {
                    Vertice atual = fila.poll();
                    List<Aresta> arestas = listaAdjacencia.get(atual);

                    for (Aresta aresta : arestas) {
                        Vertice vizinho = aresta.getDestino();

                        if (!visitados.get(vizinho)) {
                            fila.offer(vizinho);
                            visitados.put(vizinho, true);
                        }
                    }
                }
            }
        }
        for (boolean visitado : visitados.values()) {
            if (!visitado) {
                return false;
            }
        }

        return true;
    }

    public boolean possuiCicloNegativoBellmanFord() {
        Map<Vertice, Double> distancias = new HashMap<>();
        Vertice qualquerVertice = this.getVertices().get(0);

        distancias = BellmanFord.calcularDistancias(this, qualquerVertice);

        for (int i = 1; i < this.quantidadeVertice(); i++) {
            for (Vertice vertice : this.getVertices()) {
                for (Aresta aresta : this.listaAdjacencia.get(vertice)) {
                    BellmanFord.relaxamento(aresta, distancias);
                }
            }
        }

        for (Vertice vertice : this.getVertices()) {
            for (Aresta aresta : this.listaAdjacencia.get(vertice)) {
                if (BellmanFord.relaxamento(aresta, distancias)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Chamada dos Algoritmos na classe Grafo

    /*
     * Dijkstra calculando a menor
     * distância de uma origem para
     * todos os outros vértices.
     */
    public Map<Vertice, Double> dijkstra(Vertice origem) {
        if (verificarConexo()) {
            return Dijkstra.calcularDistancias(this, origem);
        }
        throw new GrafoNaoConexoException("O grafo não é conexo e Dijkstra não pode ser aplicado.");
    }

    /*
     * Dijkstra calculando a menor
     * distância de todos para todos.
     */
    public Map<Vertice, Map<Vertice, Double>> dijkstraPorVertice() {
        if (verificarConexo()) {
            Map<Vertice, Map<Vertice, Double>> distancias = new HashMap<>();

            for (Vertice origem : listaAdjacencia.keySet()) {
                distancias.put(origem, Dijkstra.calcularDistancias(this, origem));
            }

            return distancias;
        }
        throw new GrafoNaoConexoException("O grafo não é conexo e Dijkstra não pode ser aplicado.");
    }

    /*
     * Bellman-Ford calculando a menor
     * distância de uma origem para
     * todos os outros vértices.
     */
    public Map<Vertice, Double> bellmanFord(Vertice origem) {
        if (verificarConexo()) {
            return BellmanFord.calcularDistancias(this, origem);
        }
        throw new GrafoNaoConexoException("O grafo não é conexo e Bellman-Ford não pode ser aplicado.");
    }

    /*
     * Bellman-Ford calculando a menor
     * distância de todos para todos.
     */
    public Map<Vertice, Map<Vertice, Double>> bellmanFordParaTodos() {
        if (verificarConexo()) {
            Map<Vertice, Map<Vertice, Double>> distancias = new HashMap<>();

            for (Vertice origem : listaAdjacencia.keySet()) {
                distancias.put(origem, BellmanFord.calcularDistancias(this, origem));
            }

            return distancias;
        }
        throw new GrafoNaoConexoException("O grafo não é conexo e Bellman-Ford não pode ser aplicado.");
    }

    /*
     * Floyd-Warshall calculando a menor
     * distância de todos para todos
     */
    public Map<Vertice, Map<Vertice, Double>> floydWarshall() {
        if (verificarConexo()) {
            return FloydWarshall.calcularDistancias(this);
        }
        throw new GrafoNaoConexoException("O grafo não é conexo e Floyd-Warshall não pode ser aplicado.");
    }

    /*
     * AEstrela calcula a menor distância
     * contando o peso da aresta e do vértice
     */
    public List<Vertice> aEstrela(Vertice origem, Vertice destino) {
        return AEstrela.estrela(this, origem, destino);
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Quantidade de vértices do grafo: " + quantidadeVertice + "\n");
        resultado.append("Quantidade de arestas do grafo: " + quantidadeAresta + "\n");
        resultado.append("Lista de Adjacência: \n");
        for (Vertice Vertice : listaAdjacencia.keySet()) {
            resultado.append(Vertice).append(" -> ").append(listaAdjacencia.get(Vertice))
                    .append(" " + Vertice.printPesoVertice()).append("\n");
        }
        for (Vertice vertice : listaAdjacencia.keySet()) {
            List<Aresta> arestas = listaAdjacencia.get(vertice);
            for (Aresta aresta : arestas) {
                resultado.append(aresta).append(" ").append(aresta.printPesoAresta()).append("\n");
            }
        }
        resultado.append(printMatriz());
        return resultado.toString();
    }
}