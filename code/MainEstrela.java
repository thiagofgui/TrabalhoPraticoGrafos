import java.util.Map;

public class MainEstrela {

    public static void main(String[] args) {
        // Criando um grafo para teste
        Grafo grafo = new Grafo();

        // Adicionando vértices
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Vertice v3 = new Vertice("C");

        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);

        // Adicionando arestas com pesos
        grafo.adicionarAresta(new Aresta(v1, v2, 5.0, "a"));
        grafo.adicionarAresta(new Aresta(v2, v3, 3.0,"b"));
        grafo.adicionarAresta(new Aresta(v1, v3, 10.0,"c"));

        // Origem e destino para calcular distâncias
        Vertice origem = v1;
        Vertice destino = v3;

        // Chamando o método para calcular distâncias usando o algoritmo A*
        Map<Vertice, Double> distancias = Estrela.calcularDistancias(grafo, origem, destino);

        // Exibindo as distâncias calculadas
        for (Map.Entry<Vertice, Double> entry : distancias.entrySet()) {
            Vertice vertice = entry.getKey();
            Double distancia = entry.getValue();

            System.out.println("Distância de " + origem + " para " + vertice + ": " + distancia);
        }
    
}

}
