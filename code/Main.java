import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create a sample graph with 10 vertices
        Grafo grafo = new Grafo();

        Vertice a = new Vertice("A", 0.0);
        Vertice b = new Vertice("B", 1.0);
        Vertice c = new Vertice("C", 2.0);
        Vertice d = new Vertice("D", 3.0);
        Vertice e = new Vertice("E", 4.0);
        Vertice f = new Vertice("F", 5.0);
        Vertice g = new Vertice("G", 6.0);
        Vertice h = new Vertice("H", 7.0);
        Vertice i = new Vertice("I", 8.0);
        Vertice j = new Vertice("J", 9.0);

        grafo.adicionarVertice(a);
        grafo.adicionarVertice(b);
        grafo.adicionarVertice(c);
        grafo.adicionarVertice(d);
        grafo.adicionarVertice(e);
        grafo.adicionarVertice(f);
        grafo.adicionarVertice(g);
        grafo.adicionarVertice(h);
        grafo.adicionarVertice(i);
        grafo.adicionarVertice(j);

        grafo.adicionarAresta(new Aresta(a, b, 2.0, "ab"));
        grafo.adicionarAresta(new Aresta(a, c, 3.0, "ac")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(b, c, 1.0, "bc"));
        grafo.adicionarAresta(new Aresta(b, d, 4.0, "bd")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(c, d, 2.0, "cd"));
        grafo.adicionarAresta(new Aresta(c, e, 3.0, "ce")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(d, e, 1.0, "de"));
        grafo.adicionarAresta(new Aresta(d, f, 4.0, "df")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(e, f, 2.0, "ef"));
        grafo.adicionarAresta(new Aresta(e, g, 3.0, "eg")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(f, g, 1.0, "fg"));
        grafo.adicionarAresta(new Aresta(f, h, 4.0, "fh")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(g, h, 2.0, "gh"));
        grafo.adicionarAresta(new Aresta(g, i, 3.0, "gi")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(h, i, 1.0, "hi"));
        grafo.adicionarAresta(new Aresta(h, j, 4.0, "hj")); // Nova aresta para diversificar os caminhos
        grafo.adicionarAresta(new Aresta(i, j, 2.0, "ij"));

        // Define the start and end vertices for testing
        Vertice origem = a;
        Vertice destino = j;

        // Test A* algorithm
        System.out.println("A* Algorithm:");
        List<Vertice> caminhoAStar = AEstrela.aStar(grafo, origem, destino);
        if (caminhoAStar != null) {
            System.out.println("Caminho mais eficiente: " + caminhoAStar);
        } else {
            System.out.println("Caminho não encontrado.");
        }

        // Test Dijkstra algorithm
        System.out.println("\nDijkstra Algorithm:");
        Map<Vertice, Double> distanciasDijkstra = Dijkstra.calcularDistancias(grafo, origem);
        System.out.println("Distâncias a partir de " + origem + ": " + distanciasDijkstra);
    }

}
