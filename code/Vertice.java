public class Vertice {
    double peso;
    String rotulo;

    public Vertice(String rotulo, double peso) {
        this.peso = peso;
        this.rotulo = rotulo;
    }

    public Vertice(String rotulo) {
        this.peso = 0.0;
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
