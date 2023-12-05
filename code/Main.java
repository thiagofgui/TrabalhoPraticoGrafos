public class Main {
    public static void main(String[] args) {
        Grafo direcionado = new Grafo(true);
        Grafo naoDirecionado = new Grafo(false);
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Aresta arestaAB = new Aresta(a, b, 2.0, "AB");
        Aresta arestaBC = new Aresta(b, c, 1.0, "BC");
        Aresta arestaAC = new Aresta(a, c, 4.0, "AC");

        direcionado.adicionarVertice(a);
        direcionado.adicionarVertice(b);
        direcionado.adicionarVertice(c);
        direcionado.adicionarAresta(arestaAB);
        direcionado.adicionarAresta(arestaBC);
        direcionado.adicionarAresta(arestaAC);

        naoDirecionado.adicionarVertice(a);
        naoDirecionado.adicionarVertice(b);
        naoDirecionado.adicionarVertice(c);
        naoDirecionado.adicionarAresta(arestaAB);
        naoDirecionado.adicionarAresta(arestaBC);
        naoDirecionado.adicionarAresta(arestaAC);

        System.out.println(direcionado.printMatriz());
        System.out.println(naoDirecionado.printMatriz());

    }
}
