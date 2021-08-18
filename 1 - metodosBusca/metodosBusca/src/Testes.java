
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

class Aluno {
    int matricula;
    String nome;
    
    public Aluno(int m, String n) {
        matricula = m;
        nome = n;
    }

    @Override
    public String toString() {
        return this.matricula + " - " + this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Aluno) {
            Aluno e = (Aluno) o;
            return e.matricula == this.matricula;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return ("" + this.matricula).hashCode();
    }

    
    
    
}

public class Testes {

    static void exibir(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("");

        }
    }

    static int[][] clonar(int origem[][]) {
        int destino[][] = new int[origem.length][origem.length];
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = 0;
            }
        }
        return destino;
    }
    
    public static void main(String[] args) {
//        int dimensao = 3;
//        int matriz[][] = new int[dimensao][dimensao];
//        System.out.println("Tamanho matriz: " + matriz.length);
//        Random gerador = new Random();
//
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz.length; j++) {
//                matriz[i][j] = gerador.nextInt(100);
//            }
//        }
//
//        exibir(matriz);
//
//        int m[][] = new int[matriz.length][matriz.length];
//
//        m = clonar(matriz);
//        
//        System.out.println("Clonagem....");
//        exibir(m);
//        
//        System.out.println("Clonagem....");
//        exibir(matriz);

          HashSet lista1 = new HashSet();
          LinkedList lista2 = new LinkedList();
          
          lista1.add(new Aluno(100,"Erico"));
          lista1.add(new Aluno(100,"Erico"));
          
          
          
//          lista2.add(new Aluno(100,"Erico"));
//          
//          
//          
          System.out.println("Lista1: " + lista1);
//          System.out.println("Lista2: " + lista2);
//          
//          if (lista1.equals(lista2)) System.out.println("Sao iguais");
          
          
    }
}
