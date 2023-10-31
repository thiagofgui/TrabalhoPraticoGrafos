

import java.util.*;

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public void adicionarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void adicionarAresta(Aresta aresta) {
        arestas.add(aresta);
    }

    public void removerAresta(Aresta aresta) {
        arestas.remove(aresta);
    }

    public void imprimirVertices() {
        for (Vertice vertice : vertices) {
            System.out.print(vertice + " ");
        }
        System.out.println();
    }

    public void imprimirArestas() {
        for (Aresta aresta : arestas) {
            System.out.println(aresta.getOrigem() + " --(" + aresta.getPeso() + ")--> " + aresta.getDestino());
        }
    }
}
