
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class GeraNumerosAleatoriosMatriz2 {

    public static void main(String[] args) {
        int dimensao = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a dimensão do puzzle"));
        int repeticoes = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantas vezes quer repetir?"));

        int matriz[][] = new int[dimensao][dimensao];
        LinkedList vetor = new LinkedList();

        LinkedList visitados = new LinkedList();
        do {
            for (int i = 0; i < dimensao * dimensao; i++) {
                vetor.add(i);
            }
            Collections.shuffle(vetor); //embaralhamento dos numeros

            int numeros = 0;
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    matriz[i][j] = (int) vetor.get(numeros);
                    numeros++;
                }
            }

            StringBuffer estado = new StringBuffer();
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    System.out.print(matriz[i][j] + "\t");
                    estado.append(matriz[i][j]);
                }
                System.out.println("");
            }

            if (visitados.contains(estado.toString())) {
                System.out.println("Estado já visitado: " + estado);
            } else {
                System.out.println("Estado produzido e não visitado: " + estado);
                visitados.add(estado.toString());
            }
            repeticoes--;
        } while (repeticoes > 0);
        System.exit(0);
    }
}
