package busca;

import java.util.LinkedList;
import java.util.List;

/**
 * Busca Bi-direcional.
 *
 *  @author Jomi Fred Hübner
 */
public class BuscaBidirecional extends Busca {
    
    /** busca sem mostrar status */
    public BuscaBidirecional() {
    }
    
    /** busca mostrando status */
    public BuscaBidirecional(MostraStatusConsole ms) {
        super(ms);
    }

    /**
     *
     * Busca a solução por busca em Bi-direcional.
     *                              -------------
     */
    public Nodo busca(Estado inicial, Estado meta) {
        status.inicia();
        usarFechado = false; // tem que usar poda so por ascendencia! nao pode usar fechados
        
        // TODO: ter duas tabelas de fechados, uma para cada lado!
        
        // TODO: usar tree para ter ordem (g) e ser rapido de achar
        List<Nodo> abertosCima  = new LinkedList<Nodo>();
        List<Nodo> abertosBaixo = new LinkedList<Nodo>();
        
        abertosCima.add(new Nodo(inicial, null));
        Nodo nodoMeta = new Nodo(meta, null);
        abertosBaixo.add(nodoMeta);
        
        while (!parar && abertosCima.size() > 0 && abertosBaixo.size() > 0) {
            
            // incrementa em cima
            //
            Nodo n = abertosCima.remove(0);
            status.explorando(n, abertosCima.size()+abertosBaixo.size());
            // ve se tem n na borda da árvore de baixo
            int io = abertosBaixo.indexOf(n); 
            if (io >= 0) {
                Nodo nb = abertosBaixo.get(io);
                nb.invertePaternidade();
                nb.pai = n.pai;
                nodoMeta.setProfundidade();
                status.termina(true);
                return nodoMeta;
            }
            abertosCima.addAll( sucessores(n) );
            
            // incrementa para baixo
            //
            n = abertosBaixo.remove(0);
            status.explorando(n, abertosCima.size()+abertosBaixo.size());
            // ve se tem n na borda da árvore de cima
            io = abertosCima.indexOf(n);
            if (io >= 0) {
                Nodo nc = abertosCima.get(io);
                n.invertePaternidade();
                n.pai = nc.pai;
                nodoMeta.setProfundidade();
                status.termina(true);
                return nodoMeta;
            }
            abertosBaixo.addAll( antecessores(n) );
            
        }
        status.termina(false);
        return null;
    }

    
    public Nodo busca(Estado inicial) throws Exception {
    	throw new Exception("Esta classe não implementa a busca com um único parâmetro"); 
    }
    
    public String toString() {
    	return "BBD - busca bi-direcional";
    }
}
