package code;

import lib.Aresta;
import lib.Grafo;
import lib.Vertice;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        Vertice v1 = new Vertice(1, 2);
        Vertice v2 = new Vertice(2, 4);
        Vertice v3 = new Vertice(3, 6);

        Aresta a1 = new Aresta(v1, v2, 5);
        Aresta a2 = new Aresta(v2, v3, 7);

        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);

        grafo.adicionarAresta(a1);
        grafo.adicionarAresta(a2);

        grafo.imprimirVertices();
        grafo.imprimirArestas();
    }
}