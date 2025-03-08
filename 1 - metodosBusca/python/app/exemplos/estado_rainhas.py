import random
from typing import List
from app.core.busca import Aleatorio, Estado, Heuristica, MostraStatusConsole, SubidaMontanha

class EstadoRainhas(Estado, Heuristica, Aleatorio):
    tam = 8

    @staticmethod
    def set_tamanho(t: int):
        EstadoRainhas.tam = t

    def __init__(self, p=None):
        self.posicoes = [[0] * 2 for _ in range(EstadoRainhas.tam)]
        if p is None:
            for r in range(EstadoRainhas.tam):
                l = random.randint(0, EstadoRainhas.tam - 1)
                c = random.randint(0, EstadoRainhas.tam - 1)
                while self.get_pos(l, c) != -1:
                    l = random.randint(0, EstadoRainhas.tam - 1)
                    c = random.randint(0, EstadoRainhas.tam - 1)
                self.set_pos_rainha(r, l, c)
        else:
            for r in range(EstadoRainhas.tam):
                self.posicoes[r][0] = p[r][0]
                self.posicoes[r][1] = p[r][1]

    def get_descricao(self) -> str:
        return (
            "Este problema consiste em posicionar 8\n"
            "rainhas do jogo de xadrez em um tabuleiro,\n"
            "sendo que nenhuma pode atacar a outra.\n"
            "(tem heuristica implementada)\n"
        )

    def gera_aleatorio(self) -> Estado:
        return EstadoRainhas()

    def set_pos_rainha(self, r: int, l: int, c: int):
        self.posicoes[r][0] = l
        self.posicoes[r][1] = c

    def get_pos(self, l: int, c: int) -> int:
        for r in range(EstadoRainhas.tam):
            if self.posicoes[r][0] == l and self.posicoes[r][1] == c:
                return r
        return -1

    def get_pos_str(self, l: int, c: int) -> str:
        r = self.get_pos(l, c)
        return "_" if r == -1 else str(r)

    def __eq__(self, other) -> bool:
        if isinstance(other, EstadoRainhas):
            for r in range(EstadoRainhas.tam):
                if self.posicoes[r][0] != other.posicoes[r][0] or self.posicoes[r][1] != other.posicoes[r][1]:
                    return False
            return True
        return False

    def __hash__(self) -> int:
        return hash(str(self))

    def eh_meta(self) -> bool:
        return self.h() == 0

    def h(self) -> int:
        ataques = 0
        for r in range(EstadoRainhas.tam):
            for c in range(EstadoRainhas.tam):
                if self.get_pos(self.posicoes[r][0], c) != -1 and self.get_pos(self.posicoes[r][0], c) != r:
                    ataques += 1
            for l in range(EstadoRainhas.tam):
                if self.get_pos(l, self.posicoes[r][1]) != -1 and self.get_pos(l, self.posicoes[r][1]) != r:
                    ataques += 1
            l = max(self.posicoes[r][0] - self.posicoes[r][1], 0)
            c = max(self.posicoes[r][1] - self.posicoes[r][0], 0)
            while l < EstadoRainhas.tam and c < EstadoRainhas.tam:
                if self.get_pos(l, c) != -1 and self.get_pos(l, c) != r:
                    ataques += 1
                l += 1
                c += 1
            l = min(self.posicoes[r][0] + self.posicoes[r][1], EstadoRainhas.tam - 1)
            c = max(self.posicoes[r][1] - ((EstadoRainhas.tam - 1) - self.posicoes[r][0]), 0)
            while l >= 0 and c < EstadoRainhas.tam:
                if self.get_pos(l, c) != -1 and self.get_pos(l, c) != r:
                    ataques += 1
                l -= 1
                c += 1
        return ataques

    def sucessores(self) -> List[Estado]:
        suc = []
        for r in range(EstadoRainhas.tam):
            for inc, fixo, var in [(1, 0, 1), (-1, 0, 1), (1, 1, 0), (-1, 1, 0)]:
                i = self.posicoes[r][var] + inc
                while 0 <= i < EstadoRainhas.tam:
                    if self.get_pos(self.posicoes[r][fixo], i) == -1:
                        novo = EstadoRainhas(self.posicoes)
                        novo.set_pos_rainha(r, self.posicoes[r][fixo], i)
                        suc.append(novo)
                    else:
                        break
                    i += inc
        return suc

    def __str__(self) -> str:
        r = "\n"
        for i in range(EstadoRainhas.tam):
            for j in range(EstadoRainhas.tam):
                r += self.get_pos_str(i, j)
                if j + 1 < EstadoRainhas.tam:
                    r += " "
            r += "\n"
        return r

    def custo(self) -> int:
        return 1


if __name__ == "__main__":
    EstadoRainhas.tam = 8
    inicial = EstadoRainhas()
    print(f"Estado inicial:{inicial}\n")
    n = SubidaMontanha(MostraStatusConsole()).busca(inicial)
    print(f"solução:\n{n.get_estado()}\n\n")