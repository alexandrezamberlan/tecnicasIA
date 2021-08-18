using System;
using System.Collections.Generic;
using System.Collections;

namespace ia
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Informe a dimensão do puzzle: ");
            int dimensao = Int32.Parse(Console.ReadLine());

            Console.Write("Quantas vezes quer repetir: ");
            int repeticoes = Int32.Parse(Console.ReadLine());

            int[,] matriz = new int[dimensao,dimensao];

            List<int> vetor = new List<int>();
            

            List<String> visitados = new List<String>();
            do
            {
                for (int i = 0; i < dimensao * dimensao; i++)
                {
                    vetor.Add(i);
                }

                
                    
                //falta embaralhar o vetor.....


                int numeros = 0;
                for (int i = 0; i < dimensao; i++)
                {
                    for (int j = 0; j < dimensao; j++)
                    {
                        matriz[i,j] = (int)vetor[numeros];
                        numeros++;
                    }
                }

                String estado = new String("");
                for (int i = 0; i < dimensao; i++)
                {
                    for (int j = 0; j < dimensao; j++)
                    {
                        Console.Write(matriz[i,j] + "\t");
                        estado = estado + matriz[i,j];
                    }
                    Console.WriteLine();
                }

                if (visitados.Contains(estado))
                {
                    Console.WriteLine("Estado já visitado: " + estado);
                }
                else
                {
                    Console.WriteLine("Estado produzido e não visitado: " + estado);
                    visitados.Add(estado);
                }
                repeticoes--;
            } while (repeticoes > 0);
        }
    }
}
