public class MainCalculaTempo {
    public static void main(String[] args) {
        System.out.println("Dijkstra: ");
        dijkstra("grafoDijkstra_10000.net");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Bellman-Ford: ");
        bellmanFord("grafoDijkstra_10000.net");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Floyd-Warshall: ");
        floydWarshall("grafoDijkstra_10000.net");
        System.out.println("-----------------------------------------------------------");

    }

    public static Long retornaTempo(boolean init) {
        if (init) {
            return System.currentTimeMillis();
        } else {
            long endTime = System.currentTimeMillis();
            return endTime;
        }
    }

    private static void dijkstra(String caminho) {
        Grafo grafo = GrafoPAJEK.lerArquivoPAJEK(caminho, true);
        boolean inicio = true;
        long startTime = retornaTempo(inicio);

        retornaTempo(true);
        grafo.dijkstra(grafo.getListaAdjacencia().keySet().stream().findFirst().get());
        retornaTempo(false);

        inicio = false;
        long endTime = retornaTempo(inicio);

        long tempoDeExecucao = endTime - startTime;

        System.out.println("Tempo de execução: " + tempoDeExecucao + " milessegundos");
    }

    private static void bellmanFord(String caminho) {
        Grafo grafo = GrafoPAJEK.lerArquivoPAJEK(caminho, true);
        boolean inicio = true;
        long startTime = retornaTempo(inicio);

        retornaTempo(true);
        grafo.bellmanFord((grafo.getListaAdjacencia().keySet().stream().findFirst().get()));

        retornaTempo(false);

        inicio = false;
        long endTime = retornaTempo(inicio);

        long tempoDeExecucao = endTime - startTime;

        System.out.println("Tempo de execução: " + tempoDeExecucao + " milessegundos");
    }

    private static void floydWarshall(String caminho) {
        Grafo grafo = GrafoPAJEK.lerArquivoPAJEK(caminho, true);
        boolean inicio = true;
        long startTime = retornaTempo(inicio);

        retornaTempo(true);
        grafo.floydWarshall();
        retornaTempo(false);

        inicio = false;
        long endTime = retornaTempo(inicio);

        long tempoDeExecucao = endTime - startTime;

        System.out.println("Tempo de execução: " + tempoDeExecucao + " milessegundos");
    }

}
