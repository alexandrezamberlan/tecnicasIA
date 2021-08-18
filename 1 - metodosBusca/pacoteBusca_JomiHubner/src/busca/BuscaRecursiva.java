package busca;



/**
 *   Algoritmos de Busca (geral, qquer problema)
 *
 *   Busca a solução por busca em profundidade.
 *   Implementação recursiva.
 *
 *   @author Jomi Fred Hübner
 */
public class BuscaRecursiva extends BuscaProfundidade {
     
    /** busca sem mostrar status */
    public BuscaRecursiva() {
    }
    
    /** busca mostrando status */
    public BuscaRecursiva(MostraStatusConsole ms) {
        super(ms);
    }
    
    /**
     * Busca a solução por busca em profundidade.
     *                              ------------
     *
     * Como o algoritmo é recursivo, não precisa da lista de abertos.
     *
     * max é a profundidade máxima de busca.
     */
    public Nodo busca(Estado inicial) {
        status.inicia();
        initFechados();
        Nodo n = buscaR(new Nodo(inicial, null));
        status.termina(n != null);
        return n;
    }
    
    public Nodo buscaR(Nodo corrente) {
        if (corrente == null) {
            return null;
        }
        status.explorando(corrente, 0);
        if (corrente.estado.ehMeta()) {
            return corrente;
        }
        if (corrente.getProfundidade() > profMax || parar) {
            return null;
        }
        for (Nodo s: sucessores(corrente)) {
            Nodo n = buscaR(s);
            if (n != null) {
                return n;
            }
        }
        return null;
    }   
    
    
    public String toString() {
    	return "BP - Busca em Profundidade (m="+profMax+") - versão recursiva";
    }
}
