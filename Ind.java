// Importa a interface List, que será usada para representar listas de indivíduos
import java.util.List;

/**
 * A interface Ind representa o que qualquer "indivíduo" de um Algoritmo Genético
 * precisa saber fazer: recombinar (cruzar), mutar e avaliar seu desempenho (fitness).
 * Essa interface será implementada por classes que representam soluções (como NRainhasInd).
 */
public interface Ind {

    /**
     * Realiza o cruzamento (recombinação genética) entre este indivíduo (this)
     * e outro indivíduo (ind). O resultado será uma lista com os filhos (novos indivíduos).
     *
     * @param ind - o outro indivíduo com quem será feito o cruzamento
     * @return lista de novos indivíduos (filhos gerados pela recombinação)
     */
    public List<Ind> recombinar(Ind ind);

    /**
     * Aplica uma mutação aleatória neste indivíduo.
     * A mutação altera um ou mais genes, criando uma nova variação da solução.
     *
     * @return um novo indivíduo que é uma cópia do atual com mutações aplicadas
     */
    public Ind mutar();

    /**
     * Avalia a qualidade do indivíduo.
     * Em problemas como o das N-Rainhas, é o número de conflitos (colisões).
     * Quanto menor o valor retornado, melhor é a solução.
     *
     * @return valor numérico que representa o "erro" ou "custo" da solução
     */
    public double getAvaliacao();
}


