package busca;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Busca a solução por busca em largura.
 *
 *  @author Jomi Fred Hübner
 */
public class BuscaLargura extends Busca {
    
    /** busca sem mostrar status */
    public BuscaLargura() {
    }
    
    /** busca mostrando status */
    public BuscaLargura(MostraStatusConsole ms) {
        super(ms);
    }

    public Nodo busca(Estado inicial) {
        status.inicia();
        initFechados();
       
        Queue<Nodo> abertos = new PriorityQueue<Nodo>();
        
        abertos.add(new Nodo(inicial, null));
        
        while (!parar && abertos.size() > 0) {
            
            //System.out.print("abertos "+abertos);
            Nodo n = abertos.remove();
            //System.out.println("pegando "+n);
            status.explorando(n, abertos.size());
            if (n.estado.ehMeta()) {
                status.termina(true);
                return n;
            }
                        
            abertos.addAll( sucessores(n) );
        }
        status.termina(false);
        return null;
    }
    
    public String toString() {
    	return "BL - Busca em Largura";
    }
}
