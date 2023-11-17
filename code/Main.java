package code;

public class Main {
    public static void main(String[] args) {
        Grafo grafoDirecionado = new Grafo(true);
        Vertice vertice1 = new Vertice("A", 10);
        Vertice vertice2 = new Vertice("B", 20);
        Vertice vertice3 = new Vertice("C", 30);

        grafoDirecionado.adicionarVertice(vertice1);
        grafoDirecionado.adicionarVertice(vertice2);
        grafoDirecionado.adicionarVertice(vertice3);

        Aresta aresta1 = new Aresta(vertice1, vertice2, 1);
        Aresta aresta2 = new Aresta(vertice2, vertice3, 2);
        Aresta aresta3 = new Aresta(vertice3, vertice1, 3);

        grafoDirecionado.adicionarAresta(aresta1);
        grafoDirecionado.adicionarAresta(aresta2);
        grafoDirecionado.adicionarAresta(aresta3);

        System.out.println("Grafo Direcionado:");
        System.out.println(grafoDirecionado);

        boolean incidencia = grafoDirecionado.incidenciaArestaVertice(aresta2, vertice2);
        System.out.println("Aresta 2 incide no vértice 2? " + incidencia);

        grafoDirecionado.removerAresta(aresta2);

        System.out.println("Grafo Direcionado após remover a aresta entre B e C:");
        System.out.println(grafoDirecionado);

        Grafo grafoNaoDirecionado = new Grafo(false);

        grafoNaoDirecionado.adicionarVertice(vertice1);
        grafoNaoDirecionado.adicionarVertice(vertice2);
        grafoNaoDirecionado.adicionarVertice(vertice3);

        Aresta arestaNaoDirecionada1 = new Aresta(vertice1, vertice2, 4);
        Aresta arestaNaoDirecionada2 = new Aresta(vertice2, vertice3, 5);
        Aresta arestaNaoDirecionada3 = new Aresta(vertice2, vertice3, 6);
        grafoNaoDirecionado.adicionarAresta(arestaNaoDirecionada1);
        grafoNaoDirecionado.adicionarAresta(arestaNaoDirecionada2);
        grafoNaoDirecionado.adicionarAresta(arestaNaoDirecionada3);
        if (grafoNaoDirecionado.verificaGrafoCompleto()) {
            System.out.println("O grafo é completo.");
        } else {
            System.out.println("O grafo não é completo.");
        }
        if (grafoNaoDirecionado.verificaGrafoVazio()) {
            System.out.println("O grafo está vazio.");
        } else {
            System.out.println("O grafo não está vazio.");
        }
        if (grafoDirecionado.verificaGrafoCompleto()) {
            System.out.println("O grafo é completo.");
        } else {
            System.out.println("O grafo não é completo.");
        }
        if (grafoDirecionado.verificaGrafoVazio()) {
            System.out.println("O grafo está vazio.");
        } else {
            System.out.println("O grafo não está vazio.");
        }

        System.out.println("Grafo Não Direcionado:");
        System.out.println(grafoNaoDirecionado);
    }
}