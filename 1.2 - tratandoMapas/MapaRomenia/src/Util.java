import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author alexandrezamberlan
 */
public class Util {
    public static void lerArquivoHeuristica(List<Cidade> listaHeuristica) {
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);

        //montar lista ou hash/dicionário com as heurística
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            try {
                //Arquivo.lerArquivo(janelaCarregarArquivo.getSelectedFile());
                Grafo.criarListaHeuristica(new FileReader(janelaCarregarArquivo.getSelectedFile()), listaHeuristica);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void lerArquivoMapa(int matrizAdjacencia[][], List<Cidade> listaHeuristica) {     
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            try {
                Grafo.preencherMatrizAdjacencia(new FileReader(janelaCarregarArquivo.getSelectedFile()), matrizAdjacencia, listaHeuristica);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
