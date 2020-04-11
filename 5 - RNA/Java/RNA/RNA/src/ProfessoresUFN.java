//Programador: Yves Morais Jaques
//Desmonstração do algoritmo de RNA com exemplos de professores e disciplinas utilizando hashcode para encontrar o resultado

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import jdk.nashorn.internal.objects.NativeArray;

/*
alexandre = {1, 1, -1, 1}
ia = {-1, 1, 1}

reiner = {-1, 1, -1, 1}
lp = {1,-1,1}

prass = {-1, -1, 1, 1}
projeto = {-1, -1 -1}
 */
public class ProfessoresUFN {

    public static void main(String[] args) {
        int[] alexandre = {1, -1, 1, -1, 1};
        int[] ia = {1, -1, 1, -1};
        Professor palexandre = new Professor("alexandre", "ia", alexandre, ia);
        int[] reiner = {1, 1, 1, -1, -1};
        int[] linguagem2 = {1, 1, -1, -1};
        Professor preiner = new Professor("reiner", "linaguagem2", reiner, linguagem2);
        int[] fernando = {1, 1, -1, -1, 1};
        int[] csharp = {1, -1, -1, 1};
        Professor pfernando = new Professor("fernando", "csharp", fernando, csharp);

        List<Professor> listProfessores = new LinkedList<Professor>();
        listProfessores.add(palexandre);
        listProfessores.add(preiner);
        listProfessores.add(pfernando);

        final int TAMPROFESSOR = alexandre.length;
        final int TAMAREA = ia.length;

        int[][] pesos = new int[TAMPROFESSOR][TAMAREA];

        //criando a matriz de pesos a partir das entradas (artista * area)
        for (int i = 0; i < TAMPROFESSOR; i++) {
            for (int j = 0; j < TAMAREA; j++) {
                pesos[i][j] = alexandre[i] * ia[j]
                        + reiner[i] * linguagem2[j]
                        + fernando[i] * csharp[j];
            }
        }

        //exibindo a matriz de pesos
        for (int j = 0; j < TAMAREA; j++) {
            for (int i = 0; i < TAMPROFESSOR; i++) {
                System.out.print(pesos[i][j] + "\t");
            }
            System.out.println();
        }
        //int[] saidaProfessor = new int[5];
        int[] saidaArea = new int[4];

        //entrar com 
        // int[] alexandre = {1, -1, 1, -1, 1};       
        //int[] reiner = {1, 1, 1, -1, -1};
        //int[] fernando = {1, 1, -1, -1, 1};
        for (int j = 0; j < TAMAREA; j++) {
            saidaArea[j] = 0;
            for (int i = 0; i < TAMPROFESSOR; i++) {
                saidaArea[j] = pesos[i][j] * reiner[i] + saidaArea[j];
            }
        }

        System.out.println("\n\nResultado da multiplicação\n");
        for (int i = 0; i < saidaArea.length; i++) {
            System.out.print(saidaArea[i] + "\t");
        }

        //aplicar a função sign, ou seja, onde é positivo vira 1, onde é negativo
        //vira -1
        StringBuffer saida = new StringBuffer();
        for (int i = 0; i < saidaArea.length; i++) {
            if (saidaArea[i] < 0) {
                saidaArea[i] = -1;
            } else {
                saidaArea[i] = 1;
            }
            saida.append(saidaArea[i]);
        }

        System.out.println("\n\nResultado da aplicação da função sign:");

        for (int i = 0; i < listProfessores.size(); i++) {
            if (Arrays.hashCode(saidaArea) == Arrays.hashCode(listProfessores.get(i).vDisciplina)) {
                //if (Arrays.equals(saidaArea, listProfessores.get(i).vDisciplina)) {
                //System.out.println(saidaArea);
                System.out.println(listProfessores.get(i).nome);
            }
        }
    }
}
