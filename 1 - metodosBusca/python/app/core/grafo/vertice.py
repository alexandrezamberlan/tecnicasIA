from typing import List, Dict, Set
from .aresta import Aresta

class Vertice:
    def __init__(self, id: int):
        self.id = id
        self.arestas: Set[Aresta] = set()

    def add_aresta(self, a: 'Aresta'):
        self.arestas.add(a)

    def get_id(self) -> int:
        return self.id

    def get_adjacentes(self) -> List['Vertice']:
        adj = []
        for a in self.arestas:
            if a.vi == self:
                adj.append(a.vj)
            else:
                adj.append(a.vi)
        return adj

    def get_custo_adjacentes(self) -> Dict['Vertice', int]:
        adj = {}
        for a in self.arestas:
            ac = a  # Assuming all edges are valued edges
            if a.vi == self:
                adj[a.vj] = ac.get_custo()
            else:
                adj[a.vi] = ac.get_custo()
        return adj

    def get_arestas(self) -> Set[Aresta]:
        return self.arestas

    def __str__(self) -> str:
        return str(self.id)