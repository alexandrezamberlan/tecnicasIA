package busca;

/** mostra os dados de um status de busca */
public class MostraStatusConsole extends Thread {

    private Status status;
    private boolean stop = false;
    
    public MostraStatusConsole() {
        start();        
    }
    public MostraStatusConsole(Status s) {
        setStatus(s);
        start();        
    }

    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status s) {
        this.status = s;
    }
    
    public void para() {
    	if (!stop) {
	        stop = true;
	        interrupt();
    	}
    }
    
    public void run() {
        while (!stop) {
            try {
                sleep(1000);
                if (!stop && status != null) {
                    mostra();
                }
            } catch (Exception e) {  }
        }
        mostraFim();
    }

    protected void mostraFim() {
        println(": Fim da busca. "+status.nroVisitados+" nodos visitados em "+status.getTempoDecorrido()+" mili-seg.\n");        
    }
    protected void mostra() {
        println("Status:");
        println("\t"+status.nroVisitados+" nodos visitados, nodos em aberto="+status.tamAbertos);
        println("\tProfundidade atual="+status.profundidadeMax);
        println("\tTempo decorrido="+status.getTempoDecorrido());
        /* 
        print("\nNúmero médio de sucessores="+melhor.nroMedioSucessores());
        print("\nMédia de profundidade="+melhor.getProfundidadeMedia());
        print("\nProfundidade máxima="+melhor.profundidadeMax);
        */
    }

    protected void println(String s) {
        System.out.println(s);
    }
}
