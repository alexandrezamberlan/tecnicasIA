import javax.swing.JOptionPane;

public class Principal {
    public static void main(String[] args) {
        String nomeDoArquivoSudoku;
        nomeDoArquivoSudoku = JOptionPane.showInputDialog(null,"Qual o nome (caminho) do arquivo com um cenário sudoku inicial? ");        

        Sudoku solucao1 = new Sudoku();

        if (solucao1.popularDoArquivo(nomeDoArquivoSudoku)) {
            solucao1.exibirSudoku("Arquivo de cenário localizado!");
            if (solucao1.resolveSudoku(1)) {
                solucao1.exibirSudoku("\n\nCenário resolvido com sucesso!");
                System.out.println("Todas as chamadas recursivas: " + solucao1.getTotalChamadasNaoRecursivas());
            } else {
                System.out.println("Cenário não resolvido! A configuração inicial do sudoku está complexa!");
            }
        } else {
            System.out.println("Arquivo não localizado");
        }
    }
}