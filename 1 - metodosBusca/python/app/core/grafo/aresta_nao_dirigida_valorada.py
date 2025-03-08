from .aresta_valorada import ArestaValorada

class ArestaNaoDirigidaValorada(ArestaValorada):
    def __init__(self, vi, vj, custo):
        super().__init__(vi, vj, custo)
        vi.add_aresta(self)
        vj.add_aresta(self)