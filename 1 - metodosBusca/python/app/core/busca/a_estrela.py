import heapq
from .busca_heuristica import BuscaHeuristica
from .nodo import Nodo

class AEstrela(BuscaHeuristica):
    def __init__(self, mostra_status_console=None):
        super().__init__(mostra_status_console)
        self.max_f = -1  # max F
        self.max_abertos = -1  # max abertos
        self.the_best = None

    def set_max_f(self, m):
        self.max_f = m

    def set_max_abertos(self, m):
        self.max_abertos = m

    def get_the_best(self):
        return self.the_best

    def busca(self, inicial):
        self.status.inicia()
        self.init_fechados()
        
        abertos = []
        heapq.heappush(abertos, (0, Nodo(inicial, None)))
        self.the_best = abertos[0][1]  # o melhor nodo já gerado
        
        while not self.parar and abertos:
            _, melhor = heapq.heappop(abertos)
            self.status.explorando(melhor, len(abertos))
            if melhor.estado.eh_meta():
                self.status.termina(True)
                return melhor
            
            if self.max_f < 0 or melhor.f() < self.max_f:
                for suc in self.sucessores(melhor):
                    heapq.heappush(abertos, (suc.f(), suc))
            if self.max_abertos > 0 and len(abertos) > self.max_abertos:
                break
            
            # o "the best" e o código que segue só para fins de interface
            if melhor.f() < self.the_best.f():
                self.the_best = melhor
        
        self.status.termina(False)
        return None

    def __str__(self):
        return "A* - busca heurística"