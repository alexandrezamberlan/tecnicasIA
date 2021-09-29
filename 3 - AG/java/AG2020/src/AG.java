import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;

/**
 * Classe que executa um AG sobre o problema da palavra desejada
 * @author alexandrezamberlan
 */
public class AG {
    
    /**
     * Método de classe que gera a 1a população totalmente aleatória
     * @param populacao lista para os indivíduos gerados
     * @param tamanhoPopulacao quantos indivíduos/estados se quer colocar na lista
     * @param estadoFinal palavra/valor desejado
     */
    static void gerarPopulacao(List<Cromossomo> populacao, int tamanhoPopulacao, String estadoFinal){
        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao.add(new Cromossomo(Util.gerarPalavra(estadoFinal.length()), estadoFinal));
        }
    }

    /**
     * Método de classe que ordena uma lista contendo Cromossomos/Estados/Indivíduos
     * @param populacao 
     */
    static void ordenar(List<Cromossomo> populacao) {
        // Collections.sort(populacao); //O sort do Java é baseado no MergeSort
        boolean houveTroca;
        Cromossomo tmp;
        int distancia = populacao.size();

        do {
            distancia = (int) (distancia / 1.3);
            if (distancia <= 0) {
                distancia = 1;
            }
            houveTroca = false;
            for (int i = 0; i < populacao.size() - distancia; i++) {
                if (populacao.get(i).aptidao < populacao.get(i + distancia).aptidao) {
                    tmp = populacao.get(i);
                    populacao.set(i, populacao.get(i + distancia));
                    populacao.set(i + distancia, tmp);
                    houveTroca = true;
                }
            }
        } while (distancia > 1 || houveTroca);
    }

    /**
     * Método de classe que exibe os Cromossomos de uma lista
     * @param populacao lista contendo todos os cromossomos/indivíduos/estados 
     */
    static void exibir(List<Cromossomo> populacao){
        for (int i = 0; i < populacao.size(); i++) {
            System.out.println("Cromossomo: " + populacao.get(i).valor + 
                                                " - " + populacao.get(i).aptidao + 
                                                " - " + populacao.get(i).porcentagemAptidao);
        }
    }

    /**
     * Método de classe que seleciona elementos/cromossomos/indivíduos para a novaPopulacao a partir do algoritmo de sorteio
     * @param populacao lista com os Cromossomos da população atual
     * @param novaPopulacao lista para os Cromossomos selecionados
     * @param taxaSelecao porcentagem (sobre 100) de quantos serão selecionados
     */
    static void selecionarPorTorneio(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaSelecao) {
        //OBS.: a populacao nao pode ser pequena e nem a taxa de selecao ser muito alta

        Cromossomo c1, c2, c3; //elementos sorteados para o torneio
        List<Cromossomo> torneio = new ArrayList<Cromossomo>();
        Cromossomo selecionado;

        //calcular quantos devem ser selecionados a partir do tamanho da populacao com a taxaSelecao
        //populacao.size()	->	100
        //qtdSelecionados	-> 	taxaSelecao
        int qtdSelecionados = taxaSelecao * populacao.size() / 100;

        novaPopulacao.add(populacao.get(0)); //elistismo
        
        Random gerador = new Random();
        //repetir o sorteio até termos a qtdSelecionados exigidos
        int i = 1;
        do {
            c1 = populacao.get( gerador.nextInt(populacao.size()) );
            do {
                c2 = populacao.get( gerador.nextInt(populacao.size()) );
            } while (c2.equals(c1));
            do {
                c3 = populacao.get( gerador.nextInt(populacao.size()) );
            } while (c3.equals(c1) || c3.equals(c2));

            torneio.add(c1);
            torneio.add(c2);
            torneio.add(c3);
            ordenar(torneio);//o primeiro é o mais apto

            selecionado = torneio.get(0);

            if (!novaPopulacao.contains(selecionado)) { //controle de visitados
                novaPopulacao.add(selecionado);
                i++;
                //System.out.println("selecionado.......... " + selecionado.valor );
            }
            
            torneio.clear(); //FALTOU LIMPAR A LISTA torneio para a próxima rodada
        
        } while (i <= qtdSelecionados);
    }

    /**
     * Método de classe que seleciona elementos/cromossomos/indivíduos para a novaPopulacao a partir do algoritmo da roleta
     * @param populacao lista com os Cromossomos da população atual
     * @param novaPopulacao lista para os Cromossomos selecionados
     * @param taxaSelecao porcentagem (sobre 100) de quantos serão selecionados
     */
    static void selecionarPorRoleta(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaSelecao) {
        //método da roleta
        //calcular a aptidao total
        int aptidaoTotal = 0;
        for (int i = 0; i < populacao.size(); i++) {
            aptidaoTotal += populacao.get(i).aptidao;
        }
        System.out.println("Aptidão total: " + aptidaoTotal);

        //aptidaoTotal -> 100
        //aptidao      -> porcentagemAptidao
        //porcentagemAptidao = aptidao * 100 / aptidaoTotal;
        for (int i = 0; i < populacao.size(); i++) {
            populacao.get(i).porcentagemAptidao = populacao.get(i).aptidao * 100 / aptidaoTotal;
            if (populacao.get(i).porcentagemAptidao == 0) {
                populacao.get(i).porcentagemAptidao = 1;
            }
        }
        
        List<Cromossomo> sorteio = new ArrayList<>();
        for (int i = 0; i < populacao.size(); i++) {
            for (int j = 0; j < populacao.get(i).porcentagemAptidao; j++) {
                sorteio.add(populacao.get(i));
            }
        }

        System.out.println("Tamanho da lista sorteio: " + sorteio.size());

        Random gerador = new Random();
        int posicaoSorteio;
        
        //populacao.size()	->	100
        //qtdSelecionados	-> 	taxaSelecao
        int qtdSelecionados = taxaSelecao * populacao.size() / 100;
        
        //elitismo
        novaPopulacao.add(populacao.get(0));
        Cromossomo selecionado;

        for (int i = 1; i <= qtdSelecionados; i++) {
            posicaoSorteio = gerador.nextInt(sorteio.size());
            
            try {
                selecionado = sorteio.get(posicaoSorteio);

                novaPopulacao.add(selecionado);

                while (sorteio.remove(selecionado)){} //controle dos visitados
            } catch (Exception e) {
                System.out.println("Tentou pegar uma posição inválida do sorteio");
            }
            
        }
    }

    /**
     * Método de classe que gera novos Cromossomos a partir de um cromossomo Pai e outro Mae
     * @param populacao lista com os cromossomos vigentes
     * @param novaPopulacao lista para os cromossomos gerados no cruzamento entre pai e uma mae
     * @param taxaReproducao porcentagem (sobre 100) de quantos serão reproduzidos/criados/gerados
     * @param estadoFinal palavra desejada que é utilizada para calcular a aptidão dos cromossomos gerados
     */
    public static void reproduzir(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaReproducao, String estadoFinal) {
        String sPai,sMae,sFilho1,sFilho2;
        Random gerador = new Random();
        Cromossomo pai, mae;
        
        //calcular quantos devem ser reproduzidos a partir do tamanho da populacao com a taxaReproducao
        //populacao.size()	->	100
        //qtdReproduzido	-> 	taxaReproducao
        int qtdReproduzidos = taxaReproducao * populacao.size() / 100;

        //sFilho1 = Alexone - primeiraMetadeDoPai + segundaMetadeDaMae;
        //sFilho2 = Simandre - primeiraMetadeDaMae + segundaMetadeDoPai;
        int i = 0;
        do {
            pai = populacao.get( gerador.nextInt(populacao.size()) ); //futuramente, o pai pode ser um indivíduo bom
            do {
                mae = populacao.get( gerador.nextInt(populacao.size()) );
            } while (mae.equals(pai));

            sPai = pai.valor.toString();
            sMae = mae.valor.toString();
            
            sFilho1 = sPai.substring(0, sPai.length() / 2) + sMae.substring(sMae.length() / 2, sMae.length());
            sFilho2 = sMae.substring(0, sMae.length() / 2) + sPai.substring(sPai.length() / 2, sPai.length());

            novaPopulacao.add(new Cromossomo(new StringBuffer(sFilho1), estadoFinal)); //estadoFinal é passado para calcular aptidao do filho
            novaPopulacao.add(new Cromossomo(new StringBuffer(sFilho2), estadoFinal)); //estadoFinal é passado para calcular aptidao do filho
            i = i + 2;

        } while (i < qtdReproduzidos);
        
        //podar a novaPopulacao, retirando os excedentes do final
        while(novaPopulacao.size() > populacao.size()) {
            novaPopulacao.remove(novaPopulacao.size() - 1);
        }
    }

    /**
     * Método de classe com a função de esporadicamente mutar elementos da populacao passada no parametro
     * @param populacao lista de cromossomos vigentes
     * @param estadoFinal palavra desejada, em que é utilizada para recalcular a aptidão dos cromossomos mutados
     */
    public static void mutar(List<Cromossomo> populacao, String estadoFinal) {
        Random gerador = new Random();
        int qtdMutantes = gerador.nextInt(populacao.size() / 5); //a qtd de mutantes será no máximo 20% da população
        Cromossomo mutante;
        int posicaoMutante;

        for (; qtdMutantes > 0; qtdMutantes--) {
            posicaoMutante = gerador.nextInt(populacao.size());
            mutante = populacao.get(posicaoMutante);
            System.out.println("vai mutar " + mutante.valor + "  " + mutante.aptidao);
            //mudando
            String valorMutado = mutante.valor.toString();
            char caracterMutante = mutante.valor.charAt(gerador.nextInt(mutante.valor.length()));
            char caracterSorteado = Util.letras.charAt(gerador.nextInt(Util.tamanho));
            valorMutado = valorMutado.replace(caracterMutante, caracterSorteado);          
            mutante = new Cromossomo(new StringBuffer(valorMutado), estadoFinal);
            //JOptionPane.showMessageDialog(null, "mudado " + mutante.valor + "  " + mutante.aptidao);
            //recalculando sua aptidao
            populacao.set(posicaoMutante, mutante);
        }
    }

    public static void main(String[] args) {
        int tamanhoPopulacao = Integer.parseInt(JOptionPane.showInputDialog(null,"Tamanho da população"));
        String estadoFinal = JOptionPane.showInputDialog(null,"Palavra desejada");
        int taxaSelecao = Integer.parseInt(JOptionPane.showInputDialog(null,"Taxa de seleção (entre 20 a 40%)"));
        int taxaReproducao = 100 - taxaSelecao;
        int taxaMutacao = Integer.parseInt(JOptionPane.showInputDialog(null,"Taxa de mutação (entre 5 a 10%)"));
        int qtdGeracoes = Integer.parseInt(JOptionPane.showInputDialog(null,"Quantidade de gerações"));

        List<Cromossomo> populacao = new ArrayList<>();
        List<Cromossomo> novaPopulacao = new ArrayList<>();

        //1a população que é 100% aleatória
        gerarPopulacao(populacao, tamanhoPopulacao, estadoFinal);
        ordenar(populacao);
        System.out.println("Geracao 1");
        exibir(populacao);

        //gerações
        for (int i = 1; i < qtdGeracoes; i++) {
            //selecionar
            selecionarPorTorneio(populacao, novaPopulacao, taxaSelecao); //método que TENTA selecionar os mais aptos
            
            //cruzar
            reproduzir(populacao, novaPopulacao, taxaReproducao, estadoFinal);//método que cria novos indivíduos
            
            //mutacao
            //definir o momento da mutacao
            if (i % (populacao.size() / taxaMutacao) == 0) {
                mutar(novaPopulacao, estadoFinal); //estadoFinal é passado, pq indivíduos mutados devem ter suas aptidões recalculadas
            }
            
            populacao.clear();
            populacao.addAll(novaPopulacao);
            novaPopulacao.clear();
            ordenar(populacao);

            System.out.println("\n\nGeracao " + (i + 1));
            exibir(populacao);
        }
    }

}