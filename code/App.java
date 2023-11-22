import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Ler arquivo pajek \n2 - Gerar arquivo pajek");
        int op = sc.nextInt();
        if (op == 1) {
            String caminhoArquivo = "grafoTeste.net";
            Grafo grafo = GrafoPAJEK.lerArquivoPAJEK(caminhoArquivo, true);

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

            // Gerando o arquivo PAJEK
            GrafoPAJEK gj = gj.gerarArquivoPAJEK(nomeArquivo, grafo);
            System.out.println("Arquivo PAJEK gerado com sucesso: " + nomeArquivo);
        }

        public static Grafo testeGrafo(){
            Grafo g = new Grafo(false);
            g.adicionarVertice(new Vertice(1, "a", 3));
            return g;
        }
    }

}
