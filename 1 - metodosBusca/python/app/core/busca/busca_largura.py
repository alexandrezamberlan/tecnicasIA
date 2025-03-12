from collections import deque
from .busca import Busca
from .nodo import Nodo

class BuscaLargura(Busca):
    def __init__(self, mostra_status_console=None):
        super().__init__(mostra_status_console)

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
                        
            abertos.extend(self.sucessores(n))
        
        self.status.termina(False)
        return None
    
    def __str__(self):
        return "BL - Busca em Largura"