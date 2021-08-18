package busca;

import java.util.List;

/**
 *   Algoritmos de Busca (geral, qquer problema)
 *   
 *   Busca a solução por "subida da montanha"
 *                        ------------------
 *
 *   @author Jomi Fred Hübner
 */
public class SubidaMontanha extends BuscaHeuristica {
    
    /** busca sem mostrar status */
    public SubidaMontanha() {
    }
    
    /** busca mostrando status */
    public SubidaMontanha(MostraStatusConsole ms) {
        super(ms);
    }

    public Nodo busca(Estado corrente) {
        status.inicia();
        initFechados();

        Estado melhor = corrente;
        while (!parar && corrente != null) {
            
            List<Estado> filhos = corrente.sucessores();
            if (filhos.size() == 0) {
                corrente = ((Aleatorio)corrente).geraAleatorio(); // começa em outro lugar aleatório
                continue;
            }

            // acha o menor h entre os filhos
            Estado melhorFilho = null;
            for (Estado e: filhos) {
                if (melhorFilho == null) {
                    melhorFilho = e;
                } else if ( ((Heuristica)e).h() < ((Heuristica)melhorFilho).h()) {
                    melhorFilho = e;
                }
            }
            
            status.nroVisitados++;
            
            // se não tem filho melhor que corrente
            if (((Heuristica)melhorFilho).h() >= ((Heuristica)corrente).h()) {
                if (corrente.ehMeta()) {
                    status.termina(true);
                    return new Nodo(corrente, null);
                } else { // máximo local
                    if (((Heuristica)corrente).h() < ((Heuristica)melhor).h()) {
                        melhor = corrente;
                    }
                    corrente = ((Aleatorio)corrente).geraAleatorio(); // começa em outro lugar aleatório
                }
            } else { // tem filho melhor que corrente
                corrente = melhorFilho;
            }
        }
        status.termina(false);
        return null;
    }
    
    public String toString() {
    	return "BSM - busca com subida da montanha";
    }
}
