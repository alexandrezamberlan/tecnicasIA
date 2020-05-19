using System;
using System.Collections.Generic;

namespace RNA_Perceptron_Times
{
    class Program
    {
		static void Main(string[] args)
		{
			Console.WriteLine("Exemplo de RNA Perceptron para classificação de equipes");
			List<Ponto> amostras = new List<Ponto>();

			amostras.Add(new Ponto(0.72, 0.82));
			amostras.Add(new Ponto(0.91, -0.69));
			amostras.Add(new Ponto(0.46, 0.80));
			amostras.Add(new Ponto(0.03, 0.93));
			amostras.Add(new Ponto(0.12, 0.25));
			amostras.Add(new Ponto(0.96, 0.47));
			amostras.Add(new Ponto(0.8, -0.75));
			amostras.Add(new Ponto(0.46, 0.98));
			amostras.Add(new Ponto(0.66, 0.24));
			amostras.Add(new Ponto(0.72, -0.15));
			amostras.Add(new Ponto(0.35, 0.01));
			amostras.Add(new Ponto(-0.16, 0.84));
			amostras.Add(new Ponto(-0.04, 0.68));
			amostras.Add(new Ponto(-0.11, 0.1));
			amostras.Add(new Ponto(0.31, -0.96));
			amostras.Add(new Ponto(0.0, -0.26));
			amostras.Add(new Ponto(-0.43, -0.65));
			amostras.Add(new Ponto(0.57, -0.97));
			amostras.Add(new Ponto(-0.47, -0.03));
			amostras.Add(new Ponto(-0.72, -0.64));
			amostras.Add(new Ponto(-0.57, 0.15));
			amostras.Add(new Ponto(-0.25, -0.43));
			amostras.Add(new Ponto(0.47, -0.88));
			amostras.Add(new Ponto(-0.12, -0.9));
			amostras.Add(new Ponto(-0.58, 0.62));
			amostras.Add(new Ponto(-0.48, 0.05));
			amostras.Add(new Ponto(-0.79, -0.92));
			amostras.Add(new Ponto(-0.42, -0.09));
			amostras.Add(new Ponto(-0.76, 0.65));
			amostras.Add(new Ponto(-0.77, -0.76));

			List<Int32> saidas = new List<int>();
			saidas.AddRange(new Int32[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});

			double taxa_aprendizado = 0.1;
            int geracoes = 1000;
            int limiar = 1;
            Perceptron p = new Perceptron(amostras, saidas, taxa_aprendizado, geracoes, limiar);

			p.treinar();

			Console.Write("\n\nInforme valor para x (-1 a 1): ");
			double x = Double.Parse( Console.ReadLine() );
			Console.Write("Informe valor para y (-1 a 1): ");
			double y = Double.Parse( Console.ReadLine() );

			p.testar(new Ponto(x,y));
        }
    }
}
