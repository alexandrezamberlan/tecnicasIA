import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Grafo {

    public static void criarListaHeuristica(FileReader arquivo, List<Cidade> lista) {
        String resposta[];
        try {
            BufferedReader in = new BufferedReader(arquivo);
            String str;
            while (in.ready()) {
                str = in.readLine();
                resposta = str.split("@");
                lista.add(new Cidade(resposta[0], Integer.parseInt(resposta[1])));
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Erro na abertura e tratamento do arquivo!");
        }
    }
    
    public static int pegaIndice(String nomeCidade, List<Cidade> listaHeuristica) {
        for (int i = 0; i < listaHeuristica.size(); i++) {
            if (listaHeuristica.get(i).nome.equalsIgnoreCase(nomeCidade)) {
                return i;
            }
        }
        return -1;
    }

    public static void preencherMatrizAdjacencia(FileReader arquivo, int matriz[][], List<Cidade> listaHeuristica) {
        //inicializar a matriz de adjacencia
        for (int i = 0; i < listaHeuristica.size(); i++) {
            for (int j = 0; j < listaHeuristica.size(); j++) {
                matriz[i][j] = 0;
            }
        }
        String resposta[];
        try {
            BufferedReader in = new BufferedReader(arquivo);
            String str;
            int origem;
            int destino;
            while (in.ready()) {
                str = in.readLine();
                resposta = str.split("@"); //resposta[arad,sibiu,140]
                origem = pegaIndice(resposta[0], listaHeuristica);
                destino = pegaIndice(resposta[1], listaHeuristica);
                //System.out.println(origem + " - " + destino + "  -  " + Integer.parseInt(resposta[2]));
                try {
                    matriz[origem][destino] = Integer.parseInt(resposta[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Problemas de índices na matriz de adjacência!");
                } 
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Erro na abertura e tratamento do arquivo!");
        }
    }

    public static void mostrarMatrizAdjacencia(int matriz[][], List<Cidade> listaHeuristica) {
        for (int i = 0; i < listaHeuristica.size(); i++) {
            System.out.print("\t" + listaHeuristica.get(i).nome.charAt(0));
        }
        System.out.println();
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (j == 0) {
                    System.out.print(listaHeuristica.get(i).nome.charAt(0) + "\t");
                }
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void mostrarListaHeuristica(List<Cidade> listaHeuristica) {
        for (Iterator<Cidade> iterator = listaHeuristica.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }
}
