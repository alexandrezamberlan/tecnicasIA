import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.Nodo;
import busca.MostraStatusConsole;

public class Jarras implements Estado {
    
    @Override
    public String getDescricao() {
        return " Problema das Jarras.... ";
    }
    Random gerador = new Random();
    final int TAM1 = 6; //capacidade da jarra1
    final int TAM2 = 5; //capacidade da jarra2
    
    final int jarra1, jarra2;
    final String op; // operacao que gerou o estado
    
    /** construtor para o estado, recebe cada valor de atributo */
    public Jarras(int jarra1, int jarra2, String op) {
        this.jarra1 = jarra1;
        this.jarra2 = jarra2;
        this.op = op;
    }
    
    /**
     * verifica se o estado final foi atingido
     */
    @Override
    public boolean ehMeta() {
        return this.jarra1 == 2 && this.jarra2 == 0 ||
               this.jarra1 == 0 && this.jarra2 == 2;
    }
    
    /**
     * Custo para geracao do estado
     */
    @Override
    public int custo() {
        return 1;
    }
    
    /**
     * gera uma lista de sucessores do nodo.
     * @return visitados
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores
        
        encherJarra1(visitados);
        encherJarra2(visitados);
        esvaziarJarra1(visitados);
        esvaziarJarra2(visitados);
        despejarJarra1EmJarra2(visitados);
        despejarJarra2EmJarra1(visitados);
        
        return visitados;
    }
    
    private void encherJarra1(List<Estado> visitados) {
        Jarras novo = new Jarras(TAM1,this.jarra2,"Enchendo a jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void encherJarra2(List<Estado> visitados) {
        Jarras novo = new Jarras(this.jarra1,TAM2,"Enchendo a jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void esvaziarJarra1(List<Estado> visitados) {
        Jarras novo = new Jarras(0,this.jarra2,"Esvaziando a jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void esvaziarJarra2(List<Estado> visitados) {
        Jarras novo = new Jarras(this.jarra1,0,"Esvaziando a jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void despejarJarra1EmJarra2(List<Estado> visitados) {
        int j1 = this.jarra1;  //clone
        int j2 = this.jarra2;  //clone para gerar novo estado
        
        if (j1 > (TAM2 - j2)){
            j1 = j1 - (TAM2 - j2);
            j2 = TAM2;
        } else {
            j2 = j2 + j1;
            j1 = 0;
        }
        Jarras novo = new Jarras(j1,j2,"Despejando jarra1 em jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void despejarJarra2EmJarra1(List<Estado> visitados) {
        int j1 = this.jarra1;  //clone
        int j2 = this.jarra2; //clone para gerar novo estado
        
        if (j2 > (TAM1 - j1)){
            j2 = j2 - (TAM1 - j1);
            j1 = TAM1;
        } else {
            j1 = j2 + j1;
            j2 = 0;
        }
        Jarras novo = new Jarras(j1,j2,"Despejando jarra2 em jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
 
    /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Jarras) {
            Jarras e = (Jarras)o;
            return this.jarra1 == e.jarra1 && 
                   this.jarra2 == e.jarra2;
        }
        return false;
    }
    
    /** 
     * retorna o hashCode do estado
     * (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() { 
        return (""+this.jarra1 + this.jarra2).hashCode();
    }
    
    @Override
    public String toString() {
        return "(" + this.jarra1 + "," + this.jarra2 + ") "  + op + "\n";
    }
    
    public static void main(String[] a) {
        Jarras estadoInicial = new Jarras(0,0, "estado inicial");
        
        // chama busca em largura
        System.out.println("busca em ....");
        Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
    }
}

