
public class Elemento {
//    int[] a = {1,-1};
//    int[] b;
//    String sA = "";
//    String sB;

    public static void main(String[] args) {
        String frase = "1,-1,1,-1";
        int[] fraseInt = new int[frase.length()];
        String[] fraseSplitada = frase.split(",");
        for (int i = 0; i < fraseSplitada.length; i++) {
            System.out.println(fraseSplitada[i]);
            fraseInt[i] = Integer.parseInt(fraseSplitada[i]);
        }
    }

}
