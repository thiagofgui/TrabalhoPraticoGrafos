import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GerarGrafosTestes {

    public static void main(String[] args) {
        gerarGrafosTestes();
    }

    public static void gerarGrafosTestes() {
        int[] tamanhos = { 100, 1000, 10000 };

        for (int tamanho : tamanhos) {
            Grafo grafo = gerarGrafoConexo(tamanho);
            gerarArquivoPAJEK("grafo_" + tamanho + ".net", grafo);
        }
    }

    public static Grafo gerarGrafoConexo(int tamanho) {
        Grafo grafo = new Grafo();
        Random random = new Random();
        Set<String> rotulosGerados = new HashSet<>();

        for (int i = 1; i <= tamanho; i++) {
            String rotulo;

            do {
                rotulo = gerarRotuloAleatorio();
            } while (!rotulosGerados.add(rotulo));

            double peso;
            do {
                peso = ((int) (random.nextDouble() * 10)) / 1.0;
            } while (peso <= 0.0);

            grafo.adicionarVertice(new Vertice(rotulo, peso));
        }

        for (int i = 1; i <= tamanho; i++) {
            int origemIndex = random.nextInt(tamanho) + 1;
            int destinoIndex = random.nextInt(tamanho) + 1;

            Vertice origem = grafo.getVertices().get(origemIndex - 1);
            Vertice destino = grafo.getVertices().get(destinoIndex - 1);

            int peso;
            do {
                peso = random.nextInt(21) - 10;
            } while (peso == 0);

            String rotuloAresta;
            do {
                rotuloAresta = gerarRotuloAleatorio();
            } while (!rotulosGerados.add(rotuloAresta));

            grafo.adicionarAresta(new Aresta(origem, destino, peso, rotuloAresta));
        }

        conectarVerticesIsolados(grafo);

        return grafo;
    }

    private static String gerarRotuloAleatorio() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static void conectarVerticesIsolados(Grafo grafo) {
        List<Vertice> verticesIsolados = new ArrayList<>();

        for (Vertice vertice : grafo.getVertices()) {
            if (grafo.getArestas(vertice).isEmpty()) {
                verticesIsolados.add(vertice);
            }
        }

        Random random = new Random();
        for (Vertice verticeIsolado : verticesIsolados) {
            int destinoIndex = random.nextInt(grafo.quantidadeVertice()) + 1;
            Vertice destino = grafo.getVertices().get(destinoIndex - 1);

            int peso;
            do {
                peso = random.nextInt(21) - 10;
            } while (peso == 0);

            grafo.adicionarAresta(new Aresta(verticeIsolado, destino, peso, gerarRotuloAleatorio()));
        }
    }

    public static void gerarArquivoPAJEK(String caminhoArquivo, Grafo grafo) {
        if (!caminhoArquivo.toLowerCase().endsWith(".net")) {
            caminhoArquivo += ".net";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("*Vertices " + grafo.quantidadeVertice() + "\n");

            Map<Vertice, Integer> indexMap = new HashMap<>();
            int index = 1;
            for (Vertice vertice : grafo.getVertices()) {
                writer.write(index + " \"" + vertice.rotulo + "\" " + vertice.peso + "\n");
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
}
