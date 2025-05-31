import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DixonPriceInd implements Ind {

    private double[] genes;
    private int n;
    private static final Random rand = new Random();

    // Construtor principal: gera genes aleatórios entre -10 e 10
    public DixonPriceInd(int n) {
        this.n = n;
        this.genes = new double[n];
        for (int i = 0; i < n; i++) {
            genes[i] = -10 + rand.nextDouble() * 20;
        }
    }

    // Construtor auxiliar: usado em recombinação e mutação
    public DixonPriceInd(double[] genes) {
        this.n = genes.length;
        this.genes = genes.clone();
    }




@Override
public List<Ind> recombinar(Ind ind) {
    DixonPriceInd outro = (DixonPriceInd) ind;
    List<Ind> filhos = new ArrayList<>();

    double[] f1 = new double[n];
    double[] f2 = new double[n];

    for (int i = 0; i < n; i++) {
        double p1 = this.genes[i];
        double p2 = outro.genes[i];
        double diff = Math.abs(p1 - p2);

        // Gera alpha com média 0.3 e desvio padrão 0.1
        double alpha = 0.3 + 0.1 * rand.nextGaussian();

        // Limita alpha para não sair do controle
        alpha = Math.max(0.0, Math.min(1.0, alpha));

        // Aplica a fórmula do quadro com alpha adaptativo
        f1[i] = p1 + alpha * diff;
        f2[i] = p2 + alpha * diff;
    }

    filhos.add(new DixonPriceInd(f1));
    filhos.add(new DixonPriceInd(f2));

    return filhos;
}




 // Recombinação Aritmética com α = 0.33
 // Gera dois filhos: um puxando mais para cada pai
/*
@Override
public List<Ind> recombinar(Ind ind) {
    DixonPriceInd outro = (DixonPriceInd) ind;
    List<Ind> filhos = new ArrayList<>();

    double[] f1 = new double[n];
    double[] f2 = new double[n];
    double alpha = 0.33;

    for (int i = 0; i < n; i++) {
        double p1 = this.genes[i];
        double p2 = outro.genes[i];

        f1[i] = (1 - alpha) * p1 + alpha * p2;
        f2[i] = (1 - alpha) * p2 + alpha * p1;
    }

    filhos.add(new DixonPriceInd(f1));
    filhos.add(new DixonPriceInd(f2));

    return filhos;
}
    */






    // Métodos obrigatórios da interface (ainda serão implementados)
  @Override
public Ind mutar() {
    double[] novosGenes = genes.clone();
    boolean houveMutacao = false;
    double taxaMutacao = 0.05;

    for (int i = 0; i < n; i++) {
        if (rand.nextDouble() < taxaMutacao) {
            novosGenes[i] += rand.nextGaussian(); // mutação gaussiana
            houveMutacao = true;
        }
    }

    // Se nenhum gene foi alterado, força a mutação de um gene aleatório
    if (!houveMutacao) {
        int pos = rand.nextInt(n);
        novosGenes[pos] += rand.nextGaussian();
    }

    return new DixonPriceInd(novosGenes);
}


    @Override
public double getAvaliacao() {
    double soma = Math.pow(genes[0] - 1, 2); // (x1 - 1)^2

    for (int i = 1; i < n; i++) {
        double termo = 2 * Math.pow(genes[i], 2) - genes[i - 1];
        soma += (i + 1) * Math.pow(termo, 2); // i+1 porque o índice começa em 0
    }

    return soma;
}

@Override
public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < n; i++) {
        sb.append(String.format("%.4f", genes[i]));
        if (i < n - 1) sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
}

}
