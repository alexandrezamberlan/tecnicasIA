import java.util.LinkedList;
import java.util.List;

import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;
import java.util.HashSet;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Hannoi implements Estado {
    
    @Override
    public String getDescricao() {
        return " Problema das torres de Hannoi.... ";
    }
    final Stack torre1, torre2, torre3;
    final String op; // operacao que gerou o estado
    
    /** construtor para o estado, recebe cada valor de atributo */
    public Hannoi(Stack torre1, Stack torre2, Stack torre3, String op) {
        this.torre1 = torre1;
        this.torre2 = torre2;
        this.torre3 = torre3;
        
        this.op = op;
    }
    
    /**
     * verifica se o estado final foi atingido
     */
    @Override
    public boolean ehMeta() {
        return this.torre1.isEmpty() && this.torre2.isEmpty();
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
        HashSet<Estado> visitadosTemp = new HashSet<>();
        
        moverTorre1_Torre2(visitadosTemp);
        moverTorre1_Torre3(visitadosTemp);
        moverTorre2_Torre1(visitadosTemp);
        moverTorre2_Torre3(visitadosTemp);
        moverTorre3_Torre1(visitadosTemp);
        moverTorre3_Torre2(visitadosTemp);
        
        visitados.clear();
        visitados.addAll(visitadosTemp);
        
        return visitados;
    }
    
    private boolean ehValido(Stack origem, Stack destino) {
        if (origem.isEmpty() ||
            (!origem.isEmpty() && !destino.isEmpty() && (int)origem.peek() > (int)destino.peek())) {
            return false;
        }
        
        return true;
    }
    
    private void moverTorre1_Torre2(HashSet<Estado> visitados) {
        Stack origem = (Stack)this.torre1.clone();
        Stack destino = (Stack)this.torre2.clone();
        
        if (ehValido(origem, destino)){
            destino.push(origem.pop());
            Hannoi novo = new Hannoi(origem, destino, (Stack)this.torre3.clone(), "Movendo torre1 para torre2");
            visitados.add(novo);
        }
    }
    
    private void moverTorre1_Torre3(HashSet<Estado> visitados) {
        Stack origem = (Stack)this.torre1.clone();
        Stack destino = (Stack)this.torre3.clone();
        
        if (ehValido(origem, destino)){
            destino.push(origem.pop());
            Hannoi novo = new Hannoi(origem, (Stack)this.torre2.clone(), destino, "Movendo torre1 para torre3");
            visitados.add(novo);
        }
    }
    
    private void moverTorre2_Torre1(HashSet<Estado> visitados) {
        Stack origem = (Stack)this.torre2.clone();
        Stack destino = (Stack)this.torre1.clone();
        
        if (ehValido(origem, destino)){
            destino.push(origem.pop());
            Hannoi novo = new Hannoi(destino, origem, (Stack)this.torre3.clone(), "Movendo torre2 para torre1");
            visitados.add(novo);
        }
    }
    
    private void moverTorre2_Torre3(HashSet<Estado> visitados) {
        Stack origem = (Stack)this.torre2.clone();
        Stack destino = (Stack)this.torre3.clone();
        
        if (ehValido(origem, destino)){
            destino.push(origem.pop());
            Hannoi novo = new Hannoi((Stack)this.torre1.clone(), origem, destino, "Movendo torre2 para torre3");
            visitados.add(novo);
        }
    }
    
    private void moverTorre3_Torre1(HashSet<Estado> visitados) {
        Stack origem = (Stack)this.torre3.clone();
        Stack destino = (Stack)this.torre1.clone();
        
        if (ehValido(origem, destino)){
            destino.push(origem.pop());
            Hannoi novo = new Hannoi(destino, (Stack)this.torre2.clone(), origem, "Movendo torre3 para torre1");
            visitados.add(novo);
        }
    }
    
    private void moverTorre3_Torre2(HashSet<Estado> visitados) {
        Stack origem = (Stack)this.torre3.clone();
        Stack destino = (Stack)this.torre2.clone();
        
        if (ehValido(origem, destino)){
            destino.push(origem.pop());
            Hannoi novo = new Hannoi((Stack)this.torre1.clone(), destino, origem, "Movendo torre3 para torre2");
            visitados.add(novo);
        }
    }
    /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Hannoi) {
            Hannoi e = (Hannoi)o;
            return this.torre1.equals(e.torre1) &&
                   this.torre2.equals(e.torre2) &&
                   this.torre3.equals(e.torre3);
        }
        return false;
    }
    
    /** 
     * retorna o hashCode do estado
     * (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() {
        return (""+this.torre1 + this.torre2 + this.torre3).hashCode();
    }
    
    @Override
    public String toString() {
        return "" + this.torre1 + this.torre2 + this.torre3  + " -> " + op + "\n";
    }
    
    
    public static void main(String[] a) {
        int discos = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos discos?"));
        if (discos == 0) System.exit(0);
        
        Stack t1 = new Stack();
        Stack t2 = new Stack();
        Stack t3 = new Stack();
        
        for (;discos > 0; discos--) {
            t1.push(discos);
        }
        
        Hannoi estadoInicial = new Hannoi(t1, t2, t3, "estado inicial");
        
        // chama busca em largura
        System.out.println("busca em ....");
        
        Nodo n = new BuscaProfundidade(new MostraStatusConsole()).busca(estadoInicial);
        
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
    }
}

