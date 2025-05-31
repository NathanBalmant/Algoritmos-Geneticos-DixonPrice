/**
 * A interface IndFactory define um contrato para qualquer classe que seja capaz
 * de criar novos indivíduos (objetos que implementam a interface Ind).
 *
 * Essa "fábrica de indivíduos" é usada pelo algoritmo genético para gerar a população inicial.
 */
public interface IndFactory {

    /**
     * Cria e retorna uma nova instância de um indivíduo (Ind),
     * geralmente com valores aleatórios (genes iniciais).
     *
     * @return um novo indivíduo que será usado na população
     */
    public Ind getInstance();
}

