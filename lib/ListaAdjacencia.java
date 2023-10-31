package code;


import java.util.*;

import Vertice;

public class ListaAdjacencia {
    private Map<Vertice, List<Aresta>> listaAdjacencia;

    public ListaAdjacencia() {
        listaAdjacencia = new HashMap<>();
    }

    public void adicionarVertice(Vertice vertice) {
        listaAdjacencia.put(vertice, new ArrayList<>());
    }

    public void adicionarAresta(Aresta aresta) {
        Vertice origem = aresta.getOrigem();
        listaAdjacencia.get(origem).add(aresta);
        Vertice destino = aresta.getDestino();
        listaAdjacencia.get(destino).add(new Aresta(destino, origem, aresta.getPeso()));
    }

    public void imprimirListaAdjacencia() {
        for (Vertice vertice : listaAdjacencia.keySet()) {
            System.out.print(vertice + " --> ");
            List<Aresta> arestas = listaAdjacencia.get(vertice);
            for (Aresta aresta : arestas) {
                System.out.print(aresta.getDestino() + " (Peso: " + aresta.getPeso() + ") ");
            }
            System.out.println();
        }
    }
}
