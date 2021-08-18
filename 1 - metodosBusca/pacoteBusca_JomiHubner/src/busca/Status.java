package busca;

import java.util.Date;

/**
 * Contem varias informacoes de status sobre a busca
 * 
 * @author Jomi
 */
public class Status {

    int nroVisitados = 0;
    int profundidadeMax = 0; // a max prox. que a busca foi
    int tamAbertos = 0;
    Date tempoInicio;
    MostraStatusConsole ms;
    boolean resolveu = false;

    void setMostra(MostraStatusConsole ms) {
        this.ms = ms;
    }
    
    void inicia() {
        nroVisitados = 0;
        profundidadeMax = 0;
        tempoInicio = new Date();
    }

    void termina(boolean resolveu) {
        this.resolveu = true;
        if (ms != null) {
            ms.para();
        }
    }

    public boolean resolveu() {
        return resolveu;
    }
    
    public long getTempoDecorrido() {
        Date agora = new Date();
        return  agora.getTime() - tempoInicio.getTime();
    }
    
    public int getVisitados() {
        return nroVisitados;
    }
    
    public int getProfundidade() {
        return profundidadeMax;
    }
    
    /** o algoritmo pegou n para explorar de um total de s */
    public void explorando(Nodo n, int s) {
        tamAbertos = s;
        nroVisitados++;

        if (n.getProfundidade() > profundidadeMax) {
            profundidadeMax = n.getProfundidade();
        }
    }
    
}
