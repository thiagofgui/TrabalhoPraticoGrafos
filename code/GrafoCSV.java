package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrafoCSV {
    public static Grafo lerGrafo(String arquivo) throws IOException {
        // Abre o arquivo para leitura
        FileReader leitor = new FileReader(arquivo);
        BufferedReader bufferedReader = new BufferedReader(leitor);

        Grafo grafo = new Grafo(false);
        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] split = linha.split(",");

            Vertice v1 = new Vertice(split[0]);
            Vertice v2 = new Vertice(split[1]);

            grafo.adicionarVertice(v1);
            grafo.adicionarVertice(v2);

            grafo.adicionarAresta(new Aresta(v1, v2, 0));
        }
        bufferedReader.close();
        return grafo;
    }

    public static void writeGraph(Graph graph, String filename) throws IOException {
        // Abre o arquivo para escrita
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Escreve os vértices
        for (int vertex : graph.getVertices()) {
            bufferedWriter.write(vertex + ",");
        }
        bufferedWriter.write("\n");

        // Escreve as arestas
        for (Edge edge : graph.getEdges()) {
            bufferedWriter.write(edge.getSource() + "," + edge.getDestination() + "\n");
        }

        // Fecha o arquivo
        bufferedWriter.close();
    }

}
