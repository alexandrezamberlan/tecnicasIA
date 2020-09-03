package busca;

import java.util.List;

/**
 * Representa um estado do mundo e as transicoes possiveis
 */
public interface Estado {

    /**
     * retorna uma descricao do problema que esta representacao
     * de estado resolve
     */
    public String getDescricao();
    
    /**
     * verifica se o estado e meta 
     */
    public boolean ehMeta();


    /**
     * Custo para geracao deste estado
     * (nao e o custo acumulado --- g)
     */
    public int custo();


   /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores();

}
