import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Arquivo {

    public static void lerArquivo(String nomeArquivo) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(nomeArquivo));
            String str;
            while (in.ready()) {
                str = in.readLine();
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
        }
    }
    
    public static void lerArquivo(File arquivo) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            String str;
            while (in.ready()) {
                str = in.readLine();
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
        }
    }

    public static void lerArquivo_v2(String nomeArquivo) {
        try {
            FileReader r = new FileReader(nomeArquivo);
            BufferedReader br = new BufferedReader(r);
            StringBuffer sb = new StringBuffer();
            String linha = "";
            while ((linha = br.readLine()) != null) {
                sb.append(linha);
                sb.append("\n");
            }
            System.out.println(sb.toString());
            r.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void gravarEmArquivo(String frase) {
        File arquivo;
        StringBuilder sb = new StringBuilder();
        
        sb.append(frase);

        try {
            arquivo = new File("cadeiaAminoacidos.txt");
            
            FileOutputStream saida = new FileOutputStream(arquivo, true);
            
            saida.write(sb.toString().getBytes());
            
            saida.close();
        } catch (IOException e) {
        }
    }
    
   public static void carregarArquivo() {
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
    }
}
