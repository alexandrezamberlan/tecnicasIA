import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Principal {
	
	public static void gerarPopulacaoInicial(List<Cromossomo> populacao, int tamanhoPopulacao, Mapa mapa) {
		//rotina que coloca de 1 a n a quantidade de pontos/cidades na sequenciaPontos
		List<String> sequenciaPontos = new LinkedList<String>();
		for (int i = 1; i <= mapa.quantidadePontos; i++) {
			sequenciaPontos.add(""+i);
		}
		//sequenciaPontos = [1 2 3 4 5 6 7 8 9]
		
		
		for (int i = 0; i < tamanhoPopulacao; i++) {
			Collections.shuffle(sequenciaPontos); //embaralhando
			populacao.add(new Cromossomo(sequenciaPontos, mapa));
		}
	}
	
	public static void exibirPopulacao(List<Cromossomo> populacao) {
		for (int i = 0; i < populacao.size(); i++) {
			System.out.println(populacao.get(i));
		}
	}
	
	public static void ordenarPopulacao(List<Cromossomo> populacao) {
		// Collections.sort(populacao); //ordena por MergeSort
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
                if (populacao.get(i).aptidao > populacao.get(i + distancia).aptidao) {
                    tmp = populacao.get(i);
                    populacao.set(i, populacao.get(i + distancia));
                    populacao.set(i + distancia, tmp);
                    houveTroca = true;
                }
            }
        } while (distancia > 1 || houveTroca);

	}
	
	public static void selecionarPorRoletaPopulacao(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaSelecao) {
		//calcular a aptidao total
        int aptidaoTotal = 0;
        for (int i = 0; i < populacao.size(); i++) {
            aptidaoTotal += populacao.get(i).aptidao;
        }

        //aptidaoTotal -> 100
        //aptidao      -> aptidaoPorcentagem
        //aptidaoPorcentagem = aptidao * 100 / aptidaoTotal;
        for (int i = 0; i < populacao.size(); i++) {
            populacao.get(i).porcentagemAptidao = populacao.get(i).aptidao * 100 / aptidaoTotal;
            if (populacao.get(i).porcentagemAptidao == 0) {
                populacao.get(i).porcentagemAptidao = 1;
            }
        }

        List<Cromossomo> sorteio = new LinkedList<>();
        for (int i = 0; i < populacao.size(); i++) {
            for (int j = 0; j < populacao.get(i).porcentagemAptidao; j++) {
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
	
	public static void selecionarPorTorneioPopulacao(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaSelecao) {
		//OBS.: a populacao nao pode ser pequena e nem a taxa de selecao ser muito alta

        Cromossomo c1, c2, c3; //elementos sorteados para o torneio
        List<Cromossomo> torneio = new LinkedList<Cromossomo>();
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
            ordenarPopulacao(torneio);//o primeiro é o mais apto

            selecionado = torneio.get(0);

            if (!novaPopulacao.contains(selecionado)) { //controle de visitados
                novaPopulacao.add(selecionado);
                i++;
                //System.out.println("selecionado.......... " + selecionado.valor );
            }
            
            torneio.clear(); //FALTOU LIMPAR A LISTA torneio para a próxima rodada
        
        } while (i <= qtdSelecionados);
	}
	
	public static void reproduzirPopulacao(List<Cromossomo> populacao, List<Cromossomo> novaPopulacao, int taxaReproducao, Mapa mapa) {
		List<String> caminhoPai = new LinkedList<>();
		List<String> caminhoMae = new LinkedList<>();
		List<String> caminhoFilho1 = new LinkedList<>();
		List<String> caminhoFilho2 = new LinkedList<>();
		
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
            pai = populacao.get( gerador.nextInt(populacao.size()) / 4 ); //futuramente, o pai pode ser um indivíduo bom
            do {
                mae = populacao.get( gerador.nextInt(populacao.size()) );
            } while (mae.equals(pai));

            caminhoPai.addAll(pai.caminho);
            caminhoMae.addAll(mae.caminho);
            
            //caminhoFilho1 = primeiraMetadeDoPai + segundaMetadeDaMae;
            for (int j = 0; j < (int)caminhoPai.size()/2; j++) {
            	caminhoFilho1.add(caminhoPai.get(j));
            }
            for (int j = ((int)caminhoMae.size() / 2); j < caminhoMae.size(); j++ ) {
            	caminhoFilho1.add(caminhoMae.get(j));
            }
            
            //caminhoFilho2 = primeiraMetadeDaMae + segundaMetadeDoPai;
            for (int j = 0; j < (int)caminhoMae.size()/2; j++) {
            	caminhoFilho2.add(caminhoMae.get(j));
            }
            for (int j = ((int)caminhoPai.size() / 2); j < caminhoMae.size(); j++ ) {
            	caminhoFilho2.add(caminhoPai.get(j));
            }

            novaPopulacao.add(new Cromossomo(caminhoFilho1, mapa)); 
            novaPopulacao.add(new Cromossomo(caminhoFilho2, mapa)); 
            i = i + 2;
            
            caminhoPai.clear();
            caminhoMae.clear();
            caminhoFilho1.clear();
            caminhoFilho2.clear();

        } while (i < qtdReproduzidos);
        
        //podar a novaPopulacao, retirando os excedentes do final
        while(novaPopulacao.size() > populacao.size()) {
            novaPopulacao.remove(novaPopulacao.size() - 1);
        }
	}
	
	public static void mutarPopulacao(List<Cromossomo> populacao,  Mapa mapa) {
		Random gerador = new Random();
        int qtdMutantes = gerador.nextInt(populacao.size() / 5); //somente 20% dos elementos vão mutar
        Cromossomo mutante;
        int posicaoMutante;
        List<String> caminhoASerMutado = new LinkedList<>();
        int posicaoDoPontoASerMutado;
        
        for (; qtdMutantes > 0; qtdMutantes--) {
            posicaoMutante = gerador.nextInt(populacao.size());
            mutante = populacao.get(posicaoMutante);
            System.out.println("vai mutar " + mutante.caminho + "  " + mutante.aptidao);
            //mudando
            caminhoASerMutado.addAll(mutante.caminho);
            posicaoDoPontoASerMutado = gerador.nextInt(mapa.quantidadePontos);
            caminhoASerMutado.set(posicaoDoPontoASerMutado, ""+gerador.nextInt(mapa.quantidadePontos));
            
            //recalculando sua aptidao
            mutante = new Cromossomo(caminhoASerMutado, mapa);

            populacao.set(posicaoMutante, mutante);
            caminhoASerMutado.clear();
        }
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
		
		int tamanhoPopulacao = 100; //tamanho influencia no espaço de busca e combinações
		int taxaSelecao = 30;
		int taxaReproducao = 100 - taxaSelecao;
		
		int taxaMutacao = 5;
		int quantidadeGeracoes = 1000;
		
		//gerar população inicial
		gerarPopulacaoInicial(populacao, tamanhoPopulacao, mapa);
		//calcular fitness
		//ordenar
		ordenarPopulacao(populacao);
		//exibir
		System.out.println("Geração 0");
		exibirPopulacao(populacao);
		
		
		//repeticao das n geracoes
		for (int i = 1; i < quantidadeGeracoes; i++) {
			//selecionar 
			selecionarPorTorneioPopulacao(populacao, novaPopulacao, taxaSelecao);
			
			//reproduzir/cruzar
			reproduzirPopulacao(populacao, novaPopulacao, taxaReproducao, mapa);
			
			//mutar
			if (i % (populacao.size() / taxaMutacao) == 0) {
                mutarPopulacao(novaPopulacao, mapa); 
            }
			
			populacao.clear();
            populacao.addAll(novaPopulacao);
            novaPopulacao.clear();
            ordenarPopulacao(populacao);

            System.out.println("\n\nGeracao " + (i + 1));
            exibirPopulacao(populacao);


		}
	}

}
