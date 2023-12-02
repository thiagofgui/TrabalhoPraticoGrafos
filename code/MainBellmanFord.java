import java.util.Map;

import exceptions.CicloNegativoException;
import exceptions.GrafoNaoConexoException;

public class MainBellmanFord {
    public static void main(String[] args) {
        // Criando um grafo de exemplo
        Grafo grafo = new Grafo(false);

        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");
        Vertice g = new Vertice("G");
        Vertice h = new Vertice("H");
        Vertice i = new Vertice("I");
        Vertice j = new Vertice("J");

        Aresta arestaAB = new Aresta(a, b, 1.0, "AB");
        Aresta arestaBC = new Aresta(b, c, 2.0, "BC");
        Aresta arestaAC = new Aresta(a, c, 4.0, "AC");
        Aresta arestaDE = new Aresta(d, e, -2.0, "DE");
        Aresta arestaEF = new Aresta(e, f, 3.0, "EF");
        Aresta arestaFG = new Aresta(f, g, -1.0, "FG");
        Aresta arestaGH = new Aresta(g, h, 2.0, "GH");
        Aresta arestaHI = new Aresta(h, i, 1.0, "HI");
        Aresta arestaIJ = new Aresta(i, j, -4.0, "IJ");

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

        grafo.adicionarAresta(arestaAB);
        grafo.adicionarAresta(arestaBC);
        grafo.adicionarAresta(arestaAC);
        grafo.adicionarAresta(arestaDE);
        grafo.adicionarAresta(arestaEF);
        grafo.adicionarAresta(arestaFG);
        grafo.adicionarAresta(arestaGH);
        grafo.adicionarAresta(arestaHI);
        grafo.adicionarAresta(arestaIJ);

        // Testando Bellman-Ford a partir de um vértice específico
        System.out.println("Bellman-Ford a partir de A para todos os vértices:");
        try {
            Map<Vertice, Double> resultadoBellmanFord = grafo.bellmanFordParaTodos(a);
            for (Map.Entry<Vertice, Double> entry : resultadoBellmanFord.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (GrafoNaoConexoException | CicloNegativoException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        System.out.println();
        // Testando Bellman-Ford por vértice
        System.out.println("Bellman-Ford por vértice:");

        try {
            for (Vertice origem : grafo.getVertices()) {
                Map<Vertice, Double> resultadoBellmanFordPorVertice = grafo.bellmanFordParaTodos(origem);
                System.out.println("Origem: " + origem);
                for (Map.Entry<Vertice, Double> entry : resultadoBellmanFordPorVertice.entrySet()) {
                    System.out.println("  " + entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (GrafoNaoConexoException | CicloNegativoException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
