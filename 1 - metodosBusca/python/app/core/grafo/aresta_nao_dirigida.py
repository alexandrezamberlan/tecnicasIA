from .aresta import Aresta

class ArestaNaoDirigida(Aresta):
    def __init__(self, vi, vj):
        super().__init__(vi, vj)
        vi.add_aresta(self)
        vj.add_aresta(self)