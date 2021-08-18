package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.Antecessor;
import busca.BuscaLargura;
import busca.Estado;
import busca.Heuristica;
import busca.MostraStatusConsole;
import busca.Nodo;

/**
 * Representa um estado do mundo: onde estao os numeros
 */
public class Estado8Puzzle implements Estado, Heuristica, Antecessor {
    
    public String getDescricao() {
        return
        "Este problema consiste em posicionar 8\n" +
        "numeros em um tabuleiro 3x3 na seguinte disposcao:\n"+
        "1 2 3\n"+
        "8   4\n"+
        "7 6 5\n"+
        "sendo que o espaco pode se mover.\n"+
        "(bom candidato a busca bi-direcional)\n";
    }
    
    public static final short tam = 3;
    
    int[][] tabuleiro = new int[tam][tam];
    int colBranco = -1;
    int linBranco = -1;
    
    /**
     *  cria um estado inicial (aleatorio)
     */
    public Estado8Puzzle() {
        for (int r=0;r< (tam*tam);r++) {
            // tenta ata achar uma posicao livre
            int l = Math.round( (float)(Math.random()*(tam-1)) );
            int c = Math.round( (float)(Math.random()*(tam-1)) );
            while (tabuleiro[l][c] != 0) {
                l = Math.round( (float)(Math.random()*(tam-1)) );
                c = Math.round( (float)(Math.random()*(tam-1)) );
            }
            tabuleiro[l][c] = r;
        }
        setPosBranco();
    }
    
    /**
     * cria um estado igual a outro
     */
    Estado8Puzzle(int[][] p) {
        for (int l=0;l<tam;l++) {
            //System.arraycopy(p,0,tabuleiro,0,tam);
            for (int c=0;c<tam;c++) {
                tabuleiro[l][c] = p[l][c];
            }
            
        }
    }
    
    void setPosBranco() {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == 0) {
                    colBranco = c;
                    linBranco = l;
                }
            }
        }
    }
    
    
    /**
     * verifica se um estado e igual a outro
     */
    public boolean equals(Object o) {
    	if (o instanceof Estado8Puzzle) {
            Estado8Puzzle e = (Estado8Puzzle)o;
            for (int l=0;l<tam;l++) {
                for (int c=0;c<tam;c++) {
                    if (tabuleiro[l][c] != e.tabuleiro[l][c]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * verifica se o estado e meta
     */
    public boolean ehMeta() {
        return this.equals(estadoMeta);
    }
    
    
    /**
     * Heuristica: calcula a quantidade de numeros fora do lugar
     */
    public int h() {
        return h2() + h3();
    }
    
    /**
     * Heuristica: calcula a quantidade de numeros fora do lugar
     */
    public int h1() {
        int fora = 0;
        
        if (tabuleiro[0][0] != 1) fora++;
        if (tabuleiro[0][1] != 2) fora++;
        if (tabuleiro[0][2] != 3) fora++;
        if (tabuleiro[1][0] != 8) fora++;
        if (tabuleiro[1][1] != 0) fora++;
        if (tabuleiro[1][2] != 4) fora++;
        if (tabuleiro[2][0] != 7) fora++;
        if (tabuleiro[2][1] != 6) fora++;
        if (tabuleiro[2][2] != 5) fora++;
        
        return fora;
    }
    
    
    /**
     * Heuristica: calcula a distancia de cada numero ate seu lugar
     */
    public int h2() {
        int fora = 0;
        
        for (int n=0; n<(tam*tam); n++) {
            int l = getLinNro(n);
            int c = getColNro(n);
            int lMeta = estadoMeta.getLinNro(n);
            int cMeta = estadoMeta.getColNro(n);
            fora += Math.abs(l - lMeta);
            fora += Math.abs(c - cMeta);
        }
        return fora;
    }
    
    
    /**
     * Heuristica: conta os numeros fora da sequencia circular
     */
    public int h3() {
        int fora = 0;
        
        for (int n=1; n<(tam*tam); n++) {
            int l = getLinNro(n);
            int c = getColNro(n);
            int lAnt = 0;
            if (l==0 && c==0) lAnt = 1;
            else if (l==0 && c==1) lAnt = 0;
            else if (l==0 && c==2) lAnt = 0;
            else if (l==1 && c==0) lAnt = 2;
            else if (l==1 && c==2) lAnt = 0;
            else if (l==2 && c==0) lAnt = 2;
            else if (l==2 && c==1) lAnt = 2;
            else if (l==2 && c==2) lAnt = 1;
            
            int cAnt = 0;
            if (l==0 && c==0) cAnt = 0;
            else if (l==0 && c==1) cAnt = 0;
            else if (l==0 && c==2) cAnt = 1;
            else if (l==1 && c==0) cAnt = 0;
            else if (l==1 && c==2) cAnt = 2;
            else if (l==2 && c==0) cAnt = 1;
            else if (l==2 && c==1) cAnt = 2;
            else if (l==2 && c==2) cAnt = 2;
            
            int nroAnt = tabuleiro[lAnt][cAnt];
            
            if (n == 1) {
                if (nroAnt != 8) {
                    fora += 1;
                }
            } else if (nroAnt+1 != n) {
                fora += 1;
            }
        }
        return fora;
    }
    
    /** retorna a coluna de um numero */
    int getColNro(int n) {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == n) {
                    return c;
                }
            }
        }
        return -1;
    }
    
    /** retorna a linha de um numero */
    int getLinNro(int n) {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == n) {
                    return l;
                }
            }
        }
        return -1;
    }
    
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        // pra cima
        if (linBranco > 0) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco-1][colBranco] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco-1][colBranco];
            novo.linBranco = linBranco-1;
            novo.colBranco = colBranco;
            suc.add(novo);
        }
        
        // pra baixo
        if (linBranco < tam-1) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco+1][colBranco] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco+1][colBranco];
            novo.linBranco = linBranco+1;
            novo.colBranco = colBranco;
            suc.add(novo);
        }
        
        // pra esq
        if (colBranco > 0) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco][colBranco-1] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco][colBranco-1];
            novo.linBranco = linBranco;
            novo.colBranco = colBranco-1;
            suc.add(novo);
        }
        
        // pra dir
        if (colBranco < tam-1) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco][colBranco+1] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco][colBranco+1];
            novo.linBranco = linBranco;
            novo.colBranco = colBranco+1;
            suc.add(novo);
        }
        
        return suc;
    }
    
    public List<Estado> antecessores() {
        return sucessores();
    }
    
    public boolean temSolucao() {
        // se um nro par de trocar levar o estado corrente para a meta, 
    	// entao tem solucao
        Estado8Puzzle meta = estadoMeta;
        
        Estado8Puzzle t = new Estado8Puzzle(tabuleiro);
        
        // move o branco para o centro
        int cb = t.getColNro(0);
        int lb = t.getLinNro(0);
        
        // move linha
        while ( lb < meta.getLinNro(0)) {
            troca( t.tabuleiro, lb, cb, lb+1, cb);
            lb++;
        }
        while ( lb > meta.getLinNro(0)) {
            troca( t.tabuleiro, lb, cb, lb-1, cb);
            lb--;
        }
        
        // move coluna
        while ( cb < meta.getColNro(0)) {
            troca( t.tabuleiro, lb, cb, lb, cb+1);
            cb++;
        }
        while ( cb > meta.getColNro(0)) {
            troca( t.tabuleiro, lb, cb, lb, cb-1);
            cb--;
        }
        
        
        int nroTrocas = 0;
        for (int l = 0; l < tam; l++) {
            for (int c = 0; c < tam; c++) {
                // se meta for diferente de tab, troca
                if (t.tabuleiro[l][c] != meta.tabuleiro[l][c]) {
                    int vlOk = meta.tabuleiro[l][c];
                    //Estado8Puzzle t = new Estado8Puzzle(tab);
                    troca(t.tabuleiro, l, c, t.getLinNro(vlOk), t.getColNro(vlOk));
                    nroTrocas++;
                }
            }
        }
        
        return nroTrocas % 2 == 0;
        
    }
    
    private void troca(int[][] tab, int l1, int c1, int l2, int c2) {
        int bak = tab[l2][c2];
        tab[l2][c2] = tab[l1][c1];
        tab[l1][c1] = bak;
    }
    
    private String toStringCache = null;
    public String toString() {
        if (toStringCache == null) {
            StringBuffer r = new StringBuffer("\n");
            for (int i=0;i<tam;i++) {
                for (int j=0;j<tam;j++) {
                    r.append(tabuleiro[i][j]);
                    if (j+1<tam) {
                        r.append(" ");
                    }
                }
                if (i+1<tam) {
                    r.append("\n");
                }
            }
            toStringCache = r + "\n";
        }
        return toStringCache;
    }
    
    
    /**
     * Custo para geracao de um estado
     */
    public int custo() {
        return 1;
    }
    
    
    static public Estado8Puzzle getEstadoFacil() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{8,1,3},{0,7,2},{6,5,4}});
        e8.setPosBranco();
        return e8;
    }
    
    static public Estado8Puzzle getEstadoDificil() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{7,8,6},{2,3,5},{1,4,0}} );
        e8.setPosBranco();
        return e8;
    }
    
    static public Estado8Puzzle getEstadoMuitoDificil() {
        //Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{3,4,8},{1,2,5},{7,0,6}} );
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{2,3,4},{1,0,5},{8,7,6}} );
        e8.setPosBranco();
        return e8;
    }
    
    
    
    public static Estado8Puzzle getEstadoMeta() {
        return estadoMeta;
    }
    
    private final static Estado8Puzzle estadoMeta = setEstadoMeta();
    private static Estado8Puzzle setEstadoMeta() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{1,2,3},{8,0,4},{7,6,5}});
        e8.setPosBranco();
        return e8;
    }
    
    public static void main(String[] a) {
        //Estado8Puzzle e8 = getEstadoFacil();
        Estado8Puzzle e8 = getEstadoDificil();
        //Estado8Puzzle e8 = getEstadoMuitoDificil();
        System.out.println("estado inicial (h="+((Heuristica)e8).h()+") ="+e8);
        
        if (! e8.temSolucao()) {
            System.out.println(e8+"nao tem solucao!");
            return;
        }
        
        //Nodo s = new AEstrela().busca(e8);
        Nodo s = new BuscaLargura(new MostraStatusConsole()).busca(e8);
        //Nodo s = new BuscaIterativo(new MostraStatusConsole()).busca(e8);
        //Nodo s = new BuscaProfundidade(25,new MostraStatusConsole()).busca(e8);
        //Nodo s = new BuscaBidirecional(new MostraStatusConsole()).busca(e8, getEstadoMeta());
        if (s != null) {
            System.out.println("solucao ("+s.getProfundidade()+")= "+s.montaCaminho());
        }        
    }
}

