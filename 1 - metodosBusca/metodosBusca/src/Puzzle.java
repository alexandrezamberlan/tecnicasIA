import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import busca.Heuristica;
import busca.*;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Puzzle implements Estado, Heuristica {
    
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
                destino[i][j] = origem[i][j];
            }
        }
        return destino;
    }
    
    /**
     * construtor para o estado, recebe cada valor de atributo
     */
    public Puzzle(int m[][], int linha, int coluna, String o) {
        this.matriz = m; //ter certeza que m foi clonado antes de entrar no construtor
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
     * Calcula a distancia de cada numero até seu lugar
     *
     * @return Distancia
     */
    @Override
    public int custo() {
//        int nr = 0, distancia = 0;
//        for (int i = 0; i < this.matriz.length; i++) {
//             for (int j = 0; j < this.matriz.length; j++) {
//                 if (this.matriz[i][j] <= nr++) {
//                     distancia += nr - this.matriz[i][j];
//                 } else {
//                     distancia += this.matriz[i][j] - nr;
//                 }
//             }
//         }
//         return distancia;
        return 1;
    }

    /**
     * Quantidade de números fora do lugar (seqüencia)
     *
     * @return Quantidade
     */
    @Override 
    public int h() {
        int nr = 0,qtd = 0;

        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                if (this.matriz[i][j] != nr++) {
                    qtd++;
                }
            }
        }
        return qtd;
    }

    /**
     * gera uma lista de sucessores do nodo.
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores

        paraCima(visitados);
        paraBaixo(visitados);
        paraEsquerda(visitados);
        paraDireita(visitados);
        
        return visitados;
    }

    private void paraCima(List<Estado> visitados) {
        if (this.linha == 0) return;

        int mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linha - 1;
        int colunaTemp = this.coluna;
        
        mTemp[this.linha][this.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
     
        Puzzle novo = new Puzzle(mTemp, linhaTemp, colunaTemp, "Movendo para cima");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraBaixo(List<Estado> visitados) {
        if (this.linha == this.matriz.length-1) return;

        int mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linha + 1;
        int colunaTemp = this.coluna;
        
        mTemp[this.linha][this.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
               
        Puzzle novo = new Puzzle(mTemp, linhaTemp, colunaTemp, "Movendo para baixo");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraEsquerda(List<Estado> visitados) {
        if (this.coluna == 0) return;

        int mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linha;
        int colunaTemp = this.coluna - 1;
        
        mTemp[this.linha][this.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
     
        Puzzle novo = new Puzzle(mTemp, linhaTemp, colunaTemp, "Movendo para esquerda");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraDireita(List<Estado> visitados) {
        if (this.coluna == this.matriz.length-1) return;

        int mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linha;
        int colunaTemp = this.coluna + 1;
        
        mTemp[this.linha][this.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
               
        Puzzle novo = new Puzzle(mTemp, linhaTemp, colunaTemp, "Movendo para direita");
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
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                resultado.append(this.matriz[i][j] + "\t");
            }
            resultado.append("\n");
        }
        return "\n"+ op + "\n" + resultado + "\n\n";
    }

    private static int[][]geraMatrizInicial(int dimensao, Posicao p) {
        LinkedList<Integer> sequencia = new LinkedList<Integer>();

        for (int i = 0; i < dimensao*dimensao; i++){
            sequencia.add(i);
        }
        Collections.shuffle(sequencia);
        int matriz[][] = new int[dimensao][dimensao];
        
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = sequencia.get(contador);
                if (matriz[i][j] == 0) {
                    p.linha = i;
                    p.coluna = j;
                }
                contador++;
            }
        }
//        int matriz[][] = new int[][]{{0,2,5},{3,6,1},{7,4,8}};
//        p.linha = 0;
//        p.coluna = 0;
        
        return matriz;
    }

    private static void exibirMatriz(int [][]matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] a) {
        try (Scanner teclado = new Scanner(System.in)) {
            Posicao pos = new Posicao();
            System.out.println("Entre com a dimensão do Puzzle!");
            int dimensao = teclado.nextInt();
            int matriz[][] = geraMatrizInicial(dimensao, pos);
            exibirMatriz(matriz);
            System.out.println(pos.linha + "," + pos.coluna);
            Puzzle estadoInicial = new Puzzle(matriz,pos.linha, pos.coluna, "estado inicial");
            System.out.println("busca em ...");
            //Nodo n = new SubidaMontanha(new MostraStatusConsole()).busca(estadoInicial); // Com Status de andamento
            Nodo n = new AEstrela(new MostraStatusConsole()).busca(estadoInicial); // Com Status de andamento
            
            //Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
            //Nodo n = new BuscaProfundidade(new MostraStatusConsole()).busca(estadoInicial);
            if (n == null) {
                System.out.println("sem solucao!");
            } else {
                System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Problemas com a dimensão do Puzzle");
        }
        System.exit(0);
    }
}
