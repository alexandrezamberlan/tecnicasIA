using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cSharp
{
    /**
    * Contem varias informacoes de status sobre a busca
    * 
    */
    public class Status
    {
        int nroVisitados = 0;
        int profundidadeMax = 0; // a max prox. que a busca foi
        int tamAbertos = 0;
        DateTime tempoInicio;
        MostraStatusConsole ms;
        bool resolveu = false;

        void setMostra(MostraStatusConsole ms)
        {
            this.ms = ms;
        }

        void inicia()
        {
            nroVisitados = 0;
            profundidadeMax = 0;
            tempoInicio = new DateTime();
        }

        void termina(bool resolveu)
        {
            this.resolveu = true;
            if (ms != null)
            {
                ms.para();
            }
        }

        public bool resolvido()
        {
            return resolveu;
        }

        public long getTempoDecorrido()
        {
            DateTime agora = DateTime.Now;
            return (agora - tempoInicio).Ticks;
        }

        public int getVisitados()
        {
            return nroVisitados;
        }

        public int getProfundidade()
        {
            return profundidadeMax;
        }

        /** o algoritmo pegou n para explorar de um total de s */
        public void explorando(Nodo n, int s)
        {
            tamAbertos = s;
            nroVisitados++;

            if (n.getProfundidade() > profundidadeMax)
            {
                profundidadeMax = n.getProfundidade();
            }
        }

    }
}
