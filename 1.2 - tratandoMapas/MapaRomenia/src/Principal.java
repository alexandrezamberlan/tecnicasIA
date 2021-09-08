
import javax.swing.JFileChooser;
import java.util.List;
import java.util.LinkedList;

public class Principal {

    public static void tabelaHeuristica(List<Cidade> listaHeuristica) {
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
    
    public static void matrizAdjacencia(int matrizAdjacencia[][], List<Cidade> listaHeuristica) {
        Cidade origem = new Cidade(0,"Arad", 366);
        Cidade destino = new Cidade(15,"Sibiu",253);
        matrizAdjacencia[origem.indice][destino.indice] = 140;
       
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            //Arquivo.lerArquivo(janelaCarregarArquivo.getSelectedFile());
            Mapa.criarMatrizAdjacencia(janelaCarregarArquivo.getSelectedFile(), matrizAdjacencia);

            System.out.println("Quantidade de cidades/estados: " + listaHeuristica.size());
            for (int i = 0; i < listaHeuristica.size(); i++) {
                System.out.println(listaHeuristica.get(i));
            }
        }
        
        
        
    }

    public static void main(String a[]) {

        List<Cidade> listaHeuristica = new LinkedList<>();
        Principal.tabelaHeuristica(listaHeuristica);
        
        //definir o tamanho da matriz/grafo
        int matrizAdjacencia[][] = new int[listaHeuristica.size()][listaHeuristica.size()];
        Principal.matrizAdjacencia(matrizAdjacencia, listaHeuristica);

        
        //exibir o mapa em forma de matriz/grafo
        //exibir a lista heurística
        System.exit(1);
    }
}
