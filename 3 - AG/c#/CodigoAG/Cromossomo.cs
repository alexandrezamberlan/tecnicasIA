using System;
using System.Collections.Generic;
using System.Linq;


class Cromossomo
{
    public String valor;
    public int aptidao;
    public int aptidaoPorcentagem;

    public Cromossomo(String valor, String palavraFinal)
    {
        this.valor = valor;
        this.aptidao = calcularAptidao(palavraFinal); //é a heurística
        this.aptidaoPorcentagem = 0; //usado na seleção, no método da roleta
    }

    public int calcularAptidao(String palavraFinal) //isso é a heurística dinâmcia do AG
    {
        int nota = 0;
        for (int i = 0; i < palavraFinal.Count(); i++)
        {
            if (this.valor.Contains(palavraFinal.ElementAt(i) + ""))
            {
                nota += 5;
            }
            if (this.valor.ElementAt(i) == palavraFinal.ElementAt(i))
            {
                nota += 50;
            }
        }
        return nota;
    }

    public bool Equals(Cromossomo o)
    {
        if (o == null) 
            return false;
        return (this.valor.Equals(o.valor));
    }
}
