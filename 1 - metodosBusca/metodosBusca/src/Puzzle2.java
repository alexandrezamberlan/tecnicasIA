import busca.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Puzzle2 implements Estado {
    final int matriz[][];
    final int dimensao;
    int linha;
    int coluna;
    public String op; //verbalizacao dos movimentos
    
    
    public Puzzle2(int dimensao) {
        this.matriz = new int[dimensao][dimensao];
        this.dimensao = dimensao;
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                this.matriz[i][j] = 0;
            }
        }
    }
    /**
     * construtor do estado inicial
     * @param dimensao
     * @param op 
     */
    public Puzzle2(int dimensao, String op) {
        this.matriz = new int[dimensao][dimensao];
        this.dimensao = dimensao;
        this.linha = 0;
        this.coluna = 0;
        this.op = op;
        
        ArrayList<Integer> lista = new ArrayList<Integer>();                
        //gerar numeros aleatorios na matriz
        for (int i = 0; i < this.dimensao*this.dimensao; i++) {
            lista.add(i);
        }
        Collections.shuffle(lista);
        int posicao = 0;
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                this.matriz[i][j] = lista.get(posicao);
                if (this.matriz[i][j] == 0) {
                    this.linha = i;
                    this.coluna = j;
                }
                posicao++;
            }
        }
    }
    
    /**
     * construtor utilizado para gerar novos estados
     * @param m
     * @param linha
     * @param coluna
     * @param op 
     */
    public Puzzle2(int m[][], int linha, int coluna, String op) {
        this.matriz = m;
        this.linha = linha;
        this.coluna = coluna;
        this.op = op;
        this.dimensao = m.length;
    }    
    
    @Override
    public String getDescricao() {
        return "Problema do Puzzle de NxN";
    }

    @Override
    public boolean ehMeta() {
        int posicao = 0;
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                if (this.matriz[i][j] != posicao) {
                    return false;
                }
                posicao++;
            }
        }
        return true;
    }

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>();
        
        cima(visitados);
        baixo(visitados);
        esquerda(visitados);
        direita(visitados);
        
        return visitados;
    }
    
    public int[][]clonar(){
        int matrizTmp[][] = new int[this.dimensao][this.dimensao];
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                matrizTmp[i][j] = this.matriz[i][j];
            }
        }               
        return matrizTmp;
    }
    
    public void cima(List<Estado> visitados) {
        if (this.linha == 0) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
                
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha-1][this.coluna];
        matrizTmp[this.linha-1][this.coluna] = 0;
                
        Puzzle2 novo = new Puzzle2(matrizTmp, this.linha-1, this.coluna, "Indo para cima");
        if (!visitados.contains(novo)) {
            visitados.add(novo);
        } else {
            System.gc();
            System.out.println("visitado....");
        }
    }
    
    public void baixo(List<Estado> visitados) {
        if (this.linha == this.dimensao-1) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
                
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha+1][this.coluna];
        matrizTmp[this.linha+1][this.coluna] = 0;
        
        Puzzle2 novo = new Puzzle2(matrizTmp, this.linha+1, this.coluna, "Indo para baixo");
        if (!visitados.contains(novo)) {
            visitados.add(novo);
        } else {
            System.gc();
            System.out.println("visitado....");
        }
    }
    
    public void esquerda(List<Estado> visitados) {
        if (this.coluna == 0) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
                
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha][this.coluna-1];
        matrizTmp[this.linha][this.coluna-1] = 0;
                
        Puzzle2 novo = new Puzzle2(matrizTmp, this.linha, this.coluna-1, "Indo para esquerda");
        if (!visitados.contains(novo)) {
            visitados.add(novo);
        } else {
            System.gc();
            System.out.println("visitado....");
        }
    }
    
    public void direita(List<Estado> visitados) {
        if (this.coluna == this.dimensao-1) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
                
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha][this.coluna+1];
        matrizTmp[this.linha][this.coluna+1] = 0;
                
        Puzzle2 novo = new Puzzle2(matrizTmp, this.linha, this.coluna+1, "Indo para direita");
        if (!visitados.contains(novo)) {
            visitados.add(novo);
        } else {
            System.gc();
            System.out.println("visitado....");
        }
    }
    
    /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Puzzle2) {
            Puzzle2 e = (Puzzle2)o;
            
            for (int i = 0; i < this.dimensao; i++) {
                for (int j = 0; j < this.dimensao; j++) {
                    if (e.matriz[i][j] != this.matriz[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer resposta = new StringBuffer();       
        resposta.append(op + "\n");
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                resposta.append(this.matriz[i][j]+" ");
            }
            resposta.append("\n");
        }
        resposta.append("Posicao do 0: " + this.linha + "," + this.coluna + "\n\n");
        return resposta.toString();
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

        
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int dimensao;
        
        System.out.print("Entre com a dimensao: ");
        dimensao = teclado.nextInt();
        Puzzle2 estadoInicial = new Puzzle2(dimensao,"Estado Inicial");        
        
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
