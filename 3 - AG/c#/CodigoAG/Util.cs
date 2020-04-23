using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Util
{
    public static string letras = " abcdefghijklmnopqrstuvwxyz";

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
}
