package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.Aleatorio;
import busca.Estado;
import busca.Heuristica;
import busca.MostraStatusConsole;
import busca.Nodo;
import busca.SubidaMontanha;


/**
 * Representa um estado do mundo: onde est�o as rainhas
 */
public class EstadoRainhas implements Estado, Heuristica, Aleatorio {
    
    public String getDescricao() {
        return
        "Este problema consiste em posicionar 8\n" +
        "rainhas do jogo de xadrez em um tabuleiro,\n"+
        "sendo que nenhuma pode atacar a outra.\n"+
        "(tem heuristica implementada)\n";
    }
    
    static short tam = 8;
    public static void setTamanho(int t) {
        tam = (short)t;
    }
    
    // rainha x posicao (0=linha x 1=coluna)
    int[][] posicoes = new int[tam][2];
    
    /**
     *  cria um estado inicial (aleat�rio)
     */
    public EstadoRainhas() {
        for (int r=0;r<tam;r++) {
            // tenta ate achar uma posicao livre
            int l = Math.round( (float)(Math.random()*(tam-1)) );
            int c = Math.round( (float)(Math.random()*(tam-1)) );
            while (getPos(l,c) != -1) {
                l = Math.round( (float)(Math.random()*(tam-1)) );
                c = Math.round( (float)(Math.random()*(tam-1)) );
            }
            setPosRainha(r,l,c);
        }
    }
    
    /**
     * cria um estado igual a outro
     */
    EstadoRainhas(int[][] p) {
        for (int r=0;r<tam;r++) {
            posicoes[r][0] = p[r][0];
            posicoes[r][1] = p[r][1];
        }
    }
    
    /**
     * gera um estado aleatorio
     */
    public Estado geraAleatorio() {
        return new EstadoRainhas();
    }
    

    public void setPosRainha(int r, int l, int c) {
        posicoes[r][0] = l;
        posicoes[r][1] = c;
    }
    
    
    /**
     * retorna a rainha que esta em l, c ou -1 se estiver vazio
     */
    public int getPos(int l, int c) {
        for (int r=0;r<tam;r++) {
            if (posicoes[r][0] == l && posicoes[r][1] == c) {
                return r;
            }
        }
        return -1;
    }
    

    /**
     * retorna a rainha que esta em l,c ou "_" se estiver vazio
     */
    public String getPosStr(int l, int c) {
        int r = getPos(l,c);
        if (r == -1) {
            return "_";
        } else {
            return ""+r;
        }
    }
    
    /**
     * verifica se um estado � igual a outro
     */
    public boolean equals(Object o) {
        try {
            EstadoRainhas e = (EstadoRainhas)o;
            for (short r=0;r<tam;r++) { // para cada rainha
                if (posicoes[r][0] != e.posicoes[r][0]) {
                    return false;
                }
                if (posicoes[r][1] != e.posicoes[r][1]) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {}
        return false;
    }
    
    /** 
     * retorna o hashCode desse estado
     * (usado para poda, conjunto de fechados)
     */
    public int hashCode() {
        return toString().hashCode();
    }
    
    
    /**
     * verifica se o estado � meta
     */
    public boolean ehMeta() {
        return h() == 0;
    }
    
    
    /**
     * Heuristica: calcula quantos ataques existe no jogo
     */
    public int h() {
        int ataques = 0;
        
        for (short r=0;r<tam;r++) { // para cada rainha
            
            // linha livre
            for (int c=0; c<tam; c++) {
                int naPos = getPos(posicoes[r][0],c);
                if (naPos != -1) { // n�o esta livre
                    if (naPos != r) { // nao � r que esta la
                        ataques++;
                    }
                }
            }
            
            // coluna livre
            for (int l=0; l<tam; l++) {
                int naPos = getPos(l,posicoes[r][1]);
                if (naPos != -1) { // n�o esta livre
                    if (naPos != r) { // nao � r que esta la
                        ataques++;
                    }
                }
            }
            
            // testa as diagonais principais
            int l=Math.max(posicoes[r][0]-posicoes[r][1],0);
            int c=Math.max(posicoes[r][1]-posicoes[r][0],0);
            for (;
            l<tam && c<tam;
            l++, c++) {
                
                int naPos = getPos(l,c);
                if (naPos != -1) { // n�o esta livre
                    if (naPos != r) { // nao � r que esta la
                        ataques++;
                    }
                }
                
            }
            
            // testa as diagonais secund�rias
            l=Math.min(posicoes[r][0]+posicoes[r][1],tam-1);
            c=Math.max(posicoes[r][1]-((tam-1)-posicoes[r][0]),0);
            for (; l>=0 && c<tam; l--, c++) {
                
                int naPos = getPos(l,c);
                if (naPos != -1) { // n�o esta livre
                    if (naPos != r) { // nao � r que esta la
                        ataques++;
                    }
                }
            }
        }
        
        return ataques;
    }
    
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        for (short r=0;r<tam;r++) { // jogadas para rainha r
            
            // movimentos na linha
            int inc = 1; // para direira
            int fixo = 0; // linha
            int var  = 1; // coluna
            for (int i=posicoes[r][var]+inc; i < tam && i >= 0; i += inc) {
                if (getPos(posicoes[r][fixo],i) == -1) { // esta livre
                    EstadoRainhas novo = new EstadoRainhas(posicoes);
                    novo.setPosRainha(r,posicoes[r][fixo],i);
                    suc.add(novo);
                } else { // bateu noutra pe�a
                    break;
                }
            }
            
            inc = -1; // para esquerda
            fixo = 0; // linha
            var  = 1; // coluna
            for (int i=posicoes[r][var]+inc; i < tam && i >= 0; i += inc) {
                if (getPos(posicoes[r][fixo],i) == -1) { // esta livre
                    EstadoRainhas novo = new EstadoRainhas(posicoes);
                    novo.setPosRainha(r,posicoes[r][fixo],i);
                    suc.add(novo);
                } else { // bateu noutra pe�a
                    break;
                }
            }
            
            // movimentos na coluna
            inc = 1; // para baixo
            fixo = 1; // coluna
            var  = 0; // linha
            for (int i=posicoes[r][var]+inc; i < tam && i >= 0; i += inc) {
                if (getPos(i,posicoes[r][fixo]) == -1) { // esta livre
                    EstadoRainhas novo = new EstadoRainhas(posicoes);
                    novo.setPosRainha(r,i,posicoes[r][fixo]);
                    suc.add(novo);
                } else { // bateu noutra pe�a
                    break;
                }
            }
            inc = -1; // para cima
            fixo = 1; // coluna
            var  = 0; // linha
            for (int i=posicoes[r][var]+inc; i < tam && i >= 0; i += inc) {
                if (getPos(i,posicoes[r][fixo]) == -1) { // esta livre
                    EstadoRainhas novo = new EstadoRainhas(posicoes);
                    novo.setPosRainha(r,i,posicoes[r][fixo]);
                    suc.add(novo);
                } else { // bateu noutra pe�a
                    break;
                }
            }
        }
        
        return suc;
    }
    
    
    public String toString() {
        StringBuffer r = new StringBuffer("\n");
        for (int i=0;i<tam;i++) {
            for (int j=0;j<tam;j++) {
                r.append(getPosStr(i,j));
                if (j+1<tam) {
                    r.append(" ");
                }
            }
            r.append("\n");
        }
        return r.toString();
    }
    
    
    public int custo() {
        return 1;
    }
    
    public static void main(String[] a) {
        // Tamanho do tabuleiro
        EstadoRainhas.tam = 8;
        
        Estado inicial = new EstadoRainhas(); // um estado aleat�rio
        System.out.println("Estado inicial:"+inicial+"\n");
        
        Nodo n = null;
        // os tr�s m�todos seguintes n�o conseguem
        // resolver o problema das ra�nhas
        //n = Busca.buscaLargura(inicial, null);
        //n = Busca.buscaProfRec(inicial, null, 10);
        //n = Busca.buscaProfIterativo(inicial, null);
                /*
                if (n == null) {
                System.out.println("sem solu��o!");
                } else {
                System.out.println("solu��o:\n" + n.montaCaminho() + "\n\n");
                }
                 */
        
        // a subida da montanha consegue resolver
        n = new SubidaMontanha(new MostraStatusConsole()).busca(inicial);
        System.out.println("solu��o:\n" + n.getEstado() + "\n\n");
    }
    
}

