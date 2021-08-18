package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.BuscaIterativo;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.Nodo;


/**
 * Representa um estado do mundo: qdt de agua em cada jarro
 */
public class EstadoJarros implements Estado {
	
	// preferir atributos final, para não correr o risco de altera-los
	// (immutable objects)
    final int j4;
    final int j3;
    final String op; // operacao que gerou o estado
    
    public EstadoJarros(int a, int s, String o) {
        j4 = a;
        j3 = s;
        op = o;
    }
    
    public String getDescricao() {
        return
        "Neste problema existem dois jarros, um com capacidade\n"+
        "para 4 litros e outro com capacidade para 3 litros.\n"+
        "Pode-se encher os jarros, esvazia-los ou derramar a agua\n"+
        "de um deles no outro.\n"+
        "O objetivo e deixar o jarro de 3l com 2l de agua.\n";
    }
    
    /**
     * verifica se o estado e meta
     */
    public boolean ehMeta() {
        return j4 == 0 && j3 == 2;
    }
    
    /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    public boolean equals(Object o) {
        if (o instanceof EstadoJarros) {
            EstadoJarros e = (EstadoJarros)o;
            return e.j4 == j4 && e.j3 == j3;
        }
        return false;
    }

    /** 
     * retorna o hashCode desse estado
     * (usado para poda, conjunto de fechados)
     */
    public int hashCode() {
        return (j4 + "," + j3).hashCode();
    }
    
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        // encher o jarro de 4 litros
        if (j4 < 4) {
            suc.add(new EstadoJarros(4, j3, "encher(4)"));
        }
        
        // encher o jarro de 3 litros
        if (j3 < 3) {
            suc.add(new EstadoJarros(j4, 3, "encher(3)"));
        }
        
        // envaziar o jarro de 4 litros
        if (j4 > 0) {
            suc.add(new EstadoJarros(0, j3, "esvaziar(4)"));
        }
        
        // envaziar o jarro de 3 litros
        if (j3 > 0) {
            suc.add(new EstadoJarros(j4, 0, "esvaziar(3)"));
        }
        
        // jogar agua do jarro de 4 litros no de 3l
        int folgaJ3 = 3 - j3;
        if (j4 > 0 &&  folgaJ3 > 0) {
            int qdtDerramada = Math.min(folgaJ3, j4);
            int ficaJ4       = j4 - qdtDerramada;
            int ficaJ3       = j3 + qdtDerramada;
            if (ficaJ4 < 0) {
                ficaJ4 = 0;
            }
            suc.add(new EstadoJarros(ficaJ4, ficaJ3, "jogar(4,3)"));
        }
        
        // jogar agua do jarro de 3l no de 4l
        int folgaJ4 = 4 - j4;
        if (j3 > 0 &&  folgaJ4 > 0) {
            int qdtDerramada = Math.min(folgaJ4,j3);
            int ficaJ4       = j4 + qdtDerramada;
            int ficaJ3       = j3 - qdtDerramada;
            if (ficaJ3 < 0) {
                ficaJ4 = 0;
            }
            suc.add(new EstadoJarros( ficaJ4, ficaJ3, "jogar(3,4)"));
        }
        return suc;
    }
    
    
    public String toString() {
        return "\n(" + j4 + "," + j3 + ") - "+op;
    }
    
    
    /**
     * Custo para geracao do estado
     */
    public int custo() {
        return 1;
    }
    
    public static void main(String[] a) {
        Estado inicial  = new EstadoJarros(0,0, "inicial");
        
        // chama busca em largura
        System.out.println("busca em largura");
        Nodo n = new BuscaLargura().busca(inicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
        
        // chama busca em profundidade
        System.out.println("busca em profundidade");
        n = new BuscaProfundidade(15).busca(inicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
        
       
        // chama busca em profundidade iterativo
        System.out.println("busca em profundidade iterativo");
        n = new BuscaIterativo().busca(inicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
        
    }
}

