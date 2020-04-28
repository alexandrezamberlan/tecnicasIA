
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

class Util {

    static String letras = "abcdefghijklmnopqrstuvxwyz ABCDEFGHIJHIJKLMNOPQRSTUVXWYZ";
    static int tamanho = letras.length();

    public static StringBuffer gerarPalavra(int n) {
        StringBuffer palavra = new StringBuffer();
        Random gerador = new Random();
        for (int i = 0; i < n; i++) {
            palavra.append(letras.charAt(gerador.nextInt(tamanho)));
        }
        return palavra;
    }
}

final class Cromossomo implements Comparable<Cromossomo> {

    StringBuffer valor;
    int aptidao;
    int aptidaoPorcentagem;

    public Cromossomo(StringBuffer valor, int aptidao) {
        this.valor = valor;
        this.aptidao = aptidao;
    }

    public Cromossomo(StringBuffer valor, String estadoFinal) {
        this.valor = valor;
        this.aptidao = calcularAptidao(estadoFinal);
    }

    int calcularAptidao(String estadoFinal) {
        int nota = 0;
        for (int i = 0; i < estadoFinal.length(); i++) {
            if (this.valor.toString().contains(estadoFinal.charAt(i) + "")) {
                nota += 5;
            }
            if (this.valor.toString().charAt(i) == estadoFinal.charAt(i)) {
                nota += 50;
            }
        }
        return nota;
    }

    @Override
    public int compareTo(Cromossomo cromossomo) {
        if (this.aptidao > cromossomo.aptidao) {
            return -1;
        }
        return 1;
    }
}

public class AG_SugestaoFinal {

    static void gerarPopulacao(List<Cromossomo> populacao, int n, String estadoFinal) {
        for (int i = 0; i < n; i++) {
            populacao.add(new Cromossomo(Util.gerarPalavra(estadoFinal.length()), estadoFinal));
        }
    }

    static void ordenarPopulacao(List<Cromossomo> populacao) {
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

    static void exibirPopulacao(List<Cromossomo> populacao) {
        for (int i = 0; i < populacao.size(); i++) {
            System.out.println(populacao.get(i).valor + "  " + populacao.get(i).aptidao);
        }
    }

    static void gerarSelecao(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaSelecao) {
        //método da roleta

        //calcular a aptidao total
        int aptidaoTotal = 0;
        for (int i = 0; i < populacao.size(); i++) {
            aptidaoTotal += populacao.get(i).aptidao;
        }

        //aptidaoTotal -> 100
        //aptidao      -> aptidaoPorcentagem
        //aptidaoPorcentagem = aptidao * 100 / aptidaoTotal;
        for (int i = 0; i < populacao.size(); i++) {
            populacao.get(i).aptidaoPorcentagem = populacao.get(i).aptidao * 100 / aptidaoTotal;
            if (populacao.get(i).aptidaoPorcentagem == 0) {
                populacao.get(i).aptidaoPorcentagem = 1;
            }
        }

        List<Cromossomo> sorteio = new ArrayList<>();
        for (int i = 0; i < populacao.size(); i++) {
            for (int j = 0; j < populacao.get(i).aptidaoPorcentagem; j++) {
                sorteio.add(populacao.get(i));
            }
        }

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
            selecionado = sorteio.get(posicaoSorteio);

            novaPopulacao.add(selecionado);

            while (sorteio.remove(selecionado)){} //controle dos visitados
        }
    }

    static void gerarReproducao(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaReproducao, String estadoFinal) {
        Random gerador = new Random();
        int posicaoMae, posicaoPai;
        String mae, pai, filho1, filho2;

        int frequencia = taxaReproducao * populacao.size() / 100;

        for (int i = 0; i <= frequencia / 2 + 1; i++) {
            posicaoPai = gerador.nextInt(populacao.size());
            do {
                posicaoMae = gerador.nextInt(populacao.size());
            } while (posicaoPai == posicaoMae);

            pai = (populacao.get(posicaoPai).valor).toString();
            mae = (populacao.get(posicaoMae).valor).toString();

            filho1 = pai.substring(0, pai.length() / 2) + mae.substring(mae.length() / 2, mae.length());
            filho2 = mae.substring(0, mae.length() / 2) + pai.substring(pai.length() / 2, pai.length());

            novaPopulacao.add(new Cromossomo(new StringBuffer(filho1), estadoFinal));
            novaPopulacao.add(new Cromossomo(new StringBuffer(filho2), estadoFinal));
        }
        //aparar o final da lista novaPopulacao para que fique do tamanho padrao
        while (novaPopulacao.size() > populacao.size()) {
            novaPopulacao.remove(novaPopulacao.size() - 1);
        }
        //System.out.println(novaPopulacao.size());
    }

    static void gerarMutacao(List<Cromossomo> novaPopulacao, String estadoFinal) {
        Random gerador = new Random();
        int qtdMutantes = gerador.nextInt(novaPopulacao.size()/10);
        Cromossomo mutante;
        int posicaoMutante;

        for (; qtdMutantes > 0; qtdMutantes--) {
            posicaoMutante = gerador.nextInt(novaPopulacao.size());
            mutante = novaPopulacao.get(posicaoMutante);
            JOptionPane.showMessageDialog(null,"vai mutar " + mutante.valor + "  " + mutante.aptidao);
            //mudando
            String valorMutado = mutante.valor.toString();
            char caracterMutante = mutante.valor.charAt(gerador.nextInt(mutante.valor.length()));
            char caracterSorteado = Util.letras.charAt(gerador.nextInt(Util.tamanho));
            valorMutado = valorMutado.replace(caracterMutante, caracterSorteado);          
            mutante = new Cromossomo(new StringBuffer(valorMutado), estadoFinal);
            JOptionPane.showMessageDialog(null, "mudado " + mutante.valor + "  " + mutante.aptidao);
            //recalculando sua aptidao
            novaPopulacao.set(posicaoMutante, mutante);
        }
    }

    public static void main(String[] args) {
        int tamanhoPopulacao = Integer.parseInt(JOptionPane.showInputDialog(null,"Informe o tamanho da populacao"));
        int taxaSelecao = Integer.parseInt(JOptionPane.showInputDialog(null,"Informe a taxa de selecao"));
        int taxaMutacao = Integer.parseInt(JOptionPane.showInputDialog(null,"Informe a taxa de mutacao"));
        int qtdGeracoes = Integer.parseInt(JOptionPane.showInputDialog(null,"Informe a quantidade de geracoes"));
        String estadoFinal = JOptionPane.showInputDialog(null,"Informe a palavra desejada");

        List<Cromossomo> populacao = new ArrayList<>();
        List<Cromossomo> novaPopulacao = new ArrayList<>();

        gerarPopulacao(populacao, tamanhoPopulacao, estadoFinal);

        ordenarPopulacao(populacao);
        System.out.println("Geração 0");
        exibirPopulacao(populacao);

        //regra de 3
        //qtdGeracoes -> 100
        //frequencia -> taxaMutacao
        int frequencia = qtdGeracoes * (100 - taxaMutacao) / 100;

        for (int i = 1; i < qtdGeracoes; i++) {
            //selecionar
            gerarSelecao(populacao, novaPopulacao, taxaSelecao);

            //reproduzir
            gerarReproducao(populacao, novaPopulacao, 100 - taxaSelecao, estadoFinal);

            //mutar de tempos em tempos
            if (i % frequencia == 0) {
                System.out.println("Nessa geração haverá mutação!!\n\n");
                JOptionPane.showMessageDialog(null, "Nessa geração haverá mutação!!\n\n");
                gerarMutacao(novaPopulacao, estadoFinal);               
            }

            populacao.clear();
            populacao.addAll(novaPopulacao);
            novaPopulacao.clear();

            ordenarPopulacao(populacao);
            System.out.println("Geração " + i);
            exibirPopulacao(populacao);
        }

    }

}
