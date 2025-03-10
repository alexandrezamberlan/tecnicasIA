
import busca.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class QuebraCabeca implements Estado, Heuristica {

    public int tabuleiro[][]; // tabuleiro/matriz
    public int posL, // Posição da Linha da peça vazia (Zero)
               posC; // Posição da Coluna da peça vazia (Zero)
    public String op; // Nome da operação executada para chegar ao estado atual

    @Override
    public String getDescricao() {
        return "Problema do Quebra-cabeça de N peças";
    }

    @Override
    public int custo() {
        return 1;
    }

    public QuebraCabeca() {
        
        
    }

    //construtor padrao - para a primeira vez
    public QuebraCabeca(int tamanho) {
        this.op = "Estado inicial";
        ArrayList lista = new ArrayList();
        this.tabuleiro = new int[tamanho][tamanho];

        for (int i = 0; i < tamanho * tamanho; i++) {
            lista.add(i);
        }
        Collections.shuffle(lista);
        int i = 0;
        for (int lin = 0; lin < tamanho; lin++) {
            for (int col = 0; col < tamanho; col++) {
                this.tabuleiro[lin][col] = (int) lista.get(i);
                if ((int) lista.get(i) == 0) {
                    this.posL = lin;
                    this.posC = col;
                }
                i++;
            }
        }
    }

    //construtor para os demais estados, depois do estado inicial
    public QuebraCabeca(QuebraCabeca t, String op) {
        this.posL = t.posL;
        this.posC = t.posC;
        this.op = op;
    }

    /**
     * Faz o calculo de Heurística
     *
     * @return Heurística
     */
    @Override
    public int h() {
        return h_avaliacao() + h_heuristica();
    }

    /**
     * Quantidade de números fora do lugar (seqüencia)
     *
     * @return Quantidade
     */
    private int h_avaliacao() {
        int nr = 0,
                qtd = 0;
        if (this.tabuleiro == null) {
            return 0;
        }

        for (int i = 0; i < this.tabuleiro.length; i++) {
            for (int j = 0; j < this.tabuleiro.length; j++) {
                if (this.tabuleiro[i][j] != nr++) {
                    qtd++;
                }
            }
        }

        return qtd;
    }

    /**
     * Calcula a distancia de cada numero até seu lugar
     *
     * @return Distancia
     */
    private int h_heuristica() {
        int nr = 0,
                distancia = 0;

        if (this.tabuleiro == null) {
            return 0;
        }

        for (int i = 0; i < this.tabuleiro.length; i++) {
            for (int j = 0; j < this.tabuleiro.length; j++) {
                if (this.tabuleiro[i][j] <= nr++) {
                    distancia += nr - this.tabuleiro[i][j];
                } else {
                    distancia += this.tabuleiro[i][j] - nr;
                }
            }
        }

        return distancia;
    }

    /**
     * Meta do algoritmo
     *
     * @return Verdadeiro se achou o resultado que é procurado
     */
    @Override
    public boolean ehMeta() {
        int count = 0;
        if (this.tabuleiro == null) {
            return false;
        }
        for (int i = 0; i < this.tabuleiro.length; i++) {
            for (int j = 0; j < this.tabuleiro.length; j++) {
                if (this.tabuleiro[i][j] != count++) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param o Outro estado do QuebraCabeca
     * @return Verdadeiro se os estados são iguais
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof QuebraCabeca) {
            QuebraCabeca e = (QuebraCabeca) o;

            if (this.tabuleiro == null || e.tabuleiro == null) {
                return false;
            }

            for (int i = 0; i < this.tabuleiro.length; i++) {
                for (int j = 0; j < this.tabuleiro.length; j++) {
                    if (this.tabuleiro[i][j] != e.tabuleiro[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        StringBuilder frase = new StringBuilder();

        if (this.tabuleiro == null) {
            return 0;
        }

        for (int i = 0; i < this.tabuleiro.length; i++) {
            for (int j = 0; j < this.tabuleiro.length; j++) {
                frase.append(this.tabuleiro[i][j]);
            }
        }
        return frase.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder resposta = new StringBuilder();

        for (int lin = 0; lin < this.tabuleiro.length; lin++) {
            for (int col = 0; col < this.tabuleiro.length; col++) {
                //System.out.print(this.tabuleiro[lin][col] + "\t");
                resposta.append(this.tabuleiro[lin][col] + "\t");
            }
            //System.out.println("");
            resposta.append("\n");
        }
        //System.out.println("Zero: (" + this.posL + "," + this.posC + ")");
        resposta.append("Zero: (" + this.posL + "," + this.posC + ")\n");
        resposta.append(op + "\n");

        return resposta.toString();
    }

    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<>();
        

        this.moverCima(visitados);
        this.moverBaixo(visitados);
        this.moverEsquerda(visitados);
        this.moverDireita(visitados);

       
        return visitados;
    }

    public int[][] clonar() {
        int r[][] = new int[this.tabuleiro.length][this.tabuleiro.length];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r.length; j++) {
                r[i][j] = this.tabuleiro[i][j];
            }
        }
        return r;
    }

    private void moverCima(List<Estado> visitados) {
        if (this.posL > 0) {
            QuebraCabeca clone = new QuebraCabeca();
            
            clone.tabuleiro = this.clonar();
            clone.posL = this.posL;
            clone.posC = this.posC;
            

            int tmp = clone.tabuleiro[clone.posL - 1][clone.posC];
            clone.tabuleiro[clone.posL - 1][clone.posC] = clone.tabuleiro[clone.posL][clone.posC];
            clone.tabuleiro[clone.posL][clone.posC] = tmp;
            clone.posL--;
            
            if (visitados.add(new QuebraCabeca(clone, "Mover Cima"))) {
                System.out.println("mover pra cima OK");
                System.out.println(clone);
            }
        }
    }

    private void moverBaixo(List<Estado> visitados) {
        if (this.posL < this.tabuleiro.length - 1) {
            QuebraCabeca clone = new QuebraCabeca();
            
            clone.tabuleiro = this.clonar();
            clone.posL = this.posL;
            clone.posC = this.posC;
            

            int tmp = clone.tabuleiro[clone.posL + 1][clone.posC];
            clone.tabuleiro[clone.posL + 1][clone.posC] = clone.tabuleiro[clone.posL][clone.posC];
            clone.tabuleiro[clone.posL][clone.posC] = tmp;
            clone.posL++;
            
            if (visitados.add(new QuebraCabeca(clone, "Mover Baixo"))) {
                System.out.println("mover pra baixo OK");
                System.out.println(clone);
            }
        }
    }

    private void moverEsquerda(List<Estado> visitados) {
        if (this.posC > 0) {
            QuebraCabeca clone = new QuebraCabeca();
            
            clone.tabuleiro = this.clonar();
            clone.posL = this.posL;
            clone.posC = this.posC;
            

            int tmp = clone.tabuleiro[clone.posL][clone.posC - 1];
            clone.tabuleiro[clone.posL][clone.posC - 1] = clone.tabuleiro[clone.posL][clone.posC];
            clone.tabuleiro[clone.posL][clone.posC] = tmp;
            clone.posC--;
            
            if (visitados.add(new QuebraCabeca(clone, "Mover Esquerda"))) {
                System.out.println("mover pra esquerda OK");
                System.out.println(clone);
            }
        }
    }

    private void moverDireita(List<Estado> visitados) {
        if (this.posC < this.tabuleiro.length - 1) {
            QuebraCabeca clone = new QuebraCabeca();
            
            clone.tabuleiro = this.clonar();
            clone.posL = this.posL;
            clone.posC = this.posC;
            

            int tmp = clone.tabuleiro[clone.posL][clone.posC + 1];
            clone.tabuleiro[clone.posL][clone.posC + 1] = clone.tabuleiro[clone.posL][clone.posC];
            clone.tabuleiro[clone.posL][clone.posC] = tmp;
            clone.posC++;
            
            if (visitados.add(new QuebraCabeca(clone, "Mover Direita"))) {
                System.out.println("mover pra direita OK");
                System.out.println(clone);
            }
        }
    }

    public static void main(String[] a) {
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.println("Entre com a dimensao do tabuleiro: ");
            int tamanho = teclado.nextInt();
            QuebraCabeca inicial = new QuebraCabeca(tamanho); // Gera o estado inicial

            System.out.println("Busca A*");
            
            Nodo n = new AEstrela(new MostraStatusConsole()).busca(inicial); // Com Status de andamento
            if (n == null) {
                System.out.println("Sem solucao!");
            } else {
                System.out.println("Solucao:\n" + n.montaCaminho() + "\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0); 
    }
}
