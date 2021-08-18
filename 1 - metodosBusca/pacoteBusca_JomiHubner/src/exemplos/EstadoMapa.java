package exemplos;

import grafo.Grafo;
import grafo.GrafoNaoDirigido;
import grafo.Vertice;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import busca.Antecessor;
import busca.BuscaLargura;
import busca.Estado;
import busca.Nodo;

/**
 * Representa um estado do mundo para o problema de achar
 * rotas em mapas.
 * 
 * No caso o estado e uma das cidades.
 */
public class EstadoMapa implements Estado, Antecessor {
    
    public String getDescricao() {
        StringBuffer ds = new StringBuffer("Encontra rotas no mapa abaixo (o custo dos caminhos esta entre parenteses):\n");
        for (int i=0; i<nomes.length; i++) {
            ds.append("  saindo de "+nomes[i]+" para ");
            Map<Vertice,Integer> custos = mapa.getVertice(i).getCustoAdjacentes();
            for (Vertice v: custos.keySet()) {
                ds.append(nomes[v.getId()]+"("+custos.get(v)+"), ");
            }
            ds.append("\n");
        }
        return ds.toString();
    }

    /** estado: a cidade corrente */
    int cidade;
    int custo = 1;
    
    public EstadoMapa(int c)  {
        this.cidade = c;
    }
    EstadoMapa(int c, int custo)  {
        this.cidade = c;
        this.custo = custo;
    }
    
    /**
     * verifica se um estado e igual a outro
     */
    public boolean equals(Object o) {
        if (o instanceof EstadoMapa) {
            EstadoMapa e = (EstadoMapa)o;
            return e.cidade == this.cidade;
        }
        return false;
    }
    
    public int hashCode() {
        return new Integer(cidade).hashCode();
    }

    /**
     * verifica se o estado e meta
     */
    public boolean ehMeta() {
        return this.cidade == meta;
    }
    
    /**
     * Custo para geracao de um estado
     */
    public int custo() {
        return custo;
    }
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        Map<Vertice,Integer> custos = mapa.getVertice(cidade).getCustoAdjacentes();
        for (Vertice v: custos.keySet()) {
            suc.add(new EstadoMapa(v.getId(), custos.get(v)));
        }
        return suc;
    }
    
    public List<Estado> antecessores() {
        return sucessores();
    }
    
    public String toString() {
        return nomes[cidade] + " ";
    }
    
    public static int getMeta() {
        return meta;
    }
    public static void setMeta(int cidade) {
        meta = cidade;
    }

    /** informacao estatica (o mapa) */
    public static Grafo mapa = new GrafoNaoDirigido();
    static {
        for (int i=0; i<=15; i++) {
            mapa.criaVertice(i);
        }
        mapa.criaAresta(0,1,3);
        mapa.criaAresta(0,2,6);

        mapa.criaAresta(1,9,3);
        mapa.criaAresta(1,7,3);
        
        mapa.criaAresta(2,13,2);
        mapa.criaAresta(2,14,2);
        mapa.criaAresta(2,3,3);
        mapa.criaAresta(2,6,2);

        mapa.criaAresta(3,4,1);
        mapa.criaAresta(3,5,1);

        mapa.criaAresta(4,5,1);
        mapa.criaAresta(4,8,2);
        mapa.criaAresta(4,11,14); // e -> m

        mapa.criaAresta(5,6,1);
        
        mapa.criaAresta(6,7,2);
        
        mapa.criaAresta(7,8,2);
        mapa.criaAresta(7,9,4);

        mapa.criaAresta(9,10,1);
        mapa.criaAresta(9,12,3);

        mapa.criaAresta(11, 12, 2);
        mapa.criaAresta(11, 15, 1);
    }
    
    // cidades
    // a, b, c, d, e, f, g, h, i, k,  l, m,  n,   o,  p,  x
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    public final static char nomes[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k',  'l', 'm',  'n', 'o',  'p',  'x' };    

    private static int meta;
    
    
    public static void main(String[] a) {
        EstadoMapa inicial = new EstadoMapa(8);
        EstadoMapa.setMeta(15);
        System.out.println(inicial.getDescricao());
        System.out.println("estado inicial= "+inicial);

        Nodo s = new BuscaLargura().busca(inicial);
        if (s != null) {
            System.out.println("solucao = "+s.montaCaminho());
            System.out.println("\toperacoes = "+s.getProfundidade());
            System.out.println("\tcusto = "+s.g());
        }
        
        // test
        /*
        Queue<Nodo> abertos = new PriorityQueue<Nodo>();
        abertos.add(new Nodo(new EstadoMapa(4,50),null));
        abertos.add(new Nodo(new EstadoMapa(1,300),null));
        abertos.add(new Nodo(new EstadoMapa(2,100),null));
        abertos.add(new Nodo(new EstadoMapa(3,200),null));
        abertos.add(new Nodo(new EstadoMapa(5,2),null));
        while (!abertos.isEmpty()) {
            System.out.println(abertos.remove());
        }
        */
       
    }
}

