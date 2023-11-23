import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoPAJEK {

    public static void gerarArquivoPAJEK(String caminhoArquivo, Grafo grafo) {
        if (!caminhoArquivo.toLowerCase().endsWith(".net")) {
            caminhoArquivo += ".net";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("*Vertices " + grafo.quantidadeVertice() + "\n");

            // Mapeamento de vértices para índices
            Map<Vertice, Integer> indexMap = new HashMap<>();
            int index = 1;
            for (Vertice vertice : grafo.getVertices()) {
                writer.write("\"" + vertice + "\" " + vertice.peso + "\n");
                indexMap.put(vertice, index);
                index++;
            }

            writer.write("*Edges\n");
            for (Vertice vertice : grafo.getVertices()) {
                List<Aresta> arestas = grafo.getArestas(vertice);
                for (Aresta aresta : arestas) {
                    int origemIndex = indexMap.get(aresta.origem);
                    int destinoIndex = indexMap.get(aresta.destino);
                    writer.write(origemIndex + " " + destinoIndex + " " + aresta.peso + " \"" + aresta.rotulo + "\"\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                        String rotulo = dadosVertice[0];
                        double peso = Double.parseDouble(dadosVertice[1].replaceAll("\"", ""));
                        Vertice vertice = new Vertice(rotulo, peso);
                        grafo.adicionarVertice(vertice);
                    }
                } else if (linha.startsWith("*Edges")) {
                    while ((linha = br.readLine()) != null) {
                        String[] dadosAresta = linha.split(" ");
                        int origem = Integer.parseInt(dadosAresta[0]);
                        int destino = Integer.parseInt(dadosAresta[1]);
                        double peso = Double.parseDouble(dadosAresta[2]);
                        String rotulo = dadosAresta[3];

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
