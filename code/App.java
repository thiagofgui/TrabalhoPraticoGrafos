import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Ler arquivo pajek \n2 - Gerar arquivo pajek");
        int op = sc.nextInt();
        if (op == 1) {
            String caminhoArquivo = "grafoTeste.net";
            Grafo grafo = GrafoPAJEK.lerArquivoPAJEK(caminhoArquivo, false);

            if (grafo != null) {
                System.out.println(grafo);
            } else {
                System.out.println("Erro ao ler o arquivo PAJEK.");
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite um nome para o arquivo (ou deixe em branco para um nome aleatório): ");
            String userInput = scanner.nextLine();
            String nomeArquivo;

            if (userInput.isEmpty()) {
                nomeArquivo = "grafo_" + UUID.randomUUID().toString() + ".net";
            } else {
                nomeArquivo = userInput;
            }

            GrafoPAJEK.gerarArquivoPAJEK(nomeArquivo, testeGrafo());
            System.out.println("Arquivo PAJEK gerado com sucesso: " + nomeArquivo);
        }
    }

    public static Grafo testeGrafo() {
        Grafo grafo = new Grafo(false);
        Vertice v1 = new Vertice("A", 3.0);
        Vertice v2 = new Vertice("B", 2.0);
        Vertice v3 = new Vertice("C", 1.0);

        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);

        grafo.adicionarAresta(new Aresta(v1, v2, 5, "A"));
        grafo.adicionarAresta(new Aresta(v2, v3, 4, "B"));
        grafo.adicionarAresta(new Aresta(v3, v1, 6, "C"));
        return grafo;
    }

}
