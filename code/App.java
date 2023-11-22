public class App {

    public static void main(String[] args) {
        String caminhoArquivo = "grafoTeste.net";
        Grafo grafo = GrafoPAJEK.lerArquivoPAJEK(caminhoArquivo, true);

        if (grafo != null) {
            System.out.println(grafo);
        } else {
            System.out.println("Erro ao ler o arquivo PAJEK.");
        }
    }

}
