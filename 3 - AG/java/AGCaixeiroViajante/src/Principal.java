import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Principal {
	
	public static void gerarPopulacaoInicial(List<Cromossomo> populacao, int tamanhoPopulacao, 
			                                 Mapa mapa) {
		//rotina que coloca de 1 a n a quantidade de pontos/cidades na sequenciaPontos
		List<String> sequenciaPontos = new LinkedList<String>();
		for (int i = 1; i <= mapa.quantidadePontos; i++) {
			sequenciaPontos.add(""+i);
		}
		//sequenciaPontos = [1 2 3 4 5 6 7 8 9]
		
		
		for (int i = 0; i < tamanhoPopulacao; i++) {
			Collections.shuffle(sequenciaPontos);
			populacao.add(new Cromossomo(sequenciaPontos, mapa));
		}
	}
	
	public static void exibirPopulacao(List<Cromossomo> populacao) {
		for (int i = 0; i < populacao.size(); i++) {
			System.out.println(populacao.get(i));
		}
	}
	
	public static void ordenarPopulacao(List<Cromossomo> populacao) {
		Collections.sort(populacao);
	}
	
	public static void main(String a[]) {
		Mapa mapa = new Mapa(9);
		mapa.listaConexoes.add("1,2");
		mapa.listaConexoes.add("2,1");
		mapa.listaConexoes.add("2,4");
		mapa.listaConexoes.add("3,8");
		mapa.listaConexoes.add("4,3");
		mapa.listaConexoes.add("4,5");
		mapa.listaConexoes.add("5,3");
		mapa.listaConexoes.add("6,5");
		mapa.listaConexoes.add("7,6");
		mapa.listaConexoes.add("8,1");
		mapa.listaConexoes.add("8,9");
		mapa.listaConexoes.add("9,3");
		mapa.listaConexoes.add("9,7");

		
		List<Cromossomo> populacao = new LinkedList<Cromossomo>();
		List<Cromossomo> novaPopulacao = new LinkedList<Cromossomo>();
		
		int tamanhoPopulacao = 10;
		int taxaSelecao = 30;
		int taxaReproducao = 100 - taxaSelecao;
		
		int taxaMutacao = 5;
		int quantidadeGeracoes = 100;
		
		//gerar população inicial
		gerarPopulacaoInicial(populacao, tamanhoPopulacao, mapa);
		//calcular fitness
		//ordenar
		ordenarPopulacao(populacao);
		//exibir
		exibirPopulacao(populacao);
		
		
		//repeticao das n geracoes
			//selecionar 
			//reproduzir/cruzar
			//mutar
			//ordenar decrescente
			//exibir
		
		
	}

}
