package exemplos;

import java.util.LinkedList;
import java.util.List;

import busca.BuscaLargura;
import busca.Estado;
import busca.Nodo;

/**
 * Representa um estado do mundo: em que lado estao
 * o homem, o lobo, o alface e o carneiro
 */
public class HLAC implements Estado {
    
    public String getDescricao() {
        return
        "Uma pessoa, um lobo, um carneiro e um cesto de alface estao a beira \n"+
        "de um rio. Dispondo de um barco no qual pode carregar apenas um dos \n"+
        "outros tres, a pessoa deve transportar tudo para a outra margem. \n"+
        "Determine uma serie de travessias que respeite a seguinte condicao: \n"+
        "em nenhum momento devem ser deixados juntos e sozinhos o lobo e o \n"+
        "carneiro ou o carneiro e o cesto de alface. \n\n";
    }
    
    final char homem, lobo, carneiro, alface; // preferir "immutable objects"
    final String op; // operacao que gerou o estado
    
    /** construtor para o estado, recebe cada valor de atributo */
    public HLAC(char h, char l, char c, char a, String o) {
        homem = h;
        lobo = l;
        carneiro = c;
        alface = a;
        op = o;
    }
    
    /**
     * verifica se o estado e meta
     */
    public boolean ehMeta() {
        return homem=='d' && lobo=='d' && carneiro=='d' && alface=='d';
    }
    
    /**
     * Custo para geracao do estado
     */
    public int custo() {
        return 1;
    }
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    public List<Estado> sucessores() {
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        levarCarneiro(suc); // tenta levar o carneiro
        levarLobo(suc);
        levarAlface(suc);
        levarNada(suc);
        
        return suc;
    }
    
    private void levarCarneiro(List<Estado> suc) {
        if (carneiro == homem) { // homem e carneiro na mesma margem
        	char novaMargem = ladoOposto(homem);
            HLAC novo = new HLAC(novaMargem, lobo, novaMargem,  alface, "levarC-"+carneiro+novaMargem);
            if (novo.ehValido()) {
                suc.add(novo);
            }
        }
    }
    
    private void levarLobo(List<Estado> suc) {
        if (lobo == homem) {
        	char novaMargem = ladoOposto(homem);
            HLAC novo = new HLAC(novaMargem, novaMargem, carneiro,  alface, "levarL-"+lobo+novaMargem);
            if (novo.ehValido()) {
                suc.add(novo);
            }
        }
    }
    
    private void levarAlface(List<Estado> suc) {
        if (alface == homem) {
        	char novaMargem = ladoOposto(homem);
            HLAC novo = new HLAC(novaMargem, lobo, carneiro, novaMargem, "levarA-"+alface+novaMargem);
            if (novo.ehValido()) {
                suc.add(novo);
            }
        }
    }
    
    private void levarNada(List<Estado> suc) {
    	char novaMargem = ladoOposto(homem);
        HLAC novo = new HLAC(novaMargem, lobo, carneiro, alface, "levarN-"+homem+novaMargem);
        if (novo.ehValido()) {
            suc.add(novo);
        }
    }

    private char ladoOposto(char l) {
    	if (l == 'e') {
    		return 'd';
    	} else {
    		return 'e';
    	}
    }
    
    
    /** returna true se o estado e valido */
    private boolean ehValido() {
        if (lobo == carneiro && lobo != homem) {
            // lobo e carneiro estao na mesma margem sem o homem
            return false;
        }
        if (carneiro == alface && carneiro != homem) {
            // lobo e carneiro estao na mesma margem sem o homem
            return false;
        }
        return true;
    }
    
    /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    public boolean equals(Object o) {
        if (o instanceof HLAC) {
            HLAC e = (HLAC)o;
            return homem==e.homem && lobo==e.lobo && carneiro==e.carneiro && alface==e.alface;
        }
        return false;
    }
    
    /** 
     * retorna o hashCode desse estado
     * (usado para poda, conjunto de fechados)
     */
    public int hashCode() {
        return (""+homem + lobo + carneiro + alface).hashCode();
    }
    
    public String toString() {
        String dir = ""; // quem esta na dir
        String esq = ""; // quem esta na esq
        if (homem == 'd') {
            dir += 'h';
        } else {
            esq += 'h';
        }
        if (lobo == 'd') {
            dir += 'l';
        } else {
            esq += 'l';
        }
        if (carneiro == 'd') {
            dir += 'c';
        } else {
            esq += 'c';
        }
        if (alface == 'd') {
            dir += 'a';
        } else {
            esq += 'a';
        }
        return esq +"|" + dir + " (" + op + ")\n";
    }
    
    
    public static void main(String[] a) {
        HLAC inicial = new HLAC('e', 'e', 'e', 'e', "inicial");
        
        // chama busca em largura
        System.out.println("busca em largura");
        Nodo n = new BuscaLargura().busca(inicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
    }
}

