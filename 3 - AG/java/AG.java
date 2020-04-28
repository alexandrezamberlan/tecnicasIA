import java.util.List;
import java.util.Random;
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
        //Collections.sort(populacao);
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

    static void exibir(List<Cromossomo> populacao){
        for (int i = 0; i < populacao.size(); i++) {
            System.out.println("Cromossomo: " + populacao.get(i).valor + 
                                                " - " + populacao.get(i).aptidao + 
                                                " - " + populacao.get(i).porcentagemAptidao);
        }
    }

    static void selecionarPorTorneio(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaSelecao) {
        //hoje vamos tentar implementar a seleção por torneio ao invés da roleta
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

            if (!novaPopulacao.contains(selecionado)) {
                novaPopulacao.add(selecionado);
                i++;
            }
        } while (i < qtdSelecionados);
    }

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

    public static void main(String[] args) {
        int tamanhoPopulacao = 10;
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
        for (int i = 1; i < 2; i++) {
            //selecionar
            
            selecionarPorRoleta(populacao, novaPopulacao, taxaSelecao); //método que TENTA selecionar os mais aptos
            

            //cruzar
            //reproduzir(novaPopulacao, populacao, taxaReproducao, estadoFinal);
            
            //mutacao
            //definir o momento da mutacao
            //mutacao(novaPopulacao);
            
            
            populacao.clear();
            populacao.addAll(novaPopulacao);
            novaPopulacao.clear();
            ordenar(populacao);

            System.out.println("\n\nGeracao " + (i + 1));
            exibir(populacao);
        }
    }

}