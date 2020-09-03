package exemplos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import busca.Aleatorio;
import busca.Estado;
import busca.Nodo;
import busca.SubidaMontanha;

/**
 * Problema do quadrado mágico (ver getDescricao)
 *
 * Representa um estado do mundo: o nro que está em cada
 * posição do quadro.
 *
 *
 * Nesta solução o estado inicial é o quadro com os números
 * distribuídos aleatoriamente e os sucessores são gerados
 * por trocas de posição entre números.
 */
public class QuadradoMagicoB extends QuadradoMagico implements Aleatorio {
    
    public String getDescricao() {
        return
        "Um quadrado mágico de ordem n é um arranjo quadrado de n² inteiros\n"+
        "distintos dispostos de tal maneira que os números de uma linha\n"+
        "qualquer, de uma coluna qualquer ou da diagonal principal têm mesma\n"+
        "soma, chamada constante mágica do quadrado. O quadrado é normal se os\n"+
        "n² números que o formam são os primeiros n² inteiros positivos.\n\n"+
        "A constante mágica do quadrado é dada por: n (n² + 1) / 2\n"+
        "Neste exemplo, n = 4 e a constante mágica=34\n\n"+
        "Nesta versão (b), o tabuleiro inicia com os números\n"+
        "aleatoriamente posicionados e cada operação\n"+
        "troca dois números de lugar\n"+
        "(tem heurística e geração de estados aleatórios implementado)\n";
    }
    
    /**
     *  cria um estado inicial (aleatório)
     */
    public QuadradoMagicoB() {
        for (int i=1; i<=tam*tam; i++) {
            // tenta até achar um posição livre
            int l = Math.round( (float)(Math.random()*(tam-1)) );
            int c = Math.round( (float)(Math.random()*(tam-1)) );
            while (tabuleiro[l][c] != 0) {
                l = Math.round( (float)(Math.random()*(tam-1)) );
                c = Math.round( (float)(Math.random()*(tam-1)) );
            }
            tabuleiro[l][c] = i;
        }
        meuNro = tam*tam;
    }
    
    /**
     *  cria um estado inicial a partir de outro (copia)
     */
    QuadradoMagicoB(QuadradoMagico modelo) {
        super(modelo);
    }
    
    public Estado geraAleatorio() {
        return new QuadradoMagicoB();
    }
    
    /**
     * gera uma lista de sucessores do nodo.
     * (troca dois números de posição no tabuleiro)
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new ArrayList<Estado>(); // a lista de sucessores
        
        // coloca o seguinte em todas as posições livres
        for (int l=0;l<tam-1;l++) {
            for (int c=0;c<tam-1;c++) {
                // troca com o próximo na linha
                QuadradoMagicoB novo = new QuadradoMagicoB(this);
                int temp = novo.tabuleiro[l][c];
                novo.tabuleiro[l][c] = novo.tabuleiro[l+1][c];
                novo.tabuleiro[l+1][c] = temp;
                suc.add(novo);
                
                // troca com o debaixo
                novo = new QuadradoMagicoB(this);
                temp = novo.tabuleiro[l][c];
                novo.tabuleiro[l][c] = novo.tabuleiro[l][c+1];
                novo.tabuleiro[l][c+1] = temp;
                suc.add(novo);
            }
        }
        
        return suc;
    }
    
    public static void main(String[] a) {
        QuadradoMagicoB.tam = 4;
        
        //Estado inicial  = new EstadoSoma34a();
        Estado inicial  = new QuadradoMagicoB();
        Nodo n;
        
        System.out.println("Começou em "+new Date());
        
        System.out.println("Estado inicial "+inicial);
        
        //n = Busca.buscaLargura(inicial, null); // (dá "out of memory")
        //n = Busca.buscaProfRec(inicial, null, 18); // (demora muito)
        
        // chama busca em profundidade iterativo
        // (como se sabe que a solução esta em 16 níveis, não
        // faz sentido busca em prof. iterativo)
        //n = Busca.buscaProfIterativo(inicial, null);
        
        //n = Busca.buscaHeuristica(inicial, null);
        /*
        if (n == null) {
            System.out.println("sem solução!");
        } else {
            System.out.println("solução:\n" + n.montaCaminho() + "\n\n");
        }
         */
        
        n = new SubidaMontanha().busca(inicial);
        System.out.println("solução:\n" + n.getEstado() + "\n\n");
        System.out.println("Terminou em "+new Date());
    }
}
