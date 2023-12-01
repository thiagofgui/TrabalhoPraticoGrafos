import java.util.List;

public class MainTeste {

    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);

        // Adicionando vértices
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");

        grafo.adicionarVertice(a);
        grafo.adicionarVertice(b);
        grafo.adicionarVertice(c);
        grafo.adicionarVertice(d);
        grafo.adicionarVertice(e);
        grafo.adicionarVertice(f);

        // Adicionando arestas
        grafo.adicionarAresta(new Aresta(a, b, 1.0, "A"));
        grafo.adicionarAresta(new Aresta(a, c, 1.0, "B"));
        grafo.adicionarAresta(new Aresta(b, d, 1.0, "C"));
        grafo.adicionarAresta(new Aresta(b, e, 1.0, "D"));
        grafo.adicionarAresta(new Aresta(c, f, 1.0, "E"));

        // Realizando busca em profundidade (DFS)
        System.out.println("Busca em Profundidade (DFS):");
        List<Vertice> resultadoDFS = grafo.buscaEmProfundidade(a);
        System.out.println(resultadoDFS);

        // Realizando busca em largura (BFS)
        System.out.println("\nBusca em Largura (BFS):");
        List<Vertice> resultadoBFS = grafo.buscaEmLargura(a);
        System.out.println(resultadoBFS);
    }
}
