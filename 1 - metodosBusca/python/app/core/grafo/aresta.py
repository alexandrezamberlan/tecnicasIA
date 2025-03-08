from abc import ABC

class Aresta(ABC):
    def __init__(self, vi, vj):
        self.vi = vi
        self.vj = vj

    def __str__(self):
        return f"{self.vi} <-> {self.vj}"