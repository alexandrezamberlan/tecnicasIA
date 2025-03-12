from .aresta import Aresta

class ArestaDirigida(Aresta):
    def __init__(self, vi, vj):
        super().__init__(vi, vj)
        vi.add_aresta(self)