import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoPAJEK {

    public static void gerarArquivoPAJEK(String caminhoArquivo, Grafo grafo) {
        if (!caminhoArquivo.toLowerCase().endsWith(".net")) {
            caminhoArquivo += ".net";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("*Vertices " + grafo.quantidadeVertice() + "\n");

            Map<Vertice, Integer> indexMap = new HashMap<>();
            int index = 1;
            for (Vertice vertice : grafo.getVertices()) {
                writer.write(index + " \"" + vertice + "\" " + vertice.peso + "\n");
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
        Grafo grafo = new Grafo(direcionado);

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();

                if (linha.startsWith("*Vertices")) {
                    int numVertices = Integer.parseInt(linha.split("\\s+")[1]);
                    for (int i = 0; i < numVertices; i++) {
                        linha = reader.readLine().trim();
                        String[] partes = linha.split("\\s+");
                        String rotuloVertice = partes[1].replace("\"", "");
                        double pesoVertice = Double.parseDouble(partes[2]);
                        Vertice vertice = new Vertice(rotuloVertice, pesoVertice);
                        grafo.adicionarVertice(vertice);
                    }
                } else if (linha.startsWith("*Edges") || linha.startsWith("*Arcs")) {
                    while ((linha = reader.readLine()) != null) {
                        linha = linha.trim();
                        if (linha.isEmpty()) {
                            continue;
                        }

                        String[] partes = linha.split("\\s+");
                        int origemIndex = Integer.parseInt(partes[0]);
                        int destinoIndex = Integer.parseInt(partes[1]);
                        double pesoAresta = Double.parseDouble(partes[2]);
                        String rotuloAresta = partes.length > 3 ? partes[3].replace("\"", "") : "";

                        Vertice origem = grafo.getVertices().get(origemIndex - 1);
                        Vertice destino = grafo.getVertices().get(destinoIndex - 1);
                        Aresta aresta = new Aresta(origem, destino, pesoAresta, rotuloAresta);
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
