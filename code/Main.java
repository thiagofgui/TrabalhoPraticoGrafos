package code;

public class Main {
    public static void main(String[] args) {

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Vertice v5 = new Vertice(5);
        Vertice v6 = new Vertice(6);

        Aresta a1 = new Aresta(v1, v2, 2);
        Aresta a2 = new Aresta(v2, v3, 1);
        Aresta a3 = new Aresta(v3, v4, 3);
        Aresta a4 = new Aresta(v4, v1, 4);
        Aresta a5 = new Aresta(v2, v5, 2);
        Aresta a6 = new Aresta(v5, v6, 1);
        Aresta a7 = new Aresta(v6, v4, 3);
        Aresta a8 = new Aresta(v4, v5, 2);

        MatrizAdjacencia grafoMatriz = new MatrizAdjacencia(6);
        grafoMatriz.adicionarVertice(v1);
        grafoMatriz.adicionarVertice(v2);
        grafoMatriz.adicionarVertice(v3);
        grafoMatriz.adicionarVertice(v4);
        grafoMatriz.adicionarVertice(v5);
        grafoMatriz.adicionarVertice(v6);
        grafoMatriz.adicionarAresta(a1);
        grafoMatriz.adicionarAresta(a2);
        grafoMatriz.adicionarAresta(a3);
        grafoMatriz.adicionarAresta(a4);
        grafoMatriz.adicionarAresta(a5);
        grafoMatriz.adicionarAresta(a6);
        grafoMatriz.adicionarAresta(a7);
        grafoMatriz.adicionarAresta(a8);

        System.out.println("Matriz de Adjacência:");
        grafoMatriz.imprimirMatrizAdjacencia();

        ListaAdjacencia grafoLista = new ListaAdjacencia();
        grafoLista.adicionarVertice(v1);
        grafoLista.adicionarVertice(v2);
        grafoLista.adicionarVertice(v3);
        grafoLista.adicionarVertice(v4);
        grafoLista.adicionarAresta(a1);
        grafoLista.adicionarAresta(a2);
        grafoLista.adicionarAresta(a3);
        grafoLista.adicionarAresta(a4);

        System.out.println("\nLista de Adjacência:");
        grafoLista.imprimirListaAdjacencia();
    }
}