using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sudoku
{
    class Sudoku
    {
        int[,] matriz;
        int dimensao;
        int totalChamadasNaoRecursivas;

        public int[,] Matriz { get => matriz; set => matriz = value; }
        public int Dimensao { get => dimensao; set => dimensao = value; }
        public int TotalChamadasNaoRecursivas { get => totalChamadasNaoRecursivas; set => totalChamadasNaoRecursivas = value; }

        public Sudoku()
        {
            this.TotalChamadasNaoRecursivas = 0;
        }

        public bool popularDoArquivo(string nomeDoArquivoSudoku)
        {            
            // To read the entire file at once
            if (File.Exists(nomeDoArquivoSudoku)) //o método de classe Exists() é invocado pela classe File
            {
                //le o conteúdo do arquivo linha por linha e armazena em uma lista/vetor de string
                string[] linhas = File.ReadAllLines(nomeDoArquivoSudoku);

                this.dimensao = linhas.Length;
                this.matriz = new int[dimensao,dimensao];
                this.inicializarMatriz();

                for (int i = 0; i < linhas.Length; i++)
                {
                    for (int j = 0; j < linhas.Length; j++)
                    {
                        this.Matriz[i, j] = Int32.Parse( linhas[i].Substring(j,1));
                    }
                }
                return true;
            } else
            {
                return false;
            }
        }

        private void inicializarMatriz()
        {
            for (int i = 0; i < this.Dimensao; i++)
            {
                for (int j = 0; j < this.Dimensao; j++)
                {
                    this.Matriz[i, j] = 0;
                }
            }
        }


        public void exibirSudoku(string frase)
        {
            Console.WriteLine(frase);
            for (int i = 0; i < this.Dimensao; i++)
            {
                if (i % 3 == 0 && i != 0)
                {
                    Console.WriteLine("-------------------");
                }
                for (int j = 0; j < Dimensao; j++)
                {
                    if (j % 3 == 0 && j != 0)
                    {
                        Console.Write("|");
                    }
                    Console.Write(this.Matriz[i,j] + " ");
                }
                Console.WriteLine();
            }
        }

        private Boolean numeroEstaNaLinha(int numero, int linha)
        {
            for (int i = 0; i < this.Dimensao; i++)
            {
                if (this.Matriz[linha,i] == numero)
                {
                    return true;
                }
            }
            return false;
        }

        private Boolean numeroEstaNaColuna(int numero, int coluna)
        {
            for (int i = 0; i < this.Dimensao; i++)
            {
                if (this.Matriz[i,coluna] == numero)
                {
                    return true;
                }
            }
            return false;
        }

        private Boolean numeroEstaNoBox(int numero, int linha, int coluna)
        {
            int linhaBoxLocal = linha - linha % 3;
            int colunaBoxLocal = coluna - coluna % 3;

            for (int i = linhaBoxLocal; i < linhaBoxLocal + 3; i++)
            {
                for (int j = colunaBoxLocal; j < colunaBoxLocal + 3; j++)
                {
                    if (this.Matriz[i,j] == numero)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        private Boolean numeroEstaNoLugarCerto(int numero, int linha, int coluna)
        {
            return !numeroEstaNaLinha(numero, linha) &&
                   !numeroEstaNaColuna(numero, coluna) &&
                   !numeroEstaNoBox(numero, linha, coluna);
        }

        public Boolean resolveSudoku(int qtdChamadas)
        {
            for (int linha = 0; linha < this.Dimensao; linha++)
            {
                for (int coluna = 0; coluna < this.Dimensao; coluna++)
                {
                    if (this.Matriz[linha,coluna] == 0)
                    {
                        for (int tentandoNumero = 0; tentandoNumero <= this.Dimensao; tentandoNumero++)
                        {
                            if (numeroEstaNoLugarCerto(tentandoNumero, linha, coluna))
                            {
                                this.Matriz[linha, coluna] = tentandoNumero;

                                //this.exibirSudoku("Chamada " + qtdChamadas);
                                this.TotalChamadasNaoRecursivas++;
                                //entra agora a estratégia de força bruta - profundidade com uso de pilha recursiva
                                if (resolveSudoku(qtdChamadas + 1)) //chamada recursiva, que representa a pilha do profundidade
                                {
                                    return true;
                                }
                                else
                                {
                                    this.Matriz[linha, coluna] = 0;
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
