import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author alexandrezamberlan
 */
public class Util {
    
    public static void montarTabelaHeuristica(List<Cidade> listaHeuristica) {
        JFileChooser janelaCarregarArquivo = new JFileChooser("C:\\Users\\laboratorio\\Downloads\\tecnicasIA\\1.2 - tratandoMapas\\MapaRomenia\\src");
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
        janelaCarregarArquivo.setDialogTitle("Escolha o arquivo de heurística");
        janelaCarregarArquivo.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo heuristica", "map");
        janelaCarregarArquivo.addChoosableFileFilter(filtro);

        //montar lista ou hash/dicionário com as heurística
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            try {
                //Arquivo.lerArquivo(janelaCarregarArquivo.getSelectedFile());
                Grafo.criarListaHeuristica(new FileReader(janelaCarregarArquivo.getSelectedFile()), listaHeuristica);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Quantidade de cidades/estados: " + listaHeuristica.size());
            for (int i = 0; i < listaHeuristica.size(); i++) {
                System.out.println(listaHeuristica.get(i));
            }
        }
    }
    
    public static void montarMatrizAdjacencia(int matrizAdjacencia[][], List<Cidade> listaHeuristica) {     
        JFileChooser janelaCarregarArquivo = new JFileChooser("C:\\Users\\laboratorio\\Downloads\\tecnicasIA\\1.2 - tratandoMapas\\MapaRomenia\\src");
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
        janelaCarregarArquivo.setDialogTitle("Escolha o arquivo do grafo ou mapa");
        janelaCarregarArquivo.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo Mapa", "map");
        janelaCarregarArquivo.addChoosableFileFilter(filtro);
        
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            try {
                Grafo.preencherMatrizAdjacencia(new FileReader(janelaCarregarArquivo.getSelectedFile()), matrizAdjacencia, listaHeuristica);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Matriz de adjacencia");
            Grafo.mostrarMatrizAdjacencia(matrizAdjacencia, listaHeuristica);
        }
    }    
}
