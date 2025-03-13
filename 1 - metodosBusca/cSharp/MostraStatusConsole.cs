using System.Collections.Generic;
using System.Linq;
using System.Text;
using System;
using System.Threading;

namespace cSharp
{
    public class MostraStatusConsole
    {
        public int nroVisitados;
        public int tamAbertos;
        public int profundidadeMax;
        private DateTime tempoInicio;

        public MostraStatusConsole()
        {
            tempoInicio = DateTime.Now;
        }

        public double GetTempoDecorrido()
        {
            return (DateTime.Now - tempoInicio).TotalMilliseconds;
        }
    }

    public class MostraStatusConsole : Thread
    {
        private Status status;
        private bool stop = false;

        // Construtor sem parâmetros
        public MostraStatusConsole()
        {
            Start(); // Inicia a thread
        }

        // Construtor com Status
        public MostraStatusConsole(Status s)
        {
            SetStatus(s);
            Start(); // Inicia a thread
        }

        public Status GetStatus()
        {
            return status;
        }

        public void SetStatus(Status s)
        {
            this.status = s;
        }

        public void Para()
        {
            if (!stop)
            {
                stop = true;
                Interrupt();
            }
        }

        public override void Run()
        {
            while (!stop)
            {
                try
                {
                    Thread.Sleep(1000);
                    if (!stop && status != null)
                    {
                        Mostra();
                    }
                }
                catch (Exception e)
                {
                    // Tratar exceções se necessário
                }
            }
            MostraFim();
        }

        protected void MostraFim()
        {
            Console.WriteLine(": Fim da busca. " + status.nroVisitados + " nodos visitados em " + status.GetTempoDecorrido() + " mili-seg.");
        }

        protected void Mostra()
        {
            Console.WriteLine("Status:");
            Console.WriteLine("\t" + status.nroVisitados + " nodos visitados, nodos em aberto=" + status.tamAbertos);
            Console.WriteLine("\tProfundidade atual=" + status.profundidadeMax);
            Console.WriteLine("\tTempo decorrido=" + status.GetTempoDecorrido());
            /* 
            Console.WriteLine("\nNúmero médio de sucessores=" + melhor.NroMedioSucessores());
            Console.WriteLine("\nMédia de profundidade=" + melhor.GetProfundidadeMedia());
            Console.WriteLine("\nProfundidade máxima=" + melhor.profundidadeMax);
            */
        }
    }

}
