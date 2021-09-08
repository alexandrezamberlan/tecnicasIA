import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.util.List;
import java.util.LinkedList;

public class Mapa {
    
    public static void criarListaHeuristica(File arquivo, List<Cidade> lista) {
        String resposta[];
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            String str;
            int indice = 0;
            while (in.ready()) {
                str = in.readLine();
                resposta = str.split("@");
                lista.add(new Cidade(indice, resposta[0], Integer.parseInt(resposta[1])));
                indice++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Erro na abertura e tratamento do arquivo!");
        }
    }
    
    
    public static void criarMatrizAdjacencia(File arquivo, int matriz[][]) {
         
    }
}