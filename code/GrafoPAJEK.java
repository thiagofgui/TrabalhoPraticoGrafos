import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrafoPAJEK {

    public static void gerarArquivoPAJEK(String caminhoArquivo, Grafo grafo) {
        if (!caminhoArquivo.toLowerCase().endsWith(".net")) {
            caminhoArquivo += ".net";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Escrever cabeçalho
            writer.write("*Vertices " + grafo.quantidadeVertice());
            writer.newLine();

            // Escrever informações dos vértices
            List<Vertice> vertices = grafo.getVertices();
            for (int i = 0; i < vertices.size(); i++) {
                Vertice vertice = vertices.get(i);
                writer.write("\"" + vertice.rotulo + "\" " + vertice.peso);
                writer.newLine();
            }

            // Conjunto para armazenar arestas já processadas
            Set<String> arestasProcessadas = new HashSet<>();

            // Escrever cabeçalho para arestas
            writer.write("*Edges");
            writer.newLine();

            for (Vertice vertice : vertices) {
                List<Aresta> arestas = grafo.getArestas(vertice);
                for (Aresta aresta : arestas) {
                    Vertice destino = aresta.destino;

                    // Ordenar as posições dos vértices na chave
                    int pos1 = Math.min(vertices.indexOf(vertice), vertices.indexOf(destino));
                    int pos2 = Math.max(vertices.indexOf(vertice), vertices.indexOf(destino));

                    String chaveAresta = pos1 + "_" + pos2;

                    // Verificar se a aresta já foi processada
                    if (!arestasProcessadas.contains(chaveAresta)) {
                        writer.write((pos1 + 1) + " " +
                                (pos2 + 1) + " " + aresta.peso + " \"" + aresta.rotulo + "\"");
                        writer.newLine();

                        // Adicionar a aresta ao conjunto de arestas processadas
                        arestasProcessadas.add(chaveAresta);
                    }
                }
            }

            System.out.println("Arquivo PAJEK gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao gerar o arquivo PAJEK.");
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
