package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.Estado;
import busca.Heuristica;


/**
 * Problema do quadrado magico (ver getDescricao)
 *
 * Representa um estado do mundo: o nro que esta em cada
 * posicao do quadro.
 *
 * Nesta solucao o estado inicial e o quadro sem numeros e,
 * a cada sucessor, um novo numero e incluido.
 */
public class QuadradoMagico implements Estado, Heuristica {
    
    public String getDescricao() {
        return
        "Um quadrado magico de ordem n e um arranjo quadrado de n^2 inteiros\n"+
        "distintos dispostos de tal maneira que os numeros de uma linha\n"+
        "qualquer, de uma coluna qualquer ou da diagonal principal tem mesma\n"+
        "soma, chamada constante magica do quadrado. O quadrado e normal se os\n"+
        "n^2 numeros que o formam sao os primeiros n^2 inteiros positivos.\n\n"+
        "A constante magica do quadrado e dada por: n (n^2 + 1) / 2\n"+
        "Neste exemplo, n = 4 e a constante magica=34\n\n"+
        "Nesta versao (a), o tabuleira inicia vazio e, \n"+
        "a cada nivel, um novo numero e adicionado.\n"+
        "(tem heurustica implementada)\n";
    }
    
    static short tam = 4;
    static int   nroMagico = 34;
    public static void setTamanho(int t) {
        tam =(short)t;
        nroMagico = t*((t*t)+1)/2;
    }
    static long nroTestes = 0;
    static long totalFolhas = (fat(tam*tam) / 1000) / 1000;

    static long fat(int n) {
        if (n==0) {
            return 1;
        } else {
            return n * fat(n-1);
        }
    }
    
    
    int[][] tabuleiro = new int[tam][tam];
    int meuNro = 0; // o nro que este estado adicionou
    int h = -1; // cache de calculo de h
    
    /**
     *  cria um estado inicial (vazio: nenhum nro colocado)
     */
    public QuadradoMagico() {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                tabuleiro[l][c] = 0;
            }
        }
    }
    
    /**
     *  cria um estado inicial a partir de outro (copia)
     */
    QuadradoMagico(QuadradoMagico modelo) {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                tabuleiro[l][c] = modelo.tabuleiro[l][c];
            }
        }
        meuNro = modelo.meuNro;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof QuadradoMagico) {
            QuadradoMagico e = (QuadradoMagico)o;
            for (int l=0;l<tam;l++) {
                for (int c=0;c<tam;c++) {
                    if (this.tabuleiro[l][c] != e.tabuleiro[l][c]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    
    /** 
     * retorna o hashCode desse estado
     * (usado para poda, conjunto de fechados)
     */
    public int hashCode() {
        return toString().hashCode();
    }
    
    public boolean ehMeta() {
        // soma todas as linhas
        if (meuNro < tam*tam) {
            return false;
        }

        int s1linha = -1; // soma da 1 linha
        for (int l=0;l<tam;l++) {
            int s = 0;
            for (int c=0;c<tam;c++) {
                s += tabuleiro[l][c];
            }
            if (s1linha == -1) {
                s1linha = s;
            } else if (s1linha != s) {
                return false;
            }
        }
        
        // soma todas as colunas
        for (int c=0;c<tam;c++) {
            int s = 0;
            for (int l=0;l<tam;l++) {
                s += tabuleiro[l][c];
            }
            if (s1linha != s) {
                return false;
                
            }
        }
        
        // calcula soma da diagonal principal
        int dp = 0;
        for (int l=0;l<tam;l++) {
            dp  += tabuleiro[l][l];
        }
        if (dp != s1linha) {
            return false;
        }
        
        // calcula soma da diagonal secundaria
        int ds = 0;
        for (int l=0;l<tam;l++) {
            ds  += tabuleiro[(tam-1)-l][l];
        }
        if (ds != s1linha) {
            return false;
        }
        
        return true;
    }
    
    
    
    /**
     * gera uma lista de sucessores do nodo.
     * (coloca mais um numero no tabuleiro)
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        int seguinte = meuNro + 1;
        
        // coloca o seguinte em todas as posicoes livres
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == 0) {
                    QuadradoMagico novo = new QuadradoMagico(this);
                    novo.tabuleiro[l][c] = seguinte;
                    novo.meuNro = seguinte;
                    if (! novo.poda()) {
                        suc.add(novo);
                    }
                }
            }
        }
        //System.out.println("nroSuc="+suc.size()+",nivel="+seguinte+"; ");
        return suc;
    }
    
    /**
     * retorna true se o estado deve ser podado
     */
    protected boolean poda() {
        // se a soma das linhas e != do nroMagico, pode podar
        for (int l=0;l<tam;l++) {
            int soma = 0;
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] != 0) {
                    soma += tabuleiro[l][c];
                } else { // a linha ainda nao esta completa
                    soma = 0;
                    break;
                }
            }
            if (soma != 0 && soma != nroMagico) {
                return true;
            }
        }
        
        // se a soma das linhas e != de nroMagico, pode podar
        for (int c=0;c<tam;c++) {
            int soma = 0;
            for (int l=0;l<tam;l++) {
                if (tabuleiro[l][c] != 0) {
                    soma += tabuleiro[l][c];
                } else { // a linha ainda nao esta completa
                    soma = 0;
                    break;
                }
            }
            if (soma != 0 && soma != nroMagico) {
                return true;
            }
        }
        return false;
    }
    
    
    public String toString() {
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
            } else {
                r.append("  h()="+h()+"\n");
            }
        }
        return r.toString();
    }
    
    public int custo() {
        return 1;
    }
    
    /**
     * o desvio das somas das linhas, colunas, diagonais
     * em relacao ao objetivo
     */
    public int h() {
        if (this.h >= 0) {
            return h;
        }
        
        int[] linhas = new int[tam]; // soma das linhas
        int[] cols   = new int[tam]; // soma das colunas
        
        for (int i=0;i<tam;i++) {
            linhas[i] = 0;
            cols[i]   = 0;
        }
        
        // calcula soma das linhas e colunas
        int soma = 0;
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                linhas[l] += tabuleiro[l][c];
                cols[c]   += tabuleiro[l][c];
                soma      += tabuleiro[l][c];
            }
        }
        
        // calcula soma da diagonal principal
        int dp = 0;
        for (int l=0;l<tam;l++) {
            dp  += tabuleiro[l][l];
        }
        // calcula soma da diagonal secund�ria
        int ds = 0;
        for (int l=0;l<tam;l++) {
            ds  += tabuleiro[(tam-1)-l][l];
        }
        
        
        int media = nroMagico; //soma / tam;
        
        // calcula o desvio da media
        int desvio = 0;
        for (int i=0;i<tam;i++) {
            desvio += Math.abs(linhas[i] - media);
            desvio += Math.abs(cols[i]   - media);
        }
        desvio += Math.abs(dp - media);
        desvio += Math.abs(ds - media);
        
        this.h = desvio;
        return desvio;
    }
    
}

