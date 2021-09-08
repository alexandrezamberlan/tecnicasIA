
import javax.swing.JFileChooser;
import java.util.List;
import java.util.LinkedList;

public class Principal {

    public static void tabelaHeuristica(List<Cidade> listaHeuristica) {
        //ler o arquivo da heuristica
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);

        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) //montar lista ou hash/dicionário com as heurística
        {
            Arquivo.lerArquivo(janelaCarregarArquivo.getSelectedFile());
        }
        listaHeuristica = Mapa.criarListaHeuristica(janelaCarregarArquivo.getSelectedFile());

        System.out.println("Quantidade de cidades/estados: " + listaHeuristica.size());
        for (int i = 0; i < listaHeuristica.size(); i++) {
            System.out.println(listaHeuristica.get(i));
        }

    }

    public static void main(String a[]) {

        List<Cidade> listaHeuristica = new LinkedList<>();
        Principal.tabelaHeuristica(listaHeuristica);

        //definir o tamanho da matriz/grafo
        //ler o arquivo do mapa
        //contruir as estradas/conexões, sendo que a cidade origem é uma linha, enquanto que a cidade destino é uma coluna
        //exibir o mapa em forma de matriz/grafo
        //exibir a lista heurística
        System.exit(1);
    }
}
