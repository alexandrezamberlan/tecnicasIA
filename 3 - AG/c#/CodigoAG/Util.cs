using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Util
{
    public static string letras = " abcdefghijklmnopqrstuvwxyz";
    public static int tamanho = letras.Length;

    public static string gerarPalavras(int quantidadeCaracteres)
    {
        StringBuilder palavra = new StringBuilder();
        Random gerador = new Random();
        for (int i = 0; i < quantidadeCaracteres; i++)
        {
            palavra.Append(letras.ElementAt(gerador.Next(letras.Count())));
        }
        return palavra.ToString();
    }

    public static bool contem(List<Cromossomo> populacao, Cromossomo selecionado) 
    {
        for (int i = 0; i < populacao.Count; i++)
        {
            if (populacao[i].valor.Equals(selecionado.valor))
            {
                return true;
            }
        }
        return false;
    }

    public static void adicionaTodos(List<Cromossomo> destino, List<Cromossomo> origem) 
    {
        for (int i = 0; i < origem.Count ; i++)
        {
            destino.Add(origem[i]);
        }
    }
}
