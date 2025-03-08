from .aresta import Aresta

class ArestaValorada(Aresta):
    def __init__(self, vi, vj, custo):
        super().__init__(vi, vj)
        self.custo = custo

    def get_custo(self):
        return self.custo

    def __str__(self):
        return f"{self.vi} <- {self.custo} -> {self.vj}"