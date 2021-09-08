
import javax.swing.JFileChooser;
import java.util.List;
import java.util.LinkedList;

public class Principal {

    public static void montarTabelaHeuristica(List<Cidade> listaHeuristica) {
        //ler o arquivo da heuristica
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);

        //montar lista ou hash/dicionário com as heurística
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            //Arquivo.lerArquivo(janelaCarregarArquivo.getSelectedFile());
            Mapa.criarListaHeuristica(janelaCarregarArquivo.getSelectedFile(), listaHeuristica);

            System.out.println("Quantidade de cidades/estados: " + listaHeuristica.size());
            for (int i = 0; i < listaHeuristica.size(); i++) {
                System.out.println(listaHeuristica.get(i));
            }
        }
    }
    
    public static void montarMatrizAdjacencia(int matrizAdjacencia[][], List<Cidade> listaHeuristica) {     
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            //Arquivo.lerArquivo(janelaCarregarArquivo.getSelectedFile());
            Mapa.preencherMatrizAdjacencia(janelaCarregarArquivo.getSelectedFile(), matrizAdjacencia, listaHeuristica);

            System.out.println("Matriz de adjacencia");
            Mapa.mostrarMatrizAdjacencia(matrizAdjacencia, listaHeuristica);
        }
    }

    public static void main(String a[]) {

        List<Cidade> listaHeuristica = new LinkedList<>();
        Principal.montarTabelaHeuristica(listaHeuristica);
        
        //definir o tamanho da matriz/grafo
        int matrizAdjacencia[][] = new int[listaHeuristica.size()][listaHeuristica.size()];
        Principal.montarMatrizAdjacencia(matrizAdjacencia, listaHeuristica);

        System.exit(1);
    }
}
