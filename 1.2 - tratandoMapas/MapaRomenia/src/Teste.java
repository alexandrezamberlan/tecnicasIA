
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexandrezamberlan
 */
public class Teste {
    public static void main(String[] args) {
        String respostas[];
        respostas = "arad@sibiu@140".split("@");
        
        List<String> lista = new LinkedList();
        lista.add("arad");
        lista.add("sibiu");
        
        System.out.println(lista);
        
        int origem = lista.indexOf(respostas[0]);
        int destino = lista.indexOf(respostas[1]);
        
        System.out.println(origem + " - " + destino);
    }
    
}
