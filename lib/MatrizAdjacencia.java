

import java.util.*;

public class MatrizAdjacencia {
    private int[][] matrizAdjacencia;
    private List<Vertice> vertices;

    public MatrizAdjacencia(int numVertices) {
        matrizAdjacencia = new int[numVertices][numVertices];
        vertices = new ArrayList<>();
    }

    public void adicionarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void adicionarAresta(Aresta aresta) {
        int origemIndex = vertices.indexOf(aresta.getOrigem());
        int destinoIndex = vertices.indexOf(aresta.getDestino());
        matrizAdjacencia[origemIndex][destinoIndex] = aresta.getPeso();
        matrizAdjacencia[destinoIndex][origemIndex] = aresta.getPeso();
    }

    public void imprimirMatrizAdjacencia() {
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
