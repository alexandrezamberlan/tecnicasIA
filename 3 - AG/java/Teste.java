
public class Teste {
    public static void main(String a[]) {
        String nome = "Aloisio";

        Cromossomo palavra = new Cromossomo(Util.gerarPalavra(nome.length()),nome);


        for (int i = 0; i < 10; i++) {
            System.out.println(Util.gerarPalavra(nome.length())); 
        }
        
    }
}