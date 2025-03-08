from .grafo import Grafo
from .aresta_nao_dirigida import ArestaNaoDirigida
from .aresta_nao_dirigida_valorada import ArestaNaoDirigidaValorada

class GrafoNaoDirigido(Grafo):
    def cria_aresta(self, i, j):
        vi = self.get_vertice(i)
        vj = self.get_vertice(j)
        ArestaNaoDirigida(vi, vj)

    def cria_aresta(self, i, j, custo):
        vi = self.get_vertice(i)
        vj = self.get_vertice(j)
        ArestaNaoDirigidaValorada(vi, vj, custo)