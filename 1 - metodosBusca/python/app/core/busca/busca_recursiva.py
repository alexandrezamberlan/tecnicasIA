from .busca_profundidade import BuscaProfundidade
from .nodo import Nodo
class BuscaRecursiva(BuscaProfundidade):
    def __init__(self, mostra_status_console=None):
        super().__init__(mostra_status_console)

    def busca(self, inicial):
        self.status.inicia()
        self.init_fechados()
        n = self.busca_r(Nodo(inicial, None))
        self.status.termina(n is not None)
        return n
    
    def busca_r(self, corrente):
        if corrente is None:
            return None
        self.status.explorando(corrente, 0)
        if corrente.estado.eh_meta():
            return corrente
        if corrente.get_profundidade() > self.prof_max or self.parar:
            return None
        for s in self.sucessores(corrente):
            n = self.busca_r(s)
            if n is not None:
                return n
        return None
    
    def __str__(self):
        return f"BP - Busca em Profundidade (m={self.prof_max}) - versão recursiva"