from .grafo import Grafo
from .aresta_dirigida import ArestaDirigida
from .aresta_dirigida_valorada import ArestaDirigidaValorada

class GrafoDirigido(Grafo):
    def cria_aresta(self, i, j):
        vi = self.vertices.get(i)
        vj = self.vertices.get(j)
        ArestaDirigida(vi, vj)

    def cria_aresta(self, i, j, custo):
        vi = self.vertices.get(i)
        vj = self.vertices.get(j)
        ArestaDirigidaValorada(vi, vj, custo)