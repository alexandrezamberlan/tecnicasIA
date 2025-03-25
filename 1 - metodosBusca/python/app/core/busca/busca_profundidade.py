from collections import deque
from .busca import Busca
from .nodo import Nodo
from .status import Status

class BuscaProfundidade(Busca):
    def __init__(self, prof_max=40, mostra_status_console=None):
        super().__init__(mostra_status_console)
        self.prof_max = prof_max

    def set_prof_max(self, m):
        self.prof_max = m

    def busca(self, inicial):
        self.status.inicia()
        self.init_fechados()
        
        abertos = deque([Nodo(inicial, None)])
        
        while not self.parar and abertos:
            n = abertos.popleft()
            self.status.explorando(n, len(abertos))
            if n.estado.eh_meta():
                self.status.termina(True)
                return n
        
            if n.get_profundidade() < self.prof_max:
                abertos.extendleft(reversed(self.sucessores(n)))
                
        self.status.termina(False)
        return None

    def __str__(self):
        return "BP - Busca em Profundidade"