using System;
using System.Collections.Generic;
using System.Linq;


static class Program
{
    static void gerarPopulacao(List<Cromossomo> populacao, int tamanhoPopulacao, String estadoFinal) 
    {
        for (int i = 0; i < tamanhoPopulacao; i++)
        {
            populacao.Add(new Cromossomo(Util.gerarPalavras(estadoFinal.Length), estadoFinal));
        }
    }

    static void ordenarPopulacao(List<Cromossomo> populacao) 
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

    static void exibirPopulacao(List<Cromossomo> populacao) 
    {
        for (int i = 0; i < populacao.Count; i++)
        {
            Console.WriteLine(populacao[i].valor + " - " + populacao[i].aptidao + " - " + 
            populacao[i].aptidaoPorcentagem);
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

        Console.Write("Taxa de seleção (10-90): ");
        taxaSelecao = Int32.Parse( Console.ReadLine() );
        taxaReproducao = 100 - taxaSelecao;

        Console.Write("Taxa de mutação (1-5%): ");
        taxaMutacao = Int32.Parse( Console.ReadLine() );

        Console.Write("Tamanho da população: ");
        tamanhoPopulacao = Int32.Parse( Console.ReadLine() );

        //gerar população inicial
        //calcular aptidao para cada estado/indivíduo/cromossomo da população
        gerarPopulacao(populacao,tamanhoPopulacao,estadoFinal);
        ordenarPopulacao(populacao); //decrescente pela aptidao
        Console.WriteLine("Geração 0");
        exibirPopulacao(populacao);

        /*
        for (int i = 1; i < quantidadeGeracoes; i++)
        {
            selecionarPopulacao(populacao,novaPopulacao,taxaSelecao);
            //estadoFinal é passado, pq são gerados novos cromossomos, logo é preciso 
            //calcular a aptidao desses novos cromossomos
            reproduzirPopulacao(populacao,novaPopulacao,taxaReproducao,estadoFinal);
            //testar se vai haver mutacao
            //mutar(novaPopulacao,taxaMutacao);
            ordenarPopulacao(novaPopulacao); //decrescente pela aptidao
            Console.WriteLine("Geração 0");
            exibirPopulacao(novaPopulacao);

            populacao.Clear();
            populacao.Add( novaPopulacao ); //?
            novaPopulacao.Clear();    
        }
        */
    }
}
