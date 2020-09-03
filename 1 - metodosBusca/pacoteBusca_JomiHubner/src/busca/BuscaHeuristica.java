package busca;

import java.util.Comparator;

/**
 *   Algoritmos de Busca heurística
 *
 *   @author Jomi Fred Hübner
 */
public abstract class BuscaHeuristica extends Busca {
    
    /** busca sem mostrar status */
    public BuscaHeuristica() {
    }
    
    /** busca mostrando status */
    public BuscaHeuristica(MostraStatusConsole ms) {
        super(ms);
    }

    /** comparador para ordenar os nodos por F */
    Comparator<Nodo> getNodoComparatorF() {
        return new Comparator<Nodo>() {
            public int compare(Nodo no1, Nodo no2) {
                try {
                    //Heuristica eo1 = (Heuristica)no1.estado;
                    //Heuristica eo2 = (Heuristica)no2.estado;
                    int f1 = no1.f();
                    int f2 = no2.f();
                    if (f1 > f2) {
                        return 1;
                    } else if (f1 == f2) {
                        return 0; 
                    } else {
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
    }

    /** comparador para ordenar os Estados por h */
    /*<Heuristica> getEstadoComparatorH() {
        return new Comparator<Heuristica>() {
            public int compare(Heuristica eo1, Heuristica eo2) {
                try {
                	//Heuristica eo1 = (Heuristica)o1;
                    //Heuristica eo2 = (Heuristica)o2;
                    int h1 = eo1.h();
                    int h2 = eo2.h();
                    if (h1 > h2) {
                        return 1;
                    } else if (h1 == h2) {
                        return 0; 
                    } else {
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
    }
    */
}
