public class Vertice {
    int id;
    double peso;
    String rotulo;

    public Vertice(int id, String rotulo, double peso) {
        this.id = id;
        this.peso = peso;
        this.rotulo = rotulo;
    }

    public String printPesoVertice() {
        return "Peso: " + peso;
    }

    @Override
    public String toString() {
        return rotulo;
    }
}
