import random
from datetime import datetime
from typing import List
from .quadrado_magico import QuadradoMagico
from app.core.busca import SubidaMontanha

class QuadradoMagicoB(QuadradoMagico):
    def get_descricao(self) -> str:
        return (
            "Um quadrado magico de ordem n e um arranjo quadrado de n inteiros\n"
            "distintos dispostos de tal maneira que os numeros de uma linha\n"
            "qualquer, de uma coluna qualquer ou da diagonal principal tem mesma\n"
            "soma, chamada constante magica do quadrado. O quadrado e normal se os\n"
            "n numeros que o formam sao os primeiros n inteiros positivos.\n\n"
            "A constante magica do quadrado e dada por: n (n^2 + 1) / 2\n"
            "Neste exemplo, n = 4 e a constante magica=34\n\n"
            "Nesta versao (b), o tabuleiro inicia com os numeros\n"
            "aleatoriamente posicionados e cada operacao\n"
            "troca dois numeros de lugar\n"
            "(tem heuristica e geracao de estados aleatorios implementado)\n"
        )

    def __init__(self, modelo=None):
        super().__init__(modelo)
        if modelo is None:
            for i in range(1, QuadradoMagico.tam * QuadradoMagico.tam + 1):
                l, c = random.randint(0, QuadradoMagico.tam - 1), random.randint(0, QuadradoMagico.tam - 1)
                while self.tabuleiro[l][c] != 0:
                    l, c = random.randint(0, QuadradoMagico.tam - 1), random.randint(0, QuadradoMagico.tam - 1)
                self.tabuleiro[l][c] = i
            self.meuNro = QuadradoMagico.tam * QuadradoMagico.tam

    def gera_aleatorio(self) -> 'QuadradoMagicoB':
        return QuadradoMagicoB()

    def sucessores(self) -> List['QuadradoMagicoB']:
        suc = []
        for l in range(QuadradoMagico.tam - 1):
            for c in range(QuadradoMagico.tam - 1):
                novo = QuadradoMagicoB(self)
                novo.tabuleiro[l][c], novo.tabuleiro[l + 1][c] = novo.tabuleiro[l + 1][c], novo.tabuleiro[l][c]
                suc.append(novo)

                novo = QuadradoMagicoB(self)
                novo.tabuleiro[l][c], novo.tabuleiro[l][c + 1] = novo.tabuleiro[l][c + 1], novo.tabuleiro[l][c]
                suc.append(novo)
        return suc




if __name__ == "__main__":
    QuadradoMagicoB.tam = 4
    inicial = QuadradoMagicoB()
    print(f"Começou em {datetime.now()}")
    n = SubidaMontanha().busca(inicial)
    print(f"Estado inicial {inicial}")
    print(f"solução:\n{n.get_estado()}\n\n")
    print(f"Terminou em {datetime.now()}")