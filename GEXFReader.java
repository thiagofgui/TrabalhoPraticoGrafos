import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class GEXFReader {

    public static void main(String[] args) {
        try {
            // caminho do arquivo 
            String filePath = "code/hello-world.gexf";

            // Crie um mapa para armazenar os vértices por ID
            Map<String, Vertice> vertices = new HashMap<>();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            // Variáveis para rastrear o estado do parser
            boolean inNodeSection = false;
            boolean inEdgeSection = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("<node ")) {
                    inNodeSection = true;

                    String id = extractAttribute(line, "id");
                    int peso = Integer.parseInt(extractAttribute(line, "weight"));

                    Vertice vertice = new Vertice(id, peso);
                    vertices.put(id, vertice);
                } else if (line.startsWith("</nodes>")) {
                    inNodeSection = false;
                } else if (line.startsWith("<edge ")) {
                    inEdgeSection = true;

                    String source = extractAttribute(line, "source");
                    String target = extractAttribute(line, "target");
                    int pesoAresta = Integer.parseInt(extractAttribute(line, "weight"));

                    Vertice origem = vertices.get(source);
                    Vertice destino = vertices.get(target);

                    Aresta aresta = new Aresta(origem, destino, pesoAresta);

                    // Adicione a aresta ao seu grafo
                    // Exemplo: grafo.adicionarAresta(aresta);
                } else if (line.startsWith("</edges>")) {
                    inEdgeSection = false;
                }
            }

            reader.close();

            // Agora você tem os vértices e as arestas do arquivo GEXF em seu mapa e pode usá-los conforme necessário.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractAttribute(String line, String attributeName) {
        int start = line.indexOf(attributeName + "=\"") + attributeName.length() + 2;
        int end = line.indexOf("\"", start);
        return line.substring(start, end);
    }
}