// Importação de bibliotecas utilitárias
import java.util.ArrayList;     // Para criar listas dinâmicas
import java.util.Collections;   // Para ordenação e busca em listas
import java.util.Comparator;    // Para comparar indivíduos pela avaliação
import java.util.List;          // Interface de listas
import java.util.Random;        // Para gerar números aleatórios

/**
 * Classe que implementa o Algoritmo Genético (AG) para resolver um problema como as N-Rainhas.
 * Ela mantém uma população de indivíduos e executa operações de seleção, cruzamento e mutação.
 */
public class AgFga {

    private List<Ind> populacao;        // Lista de indivíduos (soluções) da população atual
    private int tamPop;                 // Tamanho da população
    private int elite;                  // Número de indivíduos a serem preservados a cada geração (elitismo)
    private static final Random rand = new Random(); // Gerador aleatório global

    /**
     * Construtor da classe. Inicializa a população com indivíduos aleatórios.
     *
     * @param tamPop - tamanho da população
     * @param elite - número de indivíduos elitistas
     * @param factory - fábrica que gera novos indivíduos aleatórios
     */
    public AgFga(int tamPop, int elite, IndFactory factory) {
        this.tamPop = tamPop;
        this.elite = elite;
        this.populacao = new ArrayList<>();

        // Cria a população inicial com indivíduos gerados pela factory
        for (int i = 0; i < tamPop; i++) {
            populacao.add(factory.getInstance());
        }
    }

    /**
     * Executa o processo evolutivo por um número de gerações especificado.
     *
     * @param nGer - número de gerações a serem executadas
     */
    public void evoluir(int nGer) {
        for (int ger = 1; ger <= nGer; ger++) {
            List<Ind> novaPopulacao = new ArrayList<>();

            // Ordena a população pela avaliação (fitness). Menor avaliação = melhor indivíduo.
            Collections.sort(populacao, Comparator.comparingDouble(Ind::getAvaliacao));

            // Elitismo: mantém os melhores 'elite' indivíduos diretamente na próxima geração
            for (int i = 0; i < elite; i++) {
                novaPopulacao.add(populacao.get(i));
            }

            // Preenche o restante da nova população com filhos gerados por recombinação e mutação
            while (novaPopulacao.size() < tamPop) {
                Ind pai1 = selecionarTorneio();     // Seleciona o 1º pai
                Ind pai2 = selecionarTorneio();     // Seleciona o 2º pai
                List<Ind> filhos = pai1.recombinar(pai2); // Gera filhos pela recombinação

                for (Ind filho : filhos) {
                    novaPopulacao.add(filho.mutar()); // Aplica mutação e adiciona à nova população

                    // Para evitar ultrapassar o tamanho da população
                    if (novaPopulacao.size() == tamPop) break;
                }
            }

            // Atualiza a população com a nova geração
            populacao = novaPopulacao;

            // Exibe o melhor indivíduo da geração atual
            Ind melhor = getMelhor();
            System.out.printf("Geração %d | Melhor: %s | Avaliação: %.6f%n", ger, melhor.toString(), melhor.getAvaliacao());


        }
    }

    /**
     * Seleciona um indivíduo usando o método de Torneio.
     * Seleciona 'k' indivíduos aleatoriamente e retorna o melhor entre eles.
     *
     * @return o indivíduo com melhor avaliação entre os selecionados
     */
    private Ind selecionarTorneio() {
        int k = 5; // número de indivíduos a comparar no torneio
        Ind melhor = null;

        for (int i = 0; i < k; i++) {
            Ind candidato = populacao.get(rand.nextInt(tamPop)); // seleciona aleatório

            // Atualiza o melhor se este for o mais apto até agora
            if (melhor == null || candidato.getAvaliacao() < melhor.getAvaliacao()) {
                melhor = candidato;
            }
        }

        return melhor;
    }

    

    /**
     * Retorna o melhor indivíduo da população atual (menor valor de avaliação).
     *
     * @return indivíduo com melhor desempenho
     */
    public Ind getMelhor() {
        return Collections.min(populacao, Comparator.comparingDouble(Ind::getAvaliacao));
    }
}
