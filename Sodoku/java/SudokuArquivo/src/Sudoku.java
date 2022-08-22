
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


class Sudoku {
    int matriz[][];
    int dimensao;
    int totalChamadasNaoRecursivas;

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public int getTotalChamadasNaoRecursivas() {
        return totalChamadasNaoRecursivas;
    }

    public void setTotalChamadasNaoRecursivas(int totalChamadasNaoRecursivas) {
        this.totalChamadasNaoRecursivas = totalChamadasNaoRecursivas;
    }

    public Sudoku() {
        this.totalChamadasNaoRecursivas = 0;
    }

    public boolean popularDoArquivo(String nomeDoArquivoSudoku) {               
        try {
            File arquivo = new File(nomeDoArquivoSudoku);
            int qtdLinhas = 0;
            ArrayList<String> linhas = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;                
                while ((linha = br.readLine()) != null) {
                    qtdLinhas++;
                    linhas.add(linha);
                }
            }
            this.dimensao = qtdLinhas;
            this.matriz = new int[this.dimensao][this.dimensao];
            this.inicializarMatriz();
            
            for (int i = 0; i < linhas.size(); i++) {
                for (int j = 0; j < linhas.size(); j++) {                    
                   this.matriz[i][j] = Integer.parseInt(linhas.get(i).substring(j, j+1));                                       
                }
            }        
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void inicializarMatriz() {
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                this.matriz[i][j] = 0;
            }
        }
    }

    public void exibirSudoku(String frase) {
        System.out.println(frase);
        for (int i = 0; i < this.dimensao; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-------------------");
            }
            for (int j = 0; j < this.dimensao; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private boolean numeroEstaNaLinha(int numero, int linha) {
        for (int i = 0; i < this.dimensao; i++) {
            if (this.matriz[linha][i] == numero){            
                return true;
            }
        }
        return false;
    }

    private boolean numeroEstaNaColuna(int numero, int coluna) {
        for (int i = 0; i < this.dimensao; i++) {
            if (this.matriz[i][coluna] == numero) {
                return true;
            }
        }
        return false;
    }

    private boolean numeroEstaNoBox(int numero, int linha, int coluna) {
        int linhaBoxLocal = linha - linha % 3;
        int colunaBoxLocal = coluna - coluna % 3;

        for (int i = linhaBoxLocal; i < linhaBoxLocal + 3; i++) {
            for (int j = colunaBoxLocal; j < colunaBoxLocal + 3; j++) {
                if (this.matriz[i][j] == numero){
                        return true;
                }
            }
        }
        return false;
    }

    private boolean numeroEstaNoLugarCerto(int numero, int linha, int coluna) {
        return !numeroEstaNaLinha(numero, linha)
                && !numeroEstaNaColuna(numero, coluna)
                && !numeroEstaNoBox(numero, linha, coluna);
    }

    public boolean resolveSudoku(int qtdChamadas) {
        for (int linha = 0; linha < this.dimensao; linha++) {
            for (int coluna = 0; coluna < this.dimensao; coluna++) {
                if (this.matriz[linha][coluna] == 0) {
                    for (int tentandoNumero = 0; tentandoNumero <= this.dimensao; tentandoNumero++) {
                        if (numeroEstaNoLugarCerto(tentandoNumero, linha, coluna)) {
                            this.matriz[linha][coluna] = tentandoNumero;

                            this.exibirSudoku("Chamada " + qtdChamadas);                            
                            this.totalChamadasNaoRecursivas++;
                            //entra agora a estratégia de força bruta - profundidade com uso de pilha recursiva
                            if (resolveSudoku(qtdChamadas + 1)) { //chamada recursiva, que representa a pilha do profundidade
                                return true;
                            } else {
                                this.matriz[linha][coluna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
