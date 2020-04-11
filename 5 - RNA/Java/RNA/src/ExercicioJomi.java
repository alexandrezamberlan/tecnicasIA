/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usrlab15
 */
public class ExercicioJomi {

    public static void main(String[] args) {
        
        int[] homero = {1, -1, 1, -1, 1};
        int[] literatura = {1, -1, 1, -1};
        int[] bethoven = {1, 1, 1, -1, -1};
        int[] musica = {1, 1, -1, -1};
        int[] rubens = {1, 1, -1, -1, 1};
        int[] pintura = {1, -1, -1, 1};
        final int TAMARTISTA = homero.length;
        final int TAMAREA =  literatura.length;

        int[][] pesos = new int[TAMARTISTA][TAMAREA];

        //criando a matriz de pesos a partir das entradas (artista * area)
        for (int i = 0; i < TAMARTISTA; i++) {
            for (int j = 0; j < TAMAREA; j++) {
                pesos[i][j] = homero[i] * literatura[j]
                        + bethoven[i] * musica[j]
                        + rubens[i] * pintura[j];
            }
        }
        
        //exibindo a matriz de pesos
        for (int j = 0; j < TAMAREA; j++) {
            for (int i = 0; i < TAMARTISTA; i++) {      
                System.out.print(pesos[i][j] + "\t");
            }
            System.out.println();
        }
        int[] saidaArtista = new int[5];
        int[] saidaArea = new int[4];
        
        
        //entrar com 
        //int[] homero = {1, -1, 1, -1, 1};        
        //int[] bethoven = {1, 1, 1, -1, -1};
        //int[] rubens = {1, 1, -1, -1, 1};
        
        
        for (int j = 0; j < TAMAREA; j++) {
            saidaArea[j] = 0;
            for (int i = 0; i < TAMARTISTA; i++) {
                saidaArea[j] = pesos[i][j] * bethoven[i] + saidaArea[j];
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
            if (saidaArea[i] < 0) saidaArea[i] = -1;
            else saidaArea[i] = 1;
            saida.append(saidaArea[i]);
        }
        
        System.out.println("\n\nResultado da aplicação da função sign\n");
        for (int i = 0; i < saidaArea.length; i++) {
            System.out.print(saidaArea[i] + "\t");
        }
        
        System.out.println("\n"+saida);
  
    }
}
