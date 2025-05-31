import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Principal {
    public static void main(String[] args) {
        int n = 50;         // número de variáveis (tamanho do vetor de genes)
        int elite = 4;      // número de indivíduos elitistas
        int nGer = 2000;    // número de gerações
        int tamPop = 100;   // tamanho da população

        
        try {
            PrintStream out = new PrintStream("resultado.txt");
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
        IndFactory factory = new DixonPriceIndFactory(n);

       
        AgFga ag = new AgFga(tamPop, elite, factory);

        
        ag.evoluir(nGer);
    }
}
