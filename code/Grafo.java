import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Grafo {
    private Map<Vertice, List<Aresta>> listaAdjacencia;
    private boolean direcionado;
    private int quantidadeVertice = 0;
    private int quantidadeAresta = 0;

    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
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

    public int[][] getMatrizAdjacencia() {
        int tamanho = listaAdjacencia.size();
        int[][] matrizAdjacencia = new int[tamanho][tamanho];

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
                matrizAdjacencia[i][j] = 1;
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
                    if (aresta.destino == vertice2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String printMatriz() {
        int[][] matrizAdjacencia = getMatrizAdjacencia();
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

    // Chamada dos Algoritmos na classe Grafo

    /*
     * Dijkstra calculando a menor
     * distância de uma origem para
     * todos os outros vértices.
     */
    public Map<Vertice, Double> dijkstraParaTodos(Vertice origem) {
        return Dijkstra.calcularDistancias(this, origem);
    }

    /*
     * Dijkstra calculando a menor
     * distância de todos para todos.
     */
    public Map<Vertice, Map<Vertice, Double>> dijkstraPorVertice() {
        Map<Vertice, Map<Vertice, Double>> distancias = new HashMap<>();

        for (Vertice origem : listaAdjacencia.keySet()) {
            distancias.put(origem, Dijkstra.calcularDistancias(this, origem));
        }

        return distancias;
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