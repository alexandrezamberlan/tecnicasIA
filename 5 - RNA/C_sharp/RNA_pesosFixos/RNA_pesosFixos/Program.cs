using System;
using System.Collections.Generic;

namespace RNA_pesosFixos
{
    class Program
    {
        static void gerarMatrizPesosFixos(int[,] pesos, List<Professor> lista)
        {
            int TAMPROFESSOR = lista[0].Vetor_nome.Length;
            int TAMAREA = lista[0].Vetor_area.Length;

            //criando a matriz de pesos a partir das entradas (professor * area) - TREINAMENTO
            for (int i = 0; i < TAMPROFESSOR; i++)
            {
                for (int j = 0; j < TAMAREA; j++)
                {
                    pesos[i, j] = lista[0].Vetor_nome[i] * lista[0].Vetor_area[j]
                            + lista[1].Vetor_nome[i] * lista[1].Vetor_area[j]
                            + lista[2].Vetor_nome[i] * lista[2].Vetor_area[j];
                }
            }
        }

        static void exibirMatrizPesosFixos(int[,] pesos , List<Professor> lista)
        {
            int TAMPROFESSOR = lista[0].Vetor_nome.Length;
            int TAMAREA = lista[0].Vetor_area.Length;
            for (int j = 0; j < TAMAREA; j++)
            {
                for (int i = 0; i < TAMPROFESSOR; i++)
                {
                    Console.Write(pesos[i,j] + "\t");
                }
                Console.WriteLine();
            }
        }

        static void aplicarFuncaoSign(int[] saida)
        {
            for (int i = 0; i < saida.Length; i++)
            {
                if (saida[i] < 0)
                {
                    saida[i] = -1;
                }
                else
                {
                    saida[i] = 1;
                }
            }
        }

        static void exibirSaida(int[] saida)
        {
            for (int i = 0; i < saida.Length; i++)
            {
                Console.Write(saida[i] + "\t");
            }
            Console.WriteLine();
        }

        static Professor descobrirObjetoProfessor(string area, List<Professor> lista)
        {
            for (int i = 0; i < lista.Count; i++)
            {
                if (area.Equals(lista[i].Area)) return lista[i];
            }
            return null;
        }

        static void Main(string[] args)
        {
            Console.WriteLine("Exemplo de uma RNA com treinamento por pesos fixos");

            /*
             * Definição das entradas e saidas
             */

            int[] alexandre = { 1, -1, 1, -1, 1 }; //assumir que o professor é entrada
            int[] ia = {1, -1, 1, -1};           //assumir que a área do professor é saída
            Professor obj_alexandre = new Professor("alexandre", "ia", alexandre, ia);

            int[] reiner = { 1, 1, 1, -1, -1 };
            int[] programacao = {1, 1, -1, -1};
            Professor obj_reiner = new Professor("reiner", "programacao", reiner, programacao);

            int[] cassio = { 1, 1, -1, -1, 1 };
            int[] design = {1, -1, -1, 1};
            Professor obj_cassio = new Professor("cassio", "design", cassio, design);

            List<Professor> listProfessores = new List<Professor>();
            listProfessores.Add(obj_alexandre);
            listProfessores.Add(obj_reiner);
            listProfessores.Add(obj_cassio);

            int TAMPROFESSOR = listProfessores[0].Vetor_nome.Length;
            int TAMAREA = listProfessores[0].Vetor_area.Length;

            int[,] pesos = new int[TAMPROFESSOR, TAMAREA]; //pesos fixos calculados pelos pares entrada-saída

            //geração da matriz de pesos fixos - treinamento único para o par entrada-saída conhecidos
            gerarMatrizPesosFixos(pesos,listProfessores);

            exibirMatrizPesosFixos(pesos,listProfessores);

            int[] saidaArea = new int[4];            

            //entrar com 
            // int[] alexandre = {1, -1, 1, -1, 1};       
            //int[] reiner = {1, 1, 1, -1, -1};
            //int[] cassio = {1, 1, -1, -1, 1};
            for (int j = 0; j < TAMAREA; j++)
            {
                saidaArea[j] = 0;
                for (int i = 0; i < TAMPROFESSOR; i++)
                {
                    saidaArea[j] = pesos[i,j] * listProfessores[1].Vetor_nome[i] + saidaArea[j];
                }
            }


            aplicarFuncaoSign(saidaArea);

            Console.WriteLine("Resposta após aplicação da função ativação na saída");
            exibirSaida(saidaArea);

            //==================
            Console.WriteLine("Testando buscar dados na lista professores pela área");
            Professor obj = descobrirObjetoProfessor("programacao",listProfessores);
            Console.WriteLine(obj.Nome);
            Console.WriteLine(obj.Area);
            for (int i = 0; i < obj.Vetor_nome.Length; i++)
            {
                Console.Write(obj.Vetor_nome[i] + "\t");
            }
            Console.WriteLine();
            for (int i = 0; i < obj.Vetor_area.Length; i++)
            {
                Console.Write(obj.Vetor_area[i] + "\t");
            }
            Console.WriteLine();

        }
    }
}


/*
 * listProfessores = ["alexandre","ia",{1, -1, 1, -1, 1},{1, -1, 1, -1}] 
 *                   ["reiner","programacao",{1, 1, 1, -1, -1},{1, 1, -1, -1}]
 *                   ["cassio","design",{1, 1, -1, -1, 1},{1, -1, -1, 1}]
 *
 *
 *                  
 */
