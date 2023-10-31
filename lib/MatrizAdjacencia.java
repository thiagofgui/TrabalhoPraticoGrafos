


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
        matrizAdjacencia[origemIndex][destinoIndex] = 1;
        matrizAdjacencia[destinoIndex][origemIndex] = 1;
    }

    public void imprimirMatrizAdjacencia() {
        System.out.print("  ");
        for (int z = 0; z < matrizAdjacencia.length; z++){
            System.out.print(z + 1 + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
