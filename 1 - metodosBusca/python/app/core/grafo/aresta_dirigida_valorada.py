from .aresta_valorada import ArestaValorada

class ArestaDirigidaValorada(ArestaValorada):
    def __init__(self, vi, vj, custo):
        super().__init__(vi, vj, custo)
        vi.add_aresta(self)