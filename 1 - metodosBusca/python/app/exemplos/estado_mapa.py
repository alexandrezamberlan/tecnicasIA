from typing import List
from app.core.busca import Antecessor, BuscaLargura, Estado
from app.core.grafo import GrafoNaoDirigido


class EstadoMapa(Estado, Antecessor):
    nomes = [
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "x",
    ]
    mapa = GrafoNaoDirigido()
    meta = None

    @staticmethod
    def init_mapa():
        for i in range(16):
            EstadoMapa.mapa.cria_vertice(i)
            EstadoMapa.mapa.cria_aresta(0, 1, 3)
            EstadoMapa.mapa.cria_aresta(0, 2, 6)
            EstadoMapa.mapa.cria_aresta(1, 9, 3)
            EstadoMapa.mapa.cria_aresta(1, 7, 3)
            EstadoMapa.mapa.cria_aresta(2, 13, 2)
            EstadoMapa.mapa.cria_aresta(2, 14, 2)
            EstadoMapa.mapa.cria_aresta(2, 3, 3)
            EstadoMapa.mapa.cria_aresta(2, 6, 2)
            EstadoMapa.mapa.cria_aresta(3, 4, 1)
            EstadoMapa.mapa.cria_aresta(3, 5, 1)
            EstadoMapa.mapa.cria_aresta(4, 5, 1)
            EstadoMapa.mapa.cria_aresta(4, 8, 2)
            EstadoMapa.mapa.cria_aresta(4, 11, 14)
            EstadoMapa.mapa.cria_aresta(5, 6, 1)
            EstadoMapa.mapa.cria_aresta(6, 7, 2)
            EstadoMapa.mapa.cria_aresta(7, 8, 2)
            EstadoMapa.mapa.cria_aresta(7, 9, 4)
            EstadoMapa.mapa.cria_aresta(9, 10, 1)
            EstadoMapa.mapa.cria_aresta(9, 12, 3)
            EstadoMapa.mapa.cria_aresta(11, 12, 2)
            EstadoMapa.mapa.cria_aresta(11, 15, 1)

    def __init__(self, cidade: int, custo: int = 1):
        self.cidade = cidade
        self.custo = custo

    def get_descricao(self) -> str:
        ds = "Encontra rotas no mapa abaixo (o custo dos caminhos esta entre parenteses):\n"
        for i in range(len(self.nomes)):
            ds += f"  saindo de {self.nomes[i]} para "
            custos = self.mapa.get_vertice(i).get_custo_adjacentes()
            for v, custo in custos.items():
                ds += f"{self.nomes[v.get_id()]}({custo}), "
            ds += "\n"
        return ds

    def __eq__(self, other) -> bool:
        if isinstance(other, EstadoMapa):
            return self.cidade == other.cidade
        return False

    def __hash__(self) -> int:
        return hash(self.cidade)

    def eh_meta(self) -> bool:
        return self.cidade == self.meta

    def custo(self) -> int:
        return self.custo

    def sucessores(self) -> List[Estado]:
        suc = []
        custos = self.mapa.get_vertice(self.cidade).get_custo_adjacentes()
        for v, custo in custos.items():
            suc.append(EstadoMapa(v.get_id(), custo))
        return suc

    def antecessores(self) -> List[Estado]:
        return self.sucessores()

    def __str__(self) -> str:
        return f"{self.nomes[self.cidade]} "

    @staticmethod
    def get_meta() -> int:
        return EstadoMapa.meta

    @staticmethod
    def set_meta(cidade: int):
        EstadoMapa.meta = cidade

    @staticmethod
    def main():
        EstadoMapa.init_mapa()
        inicial = EstadoMapa(8)
        EstadoMapa.set_meta(15)
        print(inicial.get_descricao())
        print(f"estado inicial= {inicial}")

        s = BuscaLargura().busca(inicial)
        if s is not None:
            print(f"solucao = {s.monta_caminho()}")
            print(f"\toperacoes = {s.get_profundidade()}")
            print(f"\tcusto = {s.g()}")


if __name__ == "__main__":
    EstadoMapa.init_mapa()
    EstadoMapa.set_meta(15)
    inicial = EstadoMapa(8)
    print(inicial.get_descricao())
    print(f"estado inicial= {inicial}")
    s = BuscaLargura().busca(inicial)
    if s is not None:
        print(f"solucao = {s.monta_caminho()}")
        print(f"\toperacoes = {s.get_profundidade()}")
        print(f"\tcusto = {s.g()}")
