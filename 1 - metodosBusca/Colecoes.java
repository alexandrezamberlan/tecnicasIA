import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import java.util.LinkedList;
import java.util.Iterator;

public class Colecoes {
    public static void main(String a[]) {
        // List<String> listaString1 = new LinkedList<>();
        HashSet<String> listaString1 = new HashSet<>();

        String nome;
        
        do {
            nome = JOptionPane.showInputDialog(null, "Nome ou a palavra 'fim': ");
            if (nome.equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println(nome.hashCode());
            listaString1.add(nome);
        } while (true);

        Iterator<String> it = listaString1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}