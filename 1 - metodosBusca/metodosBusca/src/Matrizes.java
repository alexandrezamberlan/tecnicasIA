import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
public class Matrizes {

    public static int [][]clonar(int origem[][]) {
        int destino[][] = new int[origem.length][origem.length];
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = 0;
            }
        }
        return destino;
    }

    public static void paraCima(int [][]matriz, Posicao pos) {
        if (pos.linha == 0) return;

        int mTemp[][];
        mTemp = matriz;
        int linhaTemp = pos.linha - 1; 
        int colunaTemp = pos.coluna;
        
        mTemp[pos.linha][pos.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;

        pos.linha--;
     
    }

    public static void paraBaixo(int [][]matriz, Posicao pos) {
        if (pos.linha == matriz.length-1) return;

        int mTemp[][];
        mTemp = matriz;
        int linhaTemp = pos.linha + 1;
        int colunaTemp = pos.coluna;
        
        mTemp[pos.linha][pos.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
               
        pos.linha++;
    }

    public static void paraEsquerda(int [][]matriz, Posicao pos) {
        if (pos.coluna == 0) return;

        int mTemp[][];
        mTemp = matriz;
        int linhaTemp = pos.linha;
        int colunaTemp = pos.coluna - 1;
        
        mTemp[pos.linha][pos.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
     
       pos.coluna--;
    }

    public static void paraDireita(int [][]matriz, Posicao pos) {
        if (pos.coluna == matriz.length-1) return;

        int mTemp[][];
        mTemp = matriz;
        int linhaTemp = pos.linha;
        int colunaTemp = pos.coluna + 1;
        
        mTemp[pos.linha][pos.coluna] = mTemp[linhaTemp][colunaTemp];
        mTemp[linhaTemp][colunaTemp] = 0;
            
        pos.coluna++;
 
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

    public static void testaHash(int [][]matriz) {
        String estado = "";
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                estado = estado + matriz[i][j];
            }
        }
        System.out.println("Para visitados: " + estado + " Hash gerado: " + estado.hashCode());
    }
    public static void main(String a[]) {
        Posicao pos = new Posicao();
        int matriz[][] = geraMatrizInicial(4, pos);
        exibirMatriz(matriz);
        System.out.println(pos.linha + "," + pos.coluna);
        testaHash(matriz);
        String op;
        try (Scanner teclado = new Scanner(System.in)) {
            do {
                // op = JOptionPane.showInputDialog(null,"W - cima; S - baixo; A - esquerda; D - direita");
                System.out.println("W - cima; S - baixo; A - esquerda; D - direita");
                op = teclado.nextLine();
                switch (op) {
                    case "w":
                        paraCima(matriz, pos);
                        break;
                    case "s":
                        paraBaixo(matriz, pos);
                        break;
                    case "a":
                        paraEsquerda(matriz, pos);
                        break;
                    case "d":
                        paraDireita(matriz, pos);
                        break;
                    default:
                        break;
                }
                System.out.println("\n");
                exibirMatriz(matriz);
                System.out.println(pos.linha + "," + pos.coluna);
                testaHash(matriz);
            } while ("w".equals(op) || "s".equals(op) || "a".equals(op) || "d".equals(op));
        }
    }
}

