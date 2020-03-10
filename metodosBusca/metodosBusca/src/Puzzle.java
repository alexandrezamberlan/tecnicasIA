import java.util.LinkedList;
import java.util.List;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * Representa um estado do mundo
 */
public class Puzzle implements Estado {
    
    @Override
    public String getDescricao() {
        return "Problema do Puzzle apresentado em sala de aula\n\n";
    }

    final int matriz[][]; // preferir "immutable objects"
    final int linha, coluna; //guarda a posição do zero/vazio na matriz
    final String op; // operacao que gerou o estado

    
    int [][]clonar(int origem[][]) {
        int destino[][] = new int[origem.length][origem.length];
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = 0;
            }
        }
        return destino;
    }
    
    /**
     * construtor para o estado, recebe cada valor de atributo
     */
    public Puzzle(int m[][], int linha, int coluna, String o) {
        this.matriz = m;
        this.linha = linha;
        this.coluna = coluna;
        this.op = o;
    }

    /**
     * verifica se o estado e meta
     */
    @Override
    public boolean ehMeta() {
       int sequencia = 0;
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                if (sequencia != matriz[i][j]) {
                    return false; //nao esta na sequencia, ou seja, nao eh meta
                }
                sequencia++;
            }
        }
        return true;
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
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores

        paraCima(visitados);
        paraBaixo(visitados);
//        paraEsquerda(visitados);
//        paraDireita(visitados);
        
        return visitados;
    }

    private void paraCima(List<Estado> visitados) {
        if (this.linha == 0) return;

        int mTemp[][];
        int linhaTemp = this.linha;
        int colunaTemp = this.coluna;
        mTemp = clonar(this.matriz);
        
        int aux = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = mTemp[linhaTemp - 1][colunaTemp];
        mTemp[linhaTemp - 1][colunaTemp] = aux;
        linhaTemp--;
               
        Puzzle novo = new Puzzle(mTemp, linhaTemp, colunaTemp, "Movendo para cima");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraBaixo(List<Estado> visitados) {
        if (this.linha == this.matriz.length-1) return;

        int mTemp[][];
        int linhaTemp = this.linha;
        int colunaTemp = this.coluna;
        mTemp = clonar(this.matriz);
        
        int aux = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = mTemp[linhaTemp + 1][colunaTemp];
        mTemp[linhaTemp + 1][colunaTemp] = aux;
        linhaTemp++;
               
        Puzzle novo = new Puzzle(mTemp, linhaTemp, colunaTemp, "Movendo para baixo");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    
    /**
     * verifica se um estado e igual a outro (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Puzzle) {
            Puzzle e = (Puzzle) o;
            for (int i = 0; i < e.matriz.length; i++) {
                for (int j = 0; j < e.matriz.length; j++) {
                    if (e.matriz[i][j] != this.matriz[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * retorna o hashCode desse estado (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() {
        String estado = "";
        
        for (int i = 0; i < this.matriz.length; i++) {
                for (int j = 0; j < this.matriz.length; j++) {
                    estado = estado + this.matriz[i][j];
                }
            }
        
        return estado.hashCode();
    }

    @Override
    public String toString() {
        return "" + op + "\n";
    }

    public static void main(String[] a) {
        
        Puzzle estadoInicial;
        System.out.println("busca em ...");
//        Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
//        if (n == null) {
//            System.out.println("sem solucao!");
//        } else {
//            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
//        }
    }
}