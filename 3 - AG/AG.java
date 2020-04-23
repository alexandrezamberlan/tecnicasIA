
public class AG {
    public static void main(String[] args) {
        int tamanhoPopulacao;
        String estadoFinal;
        int taxaSelecao;
        int taxaMutacao;
        int qtdGeracoes;
        List<Cromossomo> populacao = new ArrayList<Cromossomo>();
        List<Cromossomo> novaPopulacao = new ArrayList<Cromossomo>();

        gerarPopulacao(populacao, tamanhoPopulacao, estadoFinal);
        ordenar(populacao);
        System.out.println("Geracao 0");
        exibir(populacao);

        //gerações
        for (int i = 0; i < geracoes; i++) {
            //selecionar
            //selecionar(novaPopulacao, populacao, taxaReproducao);

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
        }
    }

}