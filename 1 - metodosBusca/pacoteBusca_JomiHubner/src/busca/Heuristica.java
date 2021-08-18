package busca;


/**
 * Interface para estados que implementam a fun��o h()
 *
 * @author  jomi
 */

public interface Heuristica {
    
    /**
     * estimativa de custo
     */
    public int h();

}

// custo real -> g(n)
// custo heurístico -> g(h)
