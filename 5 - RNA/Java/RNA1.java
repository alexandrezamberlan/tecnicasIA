import java.util.LinkedList;




public class RNA1 {

    static double somatorio(LinkedList entradas, LinkedList pesos) {
        double soma = 0;
        for (int i = 0; i < entradas.size(); i++) {
            soma = soma + ((int)entradas.get(i) * (double)pesos.get(i));
        }
        return soma;
    }

    public static int ativacaoStep(double soma) {
        if (soma >= 1) return 1; //trabalhar de forma binária
        return 0; 
    }
    public static void main(String a[]) {
        LinkedList<Integer> entradas = new LinkedList();
        entradas.add(1);
        entradas.add(7);
        entradas.add(5);

        LinkedList<Double> pesos = new LinkedList();
        pesos.add(0.8);
        pesos.add(0.1);
        pesos.add(0.0);

        double soma = somatorio(entradas, pesos);
        System.out.println("Somatório: " + soma);

        int ativa = ativacaoStep(soma);
        System.out.println("Ativou: " + ativa);


    }
}