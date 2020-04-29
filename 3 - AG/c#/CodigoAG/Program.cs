using System;
using System.Collections.Generic;
using System.Linq;


static class Program
{
    static void gerar(List<Cromossomo> populacao, int tamanhoPopulacao, String estadoFinal) 
    {
        for (int i = 0; i < tamanhoPopulacao; i++)
        {
            populacao.Add(new Cromossomo(Util.gerarPalavras(estadoFinal.Length), estadoFinal));
        }
    }

    static void ordenar(List<Cromossomo> populacao) 
    {
        //implementação do método do pente - combsort
        int distancia = populacao.Count;
        bool houveTroca;
        do {
            distancia = (int) (distancia / 1.3);

            if (distancia < 1) {
                distancia = 1;
            }
            houveTroca = false;
            for (int i = 0; i + distancia < populacao.Count; i++) {
                if ((int) populacao[i].aptidao < (int) populacao[i + distancia].aptidao) {
                    houveTroca = true;
                    Cromossomo temp = populacao[i];
                    populacao[i] = populacao[i + distancia];
                    populacao[i + distancia] =  temp;
                }
            }
        } while (distancia != 1 || houveTroca);
    }

    static void exibir(List<Cromossomo> populacao) 
    {
        for (int i = 0; i < populacao.Count; i++)
        {
            Console.WriteLine(populacao[i].valor + " - " + populacao[i].aptidao + " - " + 
            populacao[i].aptidaoPorcentagem);
        }

    }

    static void selecionarPorTorneio(List<Cromossomo> populacao, 
                                     List<Cromossomo> novaPopulacao, int taxaSelecao) 
    {
        //100          -  populacao.count
        //taxaSelecao  - qtdSelecionados
        int qtdSelecionados = (taxaSelecao * populacao.Count) / 100;
        // Console.WriteLine("Quantidade de selecionados: " + qtdSelecionados);
        
        Cromossomo c1, c2, c3;
        Random gerador = new Random();
        List<Cromossomo> torneio = new List<Cromossomo>();
        Cromossomo selecionado;

        int i  = 0;
        do {
            c1 = populacao[ gerador.Next( populacao.Count ) ];

            do {
                c2 = populacao[ gerador.Next( populacao.Count ) ];
            } while (c2.valor.Equals(c1.valor));

            do {
                c3 = populacao[ gerador.Next( populacao.Count ) ];
            } while (c3.valor.Equals(c1.valor) || c3.valor.Equals(c2.valor));

            torneio.Add(c1);
            torneio.Add(c2);
            torneio.Add(c3);

            ordenar(torneio); //ordena de forma decrescente do mais apto ao menos apto
            selecionado = torneio[0];
            
            if (!Util.contem(novaPopulacao, selecionado))
            {
                novaPopulacao.Add(selecionado);
                i++;
                //Console.WriteLine("selecionado.... " + selecionado.valor);
            }
            torneio.Clear();
        } while (i < qtdSelecionados);
    }

    static public void reproduzir(List<Cromossomo> populacao,
                                  List<Cromossomo> novaPopulacao,
                                  int taxaReproducao, String estadoFinal)
    {
        //100             -  populacao.count
        //taxaReproducao  - qtdReproduzidos == qtdGeradosNovos
        int qtdReproduzidos = (taxaReproducao * populacao.Count) / 100;
        
        Cromossomo pai;
        Cromossomo mae;
        
        String sPai, sMae, sFilho1, sFilho2;

        Random gerador = new Random();

        int i = 0;
        do {
            pai = populacao[ gerador.Next( populacao.Count ) ];

            do {
                mae = populacao[ gerador.Next( populacao.Count ) ];
            } while (mae.valor.Equals(pai.valor));

            sPai = pai.valor;
            sMae = mae.valor;
            //sPai - "teswtaetyo"
            //sMae - "abcdefghih"
            //sFilho1 = primeiraMetadeDoPai + segundaMetadeDaMae;
            //sFilho2 = primeiraMetadeDaMae + segundaMetadeDoPai; 
            //sFilho1 = "teswt fghih"
            //sFilho2 = "abcde aetyo"
            
            sFilho1 = sPai.Substring(0 , (int)sPai.Length / 2) + sMae.Substring((int)sMae.Length / 2, (int)sMae.Length / 2);
            sFilho2 = sMae.Substring(0 , (int)sMae.Length / 2) + sPai.Substring((int)sPai.Length / 2, (int)sPai.Length / 2);

            // sFilho1 = sPai.Substring(0 , (int)sPai.Length / 2 + 1) + sMae.Substring((int)sMae.Length / 2, (int)sMae.Length / 2 + 1);
            // sFilho2 = sMae.Substring(0 , (int)sMae.Length / 2 + 1) + sPai.Substring((int)sPai.Length / 2, (int)sPai.Length / 2) + 1;


            novaPopulacao.Add(new Cromossomo(sFilho1, estadoFinal));
            novaPopulacao.Add(new Cromossomo(sFilho2, estadoFinal));
            i = i + 2;

        } while (i < qtdReproduzidos);

        //podar o final que sobra da novaPopulacao
        while (populacao.Count < novaPopulacao.Count) {
            //podar o fim da novaPopulacao
            novaPopulacao.RemoveAt(novaPopulacao.Count-1);
        }
    }

    static void Main()
    {
        Console.WriteLine("Algoritmos Genéticos");
        List<Cromossomo> populacao = new List<Cromossomo>();
        List<Cromossomo> novaPopulacao = new List<Cromossomo>();

        String estadoFinal;
        int quantidadeGeracoes;
        int taxaSelecao;
        int taxaReproducao;
        int taxaMutacao;
        int tamanhoPopulacao;

        Console.Write("Qual a palavra ou frase que quer testar como estado final? ");
        estadoFinal = Console.ReadLine();

        Console.Write("Quantas gerações pretende executar? "); //serve para o for
        quantidadeGeracoes = Int32.Parse( Console.ReadLine() );

        do {
            Console.Write("Taxa de seleção (10-90): ");
            taxaSelecao = Int32.Parse( Console.ReadLine() );
            taxaReproducao = 100 - taxaSelecao;
        } while (taxaSelecao <= 10 || taxaSelecao > 90);

        do {
            Console.Write("Taxa de mutação (1-5%): ");
            taxaMutacao = Int32.Parse( Console.ReadLine() );
        } while (taxaMutacao <= 0 || taxaMutacao > 5);

        Console.Write("Tamanho da população: ");
        tamanhoPopulacao = Int32.Parse( Console.ReadLine() );

        //gerar população inicial
        //calcular aptidao para cada estado/indivíduo/cromossomo da população
        gerar(populacao,tamanhoPopulacao,estadoFinal);
        ordenar(populacao); //decrescente pela aptidao
        Console.WriteLine("Geração 1");
        exibir(populacao);

        
        for (int i = 1; i < quantidadeGeracoes; i++)
        {
            selecionarPorTorneio(populacao,novaPopulacao,taxaSelecao);
            //estadoFinal é passado, pq são gerados novos cromossomos, logo é preciso 
            //calcular a aptidao desses novos cromossomos
            
            reproduzir(populacao,novaPopulacao,taxaReproducao,estadoFinal);
            //testar se vai haver mutacao
            //mutar(novaPopulacao,taxaMutacao);
            
            ordenar(novaPopulacao); //decrescente pela aptidao
            Console.WriteLine("Geração " + (i+1));
            exibir(novaPopulacao);

            populacao.Clear();
            // Util.adicionaTodos(populacao,novaPopulacao);
            populacao.AddRange( novaPopulacao ); 
            novaPopulacao.Clear();    
        }
        
    }
}
