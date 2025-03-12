from typing import List

class QuadradoMagico:
    """ Problema do quadrado mágico (ver getDescricao)
        Representa um estado do mundo: o nro que está em cada
        posição do quadro.
        Nesta solução o estado inicial é o quadro com os números
        distribuìdos aleatoriamente e os sucessores são gerados
        por trocas de posição entre números.
    """
    tam = 4
    nroMagico = 34

    @staticmethod
    def set_tamanho(t: int):
        QuadradoMagico.tam = t
        QuadradoMagico.nroMagico = t * ((t * t) + 1) // 2

    @staticmethod
    def fat(n: int) -> int:
        if n == 0:
            return 1
        else:
            return n * QuadradoMagico.fat(n - 1)

    def __init__(self, modelo=None):
        self.tabuleiro = [[0 for _ in range(QuadradoMagico.tam)] for _ in range(QuadradoMagico.tam)]
        self.meuNro = 0
        self.cache_h = -1
        if modelo:
            for linha in range(QuadradoMagico.tam):
                for coluna in range(QuadradoMagico.tam):
                    self.tabuleiro[linha][coluna] = modelo.tabuleiro[linha][coluna]
            self.meuNro = modelo.meuNro

    def get_descricao(self) -> str:
        return (
            "Um quadrado magico de ordem n e um arranjo quadrado de n^2 inteiros\n"
            "distintos dispostos de tal maneira que os numeros de uma linha\n"
            "qualquer, de uma coluna qualquer ou da diagonal principal tem mesma\n"
            "soma, chamada constante magica do quadrado. O quadrado e normal se os\n"
            "n^2 numeros que o formam sao os primeiros n^2 inteiros positivos.\n\n"
            "A constante magica do quadrado e dada por: n (n^2 + 1) / 2\n"
            "Neste exemplo, n = 4 e a constante magica=34\n\n"
            "Nesta versao (a), o tabuleira inicia vazio e, \n"
            "a cada nivel, um novo numero e adicionado.\n"
            "(tem heurustica implementada)\n"
        )

    def __eq__(self, other):
        if isinstance(other, QuadradoMagico):
            for lin in range(QuadradoMagico.tam):
                for col in range(QuadradoMagico.tam):
                    if self.tabuleiro[lin][col] != other.tabuleiro[lin][col]:
                        return False
            return True
        return False

    def __hash__(self):
        return hash(str(self))

    def eh_meta(self) -> bool:
        if self.meuNro < QuadradoMagico.tam * QuadradoMagico.tam:
            return False

        s1linha = -1
        for lin in range(QuadradoMagico.tam):
            soma = sum(self.tabuleiro[lin])
            if s1linha == -1:
                s1linha = soma
            elif s1linha != soma:
                return False

        for col in range(QuadradoMagico.tam):
            soma = sum(self.tabuleiro[l][col] for l in range(QuadradoMagico.tam))
            if s1linha != soma:
                return False

        dp = sum(self.tabuleiro[lin][lin] for lin in range(QuadradoMagico.tam))
        if dp != s1linha:
            return False

        ds = sum(self.tabuleiro[(QuadradoMagico.tam - 1) - lin][lin] for lin in range(QuadradoMagico.tam))
        if ds != s1linha:
            return False

        return True

    def sucessores(self) -> List['QuadradoMagico']:
        suc = []
        seguinte = self.meuNro + 1

        for lin in range(QuadradoMagico.tam):
            for col in range(QuadradoMagico.tam):
                if self.tabuleiro[lin][col] == 0:
                    novo = QuadradoMagico(self)
                    novo.tabuleiro[lin][col] = seguinte
                    novo.meuNro = seguinte
                    if not novo.poda():
                        suc.append(novo)
        return suc

    def poda(self) -> bool:
        for lin in range(QuadradoMagico.tam):
            soma = sum(self.tabuleiro[lin][col] for col in range(QuadradoMagico.tam) if self.tabuleiro[lin][col] != 0)
            if soma != 0 and soma != QuadradoMagico.nroMagico:
                return True

        for col in range(QuadradoMagico.tam):
            soma = sum(self.tabuleiro[lin][col] for lin in range(QuadradoMagico.tam) if self.tabuleiro[lin][col] != 0)
            if soma != 0 and soma != QuadradoMagico.nroMagico:
                return True

        return False

    def __str__(self):
        r = "\n"
        for i in range(QuadradoMagico.tam):
            r += "\t".join(str(self.tabuleiro[i][j]) for j in range(QuadradoMagico.tam))
            if i + 1 < QuadradoMagico.tam:
                r += "\n"
            else:
                r += f"\nh()={self.h()}\n"
        return r

    def custo(self) -> int:
        return 1

    def h(self) -> int:
        if self.cache_h >= 0:
            return self.cache_h

        linhas = [0] * QuadradoMagico.tam
        cols = [0] * QuadradoMagico.tam

        soma = 0
        for lin in range(QuadradoMagico.tam):
            for col in range(QuadradoMagico.tam):
                linhas[lin] += self.tabuleiro[lin][col]
                cols[col] += self.tabuleiro[lin][col]
                soma += self.tabuleiro[lin][col]

        dp = sum(self.tabuleiro[lin][lin] for lin in range(QuadradoMagico.tam))
        ds = sum(self.tabuleiro[(QuadradoMagico.tam - 1) - l][l] for l in range(QuadradoMagico.tam))

        media = QuadradoMagico.nroMagico

        desvio = sum(abs(linhas[i] - media) + abs(cols[i] - media) for i in range(QuadradoMagico.tam))
        desvio += abs(dp - media)
        desvio += abs(ds - media)

        self.cache_h = desvio
        return desvio