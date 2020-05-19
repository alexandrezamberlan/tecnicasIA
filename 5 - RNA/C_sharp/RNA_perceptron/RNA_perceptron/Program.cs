using System;

namespace RNA_perceptron
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Perceptron para reconhecer portas lógicas AND");

            Perceptron_AND perceptron = new Perceptron_AND();

            perceptron.treinar(); //criamos o modelo de classificação

            Console.WriteLine("Para aprender o algoritmo treinou " + perceptron.contaGeracoes + " geracoes! \n ");

            perceptron.testar(); //executa a aplicação do modelo de classificação
        }
    }
}
