using System;
using System.Collections.Generic;
namespace RNA_Perceptron_Times
{
    public class Perceptron
    {
        public List<Ponto> amostras = new List<Ponto>();
        public List<Int32> saidas = new List<Int32>();
        public double taxa_aprendizado;
        public int geracoes;
        public int limiar;
        public int numeroAmostras;
        public int numeroAtributos;

        public double[] pesos;

        public Perceptron(List<Ponto> amostras, List<Int32> saidas, double taxa_aprendizado,
            int geracoes, int limiar)
        {
            this.amostras = amostras;
            this.saidas = saidas;
            this.taxa_aprendizado = taxa_aprendizado;
            this.geracoes = geracoes;
            this.limiar = limiar;
            this.numeroAmostras = amostras.Count;
            this.pesos = new double[3]; //tamanho deste vetor peso vai ser igual a quantidade de atributos + um valor par limiar
        }

        private int funcao_ativacao_signal(double soma)
        {
            if (soma >= 0) return 1; 
            return -1;
        }
		

        public void treinar()
        {
            // Inserir o valor do limiar na posição limiar de cada ponto de cada amostra da lista "amostras"
            // Ex.: [[0.72, 0.82], ...] vira [[1, 0.72, 0.82], ...]
            for (int i = 0; i < this.amostras.Count; i++)
            {
                amostras[i].limiar = this.limiar;
            }

            // Gerar valores randômicos entre 0 e 1 (pesos) conforme o número de atributos
            // a lista de pesos tem tamanho da quantidade de atributos de uma amostra
            //pesos = [1.0,0.4,0.6], por exemplo

            Random gerador = new Random();
            pesos[0] = limiar; 
            pesos[1] = gerador.NextDouble(); //para o peso da entrada x
            pesos[2] = gerador.NextDouble(); //para o peso da entrada y

            int conta = 0;
            bool aprendeu;
            double soma;
            int saida_gerada;
            while (true)
            {
                aprendeu = true;

                //Para cada amostra...
                soma = 0;
                for (int i = 0; i < amostras.Count; i++) {
                    //Inicializar potencial de ativação
                    soma = soma + (amostras[i].limiar * pesos[0]) + (amostras[i].x * pesos[1]) + (amostras[i].y * pesos[2]);


                    //Obter a saída da rede considerando a função sinal
                    saida_gerada = funcao_ativacao_signal(soma);

                    //Verificar se a saída da rede é diferente da saída desejada
                    //se sim, calibrar ou treinar ou ajustar ou fazer aprender
                    if (saida_gerada != this.saidas[i])
                    {
                        aprendeu = false;
                        double erro = this.saidas[i] - saida_gerada;
                        //Fazer o ajuste dos pesos para cada elemento da amostra ou SEJA UM CALIBRAGEM DOS PESOS
                        this.pesos[0] = this.pesos[0] + this.taxa_aprendizado * erro * this.amostras[i].limiar;
                        this.pesos[1] = this.pesos[1] + this.taxa_aprendizado * erro * this.amostras[i].x;
                        this.pesos[2] = this.pesos[2] + this.taxa_aprendizado * erro * this.amostras[i].y;
                    }
                }
                //Atualizar contador de gerações
                conta++;

                if (aprendeu || conta > this.geracoes)
                {
                    Console.Write("Geracoes de treinamento: " + conta);
                    break;
                }
            }
        }

        //testes para "novas" amostras
        public void testar(Ponto amostra)
        {
            //Inserir o valor do limiar na posição "0" para cada amostra da lista "amostras"
            amostra.limiar = this.limiar;

            //Inicializar potencial de ativação
            double soma = 0;
            //# Para cada atributo...
            soma = soma + (amostra.limiar * pesos[0]) + (amostra.x * pesos[1]) + (amostra.y * pesos[2]);

            // Obter a saída da rede considerando g a função funcao_ativacao_signal
            double saida_gerada = this.funcao_ativacao_signal(soma);

            if (saida_gerada == 1) {
                Console.WriteLine("Classe: " + saida_gerada + " ou Time Azul");
            } else
            {
                Console.WriteLine("Classe: " + saida_gerada + " ou Time Vermelho");
            }

        }
    }    
}
