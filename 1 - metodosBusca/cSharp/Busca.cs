using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace cSharp
{
    abstract class Busca 
    {
        protected bool parar = false;
        protected bool podar = true;
        protected bool usarFechado = true;

        protected Status status = new Status(); // a classe que tem o status (model)
        protected MostraStatusConsole mstatus = null; // a classe que mostra o stauts (view)

        private Map<Estado,Integer> fechados = null; // mapeia o estado para um custo g
        
        /** busca sem mostrar status */
        public Busca() 
        {
        }

        /** busca mostrando status */
        public Busca(MostraStatusConsole ms) 
        {
            setMostra(ms);
        }
        
        protected void initFechados() 
        {
            fechados = new HashMap<Estado,Integer>();
        }
        
        public Status getStatus() 
        {
            return status;
        }
        
        public Status novoStatus() 
        {
            status = new Status();
            if (mstatus != null) 
            {
                mstatus.setStatus(status);
                status.setMostra(mstatus);
            }
            return status;
        }
        
        public void setMostra(MostraStatusConsole ms) 
        {
            mstatus = ms;
            ms.setStatus(status);
            status.setMostra(ms);        
        }
        
        public String toString() 
        {
            return "Algoritmo de busca geral";
        }
        
        public abstract Nodo busca(Estado inicial) throws Exception;
        
        public void setParar(bool b) 
        {
            parar = b;
        }
        
        public void para() 
        {
            parar = true;
            status.termina(false);
        }

        public void setPodar(bool b) 
        {
            podar = b;
        }
        
        public void usarFechados(bool b) 
        {
            usarFechado = b;
        }
        
        /**
        * gera uma lista de sucessores do nodo.
        */
        public List<Nodo> sucessores(Nodo pai) 
        {
            return soNovos(pai.estado.sucessores(),pai); // lista de todos os sucessores
        }

        public List<Nodo> antecessores(Nodo pai) 
        {
            try {
                return soNovos( ((Antecessor)pai.estado).antecessores(),pai);
            } catch (Exception e) {
                Console.WriteLine("O estado "+pai.estado+" nao implementa antecessores!");
                return new LinkedList<Nodo>();
            }
        }
        
        private List<Nodo> soNovos(List<Estado> estados, Nodo pai) 
        {
            List<Nodo> sucNodo   = new LinkedList<Nodo>(); // a lista de sucessores novos
            foreach (Estado e in estados) 
            {
                Nodo filho = new Nodo( e, pai);
                if (podar) 
                {
                    if (usarFechado && fechados != null) 
                    {
                        Integer custo = fechados.get(e);
                        if (custo == null || filho.g < custo.intValue()) 
                        { // nao esta em fechados ou tem custo menor
                            sucNodo.add(filho);
                            fechados.put(e, filho.g);
                        }
                    } else if (filho.ehDescendenteNovo(pai)) 
                    { // poda os filhos que tem um ascensor igual a ele
                        sucNodo.add(filho);
                    }
                } else 
                {
                    sucNodo.add(filho);
                }
            }
            return sucNodo;
        }
    }
}
