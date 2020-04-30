
import java.util.Random;

public class Util {

    public static String letras = "abcdefghijklmnopqrstuvxwyz";
    public static int tamanho = letras.length();

    public static StringBuffer gerarPalavra(int n) {
        

        StringBuffer palavra = new StringBuffer();

        Random gerador = new Random();

        for (int i = 0; i < n; i++) {
            palavra.append(letras.charAt(gerador.nextInt(tamanho)));
        }
        return palavra;
    }
}