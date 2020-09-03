package busca;




/**
 *   Algoritmos de Busca (geral, qquer problema)
 *
 *   Busca a solu��o por busca em profundidade iterativo.
 *
 *   @author Jomi Fred H�bner
 */
public class BuscaIterativo extends BuscaProfundidade {

    private Status status = new Status();
    
    /** busca sem mostrar status */
    public BuscaIterativo() {
    }
    
    /** busca mostrando status */
    public BuscaIterativo(MostraStatusConsole ms) {
        setMostra(ms);
    }
       
    public void setMostra(MostraStatusConsole ms) {
        mstatus = ms;
        ms.setStatus(status);
        status.setMostra(ms);        
    }

    public Status novoStatus() {
        status = new Status();
        if (mstatus != null) {
            mstatus.setStatus(status);
            status.setMostra(mstatus);
        }
        return status;
    }
    

    /**
     *
     * Busca a solucao por busca em profundidade iterativo.
     *                              ----------------------
     *
     */
    public Nodo busca(Estado inicial) {
        status.inicia();
        initFechados();

        int prof = 0;
        while (!parar) {
            status.profundidadeMax = prof;
            setProfMax(prof++); // indica a profundidade maxima atual
            Nodo n = super.busca(inicial); 
            status.nroVisitados += super.status.nroVisitados; // acumula das varias buscas em profundidade
            if (n != null) {
                status.termina(true);
                return n;
            }
        }

        status.termina(false);
        return null;
    }    
    
    public String toString() {
    	return "BPI - busca em profundidade iterativo";
    }
}
