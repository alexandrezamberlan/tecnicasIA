from collections import deque
from .busca import Busca
from .nodo import Nodo

class BuscaBidirecional(Busca):
    def __init__(self, mostra_status_console=None):
        super().__init__(mostra_status_console)

    def busca_with_meta(self, inicial, meta):
        self.status.inicia()
        self.usar_fechado = False  # tem que usar poda so por ascendencia! nao pode usar fechados

        # TODO: ter duas tabelas de fechados, uma para cada lado!
        
        # TODO: usar tree para ter ordem (g) e ser rapido de achar
        abertos_cima = deque([Nodo(inicial, None)])
        nodo_meta = Nodo(meta, None)
        abertos_baixo = deque([nodo_meta])
        
        while not self.parar and abertos_cima and abertos_baixo:
            
            # incrementa em cima
            n = abertos_cima.popleft()
            self.status.explorando(n, len(abertos_cima) + len(abertos_baixo))
            # ve se tem n na borda da árvore de baixo
            if n in abertos_baixo:
                nb = abertos_baixo[abertos_baixo.index(n)]
                nb.inverte_paternidade()
                nb.pai = n.pai
                nodo_meta.set_profundidade()
                self.status.termina(True)
                return nodo_meta
            abertos_cima.extend(self.sucessores(n))
            
            # incrementa para baixo
            n = abertos_baixo.popleft()
            self.status.explorando(n, len(abertos_cima) + len(abertos_baixo))
            # ve se tem n na borda da árvore de cima
            if n in abertos_cima:
                nc = abertos_cima[abertos_cima.index(n)]
                n.inverte_paternidade()
                n.pai = nc.pai
                nodo_meta.set_profundidade()
                self.status.termina(True)
                return nodo_meta
            abertos_baixo.extend(self.antecessores(n))
        
        self.status.termina(False)
        return None

    def busca(self, inicial):
        raise Exception("Esta classe não implementa a busca com um único parâmetro")

    def __str__(self):
        return "BBD - busca bi-direcional"