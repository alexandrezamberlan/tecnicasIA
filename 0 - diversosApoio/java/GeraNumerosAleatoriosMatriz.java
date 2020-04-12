import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class GeraNumerosAleatoriosMatriz {
    public static void main(String[] args) {
        int dimensao = Integer.parseInt(JOptionPane.showInputDialog(null,"Informe a dimensão do puzzle"));
        
        int matriz[][] = new int[dimensao][dimensao];
        
        LinkedList vetor = new LinkedList();
                
        for (int i = 0; i < dimensao * dimensao; i++) {
            vetor.add(i);
        }
        
        Collections.shuffle(vetor); //embaralhamento dos numeros
        
        System.out.println(vetor);
        int numeros = 0;
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                matriz[i][j] = (int)vetor.get(numeros);
                numeros++;
            }
        }
        
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("");
        }
        
        System.exit(0);
    }
}