using System;
namespace RNA_perceptron
{
    public class Perceptron_AND
    {
        // pesos sinápticos [0] entrada 1, [1] entrada 2, [3] saída esperada
        public double[] pesos = new double[3];//vetor que vai receber treinamento

        // variável responsável pelo somatório(rede).
        public double SOMATORIO_REDE = 0;

        // constante responsável pelo número máximo de gerações
        public int geracoesMax = 100000;

        // variável responsável pela contagem das gerações durante o treinamento
        public int contaGeracoes = 0;

        // declara o vetor da matriz de aprendizado
        public int[,] matrizAprendizado = new int[4, 3];

        // metodo de inicialização inicia o vetor da matriz de aprendizado
        public Perceptron_AND()
        {
            //tabela verdade
            //0 e 0 = 0 - Primeiro Valor
            //1 e 0 = 0 - Segundo Valor
            //0 e 1 = 0 - Terceiro Valor
            //1 e 1 = 1 - Quarto Valor

            Console.WriteLine("Usando length para toda a matriz: " + matrizAprendizado.Length);
            Console.WriteLine("Usando GetLength(0) para a quantidade de linhas: " + matrizAprendizado.GetLength(0));

            // Primeira linha da tabela verdade
            this.matrizAprendizado[0, 0] = 0; // entrada 1
            this.matrizAprendizado[0, 1] = 0; // entrada 2
            this.matrizAprendizado[0, 2] = 0; // valor esperado

            // Segunda linha
            this.matrizAprendizado[1, 0] = 0; // entrada 1
            this.matrizAprendizado[1, 1] = 1; // entrada 2
            this.matrizAprendizado[1, 2] = 0; // valor esperado

            // terceira linha
            this.matrizAprendizado[2, 0] = 1; // entrada 1
            this.matrizAprendizado[2, 1] = 0; // entrada 2
            this.matrizAprendizado[2, 2] = 0; // valor esperado

            // quarta linha
            this.matrizAprendizado[3, 0] = 1; // entrada 1
            this.matrizAprendizado[3, 1] = 1; // entrada 2
            this.matrizAprendizado[3, 2] = 1; // valor esperado

            // inicialização dos pesos sinápticos

            // Peso sináptico para primeira entrada.
            pesos[0] = 0;
            // Peso sináptico para segunda entrada.
            pesos[1] = 0;
            // Peso sináptico para o BIAS ou saída esperada
            pesos[2] = 0;
        }

        // Método responsável pelo somatório e a função de ativação.
        int executar(int entrada1, int entrada2)
        {

            // Somatório (SOMATORIO_REDE)
            SOMATORIO_REDE = (entrada1 * pesos[0]) + (entrada2 * pesos[1]) + ((-1) * pesos[2]);

            // Função de Ativação tipo STEP
            if (SOMATORIO_REDE >= 0)
            {
                return 1;
            }
            return 0;
        }

        // Método para treinamento da rede
        public void treinar()
        {
            // variavel utilizada responsável pelo controle do treinamento recebe falso
            bool aprendeu; //aprendeu
            // varável responsável para receber o valor da saída (y)
            int saida;
            int qtdLinhas = matrizAprendizado.GetLength(0);
            do
            {
                aprendeu = true;
                // laço usado para fazer todas as entradas
                for (int i = 0; i < qtdLinhas; i++)
                {
                    // A saída recebe o resultado da rede que no caso é 1 ou 0
                    saida = executar(matrizAprendizado[i, 0], matrizAprendizado[i, 1]);

                    if (saida != matrizAprendizado[i, 2])
                    {
                        // Caso a saída seja diferente do valor esperado

                        // os pesos sinápticos serão corrigidos, ou seja, calibrados
                        corrigirPeso(i, saida); //retreinar....
                        // a variavél responsável pelo controlede treinamento recebe falso
                        aprendeu = false; //nao aprendeu
                    }
                }
                // acrescenta uma época
                this.contaGeracoes++;

                // teste se houve algum erro duranteo treinamento e o número de geracoes
                //é menor qe o definido
            } while (!aprendeu && this.contaGeracoes < this.geracoesMax);
        }    // fim do método para treinamento

        // Método para a correção de pesos, conhecido como HEURÍSTICA
        void corrigirPeso(int i, int saida)
        {
            //esta parte é realmente o treinamento ou a calibragem
            pesos[0] = pesos[0] + (1 * (matrizAprendizado[i, 2] - saida) * matrizAprendizado[i, 0]);
            pesos[1] = pesos[1] + (1 * (matrizAprendizado[i, 2] - saida) * matrizAprendizado[i, 1]);
            pesos[2] = pesos[2] + (1 * (matrizAprendizado[i, 2] - saida) * (-1));

        }

        public void testar()
        { //colocar em prática o modelo rna treinado para reconhecer

            Console.WriteLine(" Teste 1 para 0 and 0 " + executar(0, 0));

            Console.WriteLine(" Teste 2 para 0 and 1 " + executar(0, 1));

            Console.WriteLine(" Teste 3 para 1 and 0 " + executar(1, 0));

            Console.WriteLine(" Teste 4 para 1 and 1 " + executar(1, 1));
        }
    }
}

