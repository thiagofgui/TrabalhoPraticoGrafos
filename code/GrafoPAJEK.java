import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GrafoPAJEK {

    public static Grafo lerArquivoPAJEK(String caminhoArquivo, boolean direcionado) {
        Grafo grafo = null;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("*Vertices")) {
                    int numVertices = Integer.parseInt(linha.split(" ")[1]);
                    grafo = new Grafo(direcionado); // Assumindo grafo não direcionado

                    for (int i = 0; i < numVertices; i++) {
                        linha = br.readLine(); // Avançar para a próxima linha
                        String[] dadosVertice = linha.split(" ");
                        String id = dadosVertice[0];
                        String rotulo = dadosVertice[1];
                        double peso = Double.parseDouble(dadosVertice[2].replaceAll("\"", ""));
                        Vertice vertice = new Vertice(Integer.parseInt(id), rotulo, peso);
                        grafo.adicionarVertice(vertice);
                    }
                } else if (linha.startsWith("*Edges")) {
                    while ((linha = br.readLine()) != null) {
                        String[] dadosAresta = linha.split(" ");
                        int id = Integer.parseInt(dadosAresta[0]);
                        int origem = Integer.parseInt(dadosAresta[1]);
                        int destino = Integer.parseInt(dadosAresta[2]);
                        double peso = Double.parseDouble(dadosAresta[3]);
                        String rotulo = dadosAresta[4];

                        Vertice verticeOrigem = grafo.getVertices().get(origem - 1);
                        Vertice verticeDestino = grafo.getVertices().get(destino - 1);
                        Aresta aresta = new Aresta(verticeOrigem, verticeDestino, peso, rotulo);
                        grafo.adicionarAresta(aresta);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grafo;
    }
}
