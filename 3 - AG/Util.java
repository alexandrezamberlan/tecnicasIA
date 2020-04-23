
import java.util.Random;

public class Util {

    
    public static StringBuffer gerarPalavra(int n) {
        String letras = "abcdefghijklmnopqrstuvxwyz";
        int tamanho = letras.length();

        StringBuffer palavra = new StringBuffer();

        Random gerador = new Random();

        for (int i = 0; i < n; i++) {
            palavra.append(letras.charAt(gerador.nextInt(tamanho)));
        }
        return palavra;
    }
}