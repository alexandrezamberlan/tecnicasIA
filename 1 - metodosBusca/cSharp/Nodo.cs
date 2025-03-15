using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace cSharp
{
    /**
     * Representa um nodo da arvore de busca
     */
    public class Nodo : IComparable
    {

        Estado estado;  // o estado
        Nodo pai;     // o pai
        int profundidade = 0;
        int g = 0; // custo de ter gerado o nodo (todo o caminho)
        int f = -1; // f = g + h


        public Nodo(Estado e, Nodo p)
        {
            estado = e;
            pai = p;
            if (p == null)
            {
                profundidade = 0;
                g = e.custo();
            }
            else
            {
                profundidade = p.getProfundidade() + 1;
                g = e.custo() + p.g;
            }
        }

        public int getProfundidade()
        {
            return profundidade;
        }

        public Estado getEstado()
        {
            return estado;
        }

        public Nodo getPai()
        {
            return pai;
        }

        /** retorna o custo acumulado de gerar o nodo 
         *  (baseado no acumulo do custo de gerar os estados)
         */
        public int custoAcumulado()
        {
            return g;
        }

        /**
         * Custo total
         */
        public int custoTotal()
        {
            if (f == -1)
            {
                f = g + ((Heuristica)estado).h();
            }
            return f;
        }

        void invertePaternidade()
        {
            if (pai.pai != null)
            {
                pai.invertePaternidade();
            }
            pai.pai = this;
        }

        /**
         * arruma a profundidade de um nodo e de seus pais
         */
        void setProfundidade()
        {
            if (pai == null)
            {
                profundidade = 0;
            }
            else
            {
                pai.setProfundidade();
                profundidade = pai.getProfundidade() + 1;
            }
        }


        /**
         * testa se o nodo nao tem um ascensor igual a ele
         * (se um dos pais eh igual a ele)
         */
        bool ehDescendenteNovo(Nodo ascensor)
        {
            if (ascensor == null)
            {
                return true;
            }
            else
            {
                if (ascensor.estado.Equals(this.estado))
                {
                    return false;
                }
                else
                {
                    return ehDescendenteNovo(ascensor.pai);
                }
            }
        }

        /**
         * se dois nodos sao iguais
         * (por enquanto, so verifica se os estados sao iguais --
         *  usado no bi-direcional)
         */
        public override bool Equals(Object o)
        {
            try
            {
                Nodo n = (Nodo)o;
                return this.estado.Equals(n.estado);
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return false;
        }




        /** utiliza o custo (g) como elemento de ordenacao */
        public int CompareTo(Object obj)
        {
            try
            {
                Nodo outro = (Nodo)obj;
                if (g > outro.g)
                {
                    return 1; // sou maior (fica depois na fila)
                }
                else if (g == outro.g)
                {
                    return 0; // sou =
                }
                else
                {
                    return -1; // sou menor
                }
            }
            catch (Exception e1)
            {
                Console.WriteLine(e1.StackTrace);
            }
            return 0; // sou igual

        }

        /**
         *  imprime o caminho ate a raiz
         */
        public string montaCaminho()
        {
            return montaCaminho(this);
        }

        public string montaCaminho(Nodo n)
        {
            if (n != null)
            {
                return montaCaminho(n.pai) + n + "; ";
            }
            return "";
        }

        public string toString()
        {
            return estado.ToString();
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(estado, pai, profundidade, g, f);
        }
    }
}
