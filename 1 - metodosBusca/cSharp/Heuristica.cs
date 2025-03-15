using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cSharp
{
    /**
    * Interface para estados que implementam a funcao h()
    *
    */
    public interface Heuristica
    {
        /**
         * estimativa de custo
         */
        public int h();

    }

    // custo real -> g(n)
    // custo heurístico -> g(h)
}
