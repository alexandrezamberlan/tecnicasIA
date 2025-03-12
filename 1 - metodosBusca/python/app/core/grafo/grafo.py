from abc import ABC, abstractmethod
from .vertice import Vertice

class Grafo(ABC):
    def __init__(self):
        self.vertices = {}

    def add_vertice(self, v):
        self.vertices[v.get_id()] = v

    def cria_vertice(self, id):
        self.add_vertice(Vertice(id))

    def get_vertice(self, id):
        return self.vertices.get(id)

    @abstractmethod
    def cria_aresta(self, i, j):
        pass

    @abstractmethod
    def cria_aresta(self, i, j, custo):
        pass

    def __str__(self):
        out = []
        for v in self.vertices.values():
            out.append(f"{v.get_id()}: {v.get_adjacentes()}")
        return "\n".join(out)