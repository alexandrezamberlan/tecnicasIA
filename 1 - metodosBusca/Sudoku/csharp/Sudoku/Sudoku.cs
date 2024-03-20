using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sudoku
{
    internal class Sudoku
    {
        public Sudoku(String nomeArquivo) {
            this.TotalChamadasRecursivas = 0;
            //trazer os numeros do arquivo para dentro da matriz
            if (File.Exists(nomeArquivo))
            {
                //le o conteúdo do arquivo linha por linha e armazena em uma lista/vetor de string
                string[] linhas = File.ReadAllLines(nomeArquivo);
                
                //criar a matriz
                this.Dimensao= linhas.Length;
                this.Matriz = new int[Dimensao, Dimensao];
                                
                string[] numeros;
                for (int i = 0; i < linhas.Length; i++)
                {
                    numeros = linhas[i].Split(" "); //método de instância que divide uma string em um vetor por algum critério
                    for (int j = 0; j < linhas.Length; j++)
                    {
                        this.Matriz[i,j] = int.Parse(numeros[j]);
                    }
                }
            }
            else
            {
                Console.WriteLine("Arquivo não localizado!");
            }            
        }

        public int[,] Matriz { get; set; }
        public int Dimensao { get; set; }
        public int TotalChamadasRecursivas { get; set; }

        public void exibir()
        {
            for (int i = 0; i < this.Dimensao; i++)
            {
                if (i % 3 == 0 && i != 0)
                {
                    Console.WriteLine("-------------------");
                }
                for (int j = 0; j < this.Dimensao; j++)
                {
                    if (j > 0 && j % 3 == 0)
                    {
                        Console.Write("|");
                    }
                    Console.Write(this.Matriz[i, j] + " ");
                }
                Console.WriteLine();               
            }
        }

        private Boolean numeroNaLinha(int numero, int linha)
        {
            for (int coluna = 0; coluna < this.Dimensao; coluna++)
            {
                if (numero == this.Matriz[linha, coluna])
                {
                    return true;
                }
            }
            return false;
        }

        private Boolean numeroNaColuna(int numero, int coluna)
        {
            for (int linha = 0; linha < this.Dimensao; linha++)
            {
                if (numero == this.Matriz[linha, coluna])
                {
                    return true;
                }
            }
            return false;
        }

        private Boolean numeroNoBox(int numero, int linha, int coluna)
        {
            int linhaInicialBox = linha - linha % 3; ;
            int colunaInicialBox = coluna - coluna % (this.Dimensao / 3);

            for (int i = linhaInicialBox; i < linhaInicialBox + 3; i++)
            {
                for (int j = colunaInicialBox; j < colunaInicialBox + (this.Dimensao / 3); j++)
                {
                    if (numero == this.Matriz[i, j])
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        private Boolean numeroNoLugarCerto(int numero, int linha, int coluna)
        {
            return !numeroNaLinha(numero, linha) &&
                   !numeroNaColuna(numero, coluna) &&
                   !numeroNoBox(numero, linha, coluna);
        }

        public Boolean resolver()
        {
            for (int i = 0; i < this.Dimensao; i++)
            {
                for (int j = 0; j < this.Dimensao; j++)
                {
                    if (this.Matriz[i,j] == 0)
                    {
                        for (int numero = 1; numero <= this.Dimensao; numero++)
                        {
                            if (numeroNoLugarCerto(numero, i, j))
                            {
                                this.Matriz[i, j] = numero;
                                if (this.resolver())
                                {
                                    this.exibir();
                                    return true;
                                } else
                                {
                                    this.Matriz[i, j] = 0;
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        public Boolean resolver(int qtdChamadas)
        {
            for (int i = 0; i < this.Dimensao; i++)
            {
                for (int j = 0; j < this.Dimensao; j++)
                {
                    if (this.Matriz[i, j] == 0)
                    {
                        for (int numero = 1; numero <= this.Dimensao; numero++)
                        {
                            if (numeroNoLugarCerto(numero, i, j))
                            {
                                this.Matriz[i, j] = numero;
                                this.TotalChamadasRecursivas++;
                                if (this.resolver(qtdChamadas + 1))
                                {
                                  
                                    return true;
                                }
                                else
                                {
                                    this.Matriz[i, j] = 0;
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
