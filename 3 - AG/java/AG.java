import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;
public class AG {
    static void gerarPopulacao(List<Cromossomo> populacao, int tamanhoPopulacao, String estadoFinal){
        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao.add(new Cromossomo(Util.gerarPalavra(estadoFinal.length()), estadoFinal));
        }
    }

    static void ordenar(List<Cromossomo> populacao) {
        Collections.sort(populacao);
    }

    static void exibir(List<Cromossomo> populacao){
        for (int i = 0; i < populacao.size(); i++) {
            System.out.println("Cromossomo: " + populacao.get(i).valor + 
                                                " - " + populacao.get(i).aptidao + 
                                                " - " + populacao.get(i).porcentagemAptidao);
        }
    }

    public static void main(String[] args) {
        int tamanhoPopulacao = 20;
        String estadoFinal = "inteligencia";
        int taxaSelecao = 70;
        int taxaReproducao = 100 - taxaSelecao;
        int taxaMutacao;
        int qtdGeracoes;

        List<Cromossomo> populacao = new ArrayList<Cromossomo>();
        List<Cromossomo> novaPopulacao = new ArrayList<Cromossomo>();

        gerarPopulacao(populacao, tamanhoPopulacao, estadoFinal);
        ordenar(populacao);
        System.out.println("Geracao 0");
        exibir(populacao);

        //gerações
        //for (int i = 1; i < geracoes; i++) {
            //selecionar
            //selecionar(novaPopulacao, populacao, taxaSelecao);

            //cruzar
            //reproduzir(novaPopulacao, populacao, taxaReproducao, estadoFinal);
            
            //mutacao
            //definir o momento da mutacao
            //mutacao(novaPopulacao);
            
            
            // populacao.clear();
            // populacao.addAll(novaPopulacao);
            // novaPopulacao.clear();
            // ordenar(populacao);

            // System.out.println("\n\nGeracao " + (i + 1));
            // exibir(populacao);
        //}
    }

}