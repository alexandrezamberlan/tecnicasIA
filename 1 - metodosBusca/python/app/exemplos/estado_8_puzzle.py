import random

from app.core.busca import (
    Antecessor,
    BuscaLargura,
    Estado,
    Heuristica,
    MostraStatusConsole,
)


class Estado8Puzzle(Estado, Heuristica, Antecessor):
    tam = 3
    estado_meta = None

    def __init__(self, p=None):
        self.tabuleiro = [[0] * self.tam for _ in range(self.tam)]
        self.col_branco = -1
        self.lin_branco = -1

        if p is None:
            for r in range(self.tam * self.tam):
                l = random.randint(0, self.tam - 1)
                c = random.randint(0, self.tam - 1)
                while self.tabuleiro[l][c] != 0:
                    l = random.randint(0, self.tam - 1)
                    c = random.randint(0, self.tam - 1)
                self.tabuleiro[l][c] = r
            self.set_pos_branco()
        else:
            for l in range(self.tam):
                for c in range(self.tam):
                    self.tabuleiro[l][c] = p[l][c]
            self.set_pos_branco()

    def get_descricao(self):
        return (
            "Este problema consiste em posicionar 8\n"
            "numeros em um tabuleiro 3x3 na seguinte disposcao:\n"
            "1 2 3\n"
            "8   4\n"
            "7 6 5\n"
            "sendo que o espaco pode se mover.\n"
            "(bom candidato a busca bi-direcional)\n"
        )

    def set_pos_branco(self):
        for l in range(self.tam):
            for c in range(self.tam):
                if self.tabuleiro[l][c] == 0:
                    self.col_branco = c
                    self.lin_branco = l

    def __eq__(self, other):
        if isinstance(other, Estado8Puzzle):
            return self.tabuleiro == other.tabuleiro
        return False

    def __hash__(self):
        return hash(str(self))

    def eh_meta(self):
        return self == self.estado_meta

    def h(self):
        return self.h2() + self.h3()

    def h1(self):
        fora = 0
        if self.tabuleiro[0][0] != 1: fora += 1
        if self.tabuleiro[0][1] != 2: fora += 1
        if self.tabuleiro[0][2] != 3: fora += 1
        if self.tabuleiro[1][0] != 8: fora += 1
        if self.tabuleiro[1][1] != 0: fora += 1
        if self.tabuleiro[1][2] != 4: fora += 1
        if self.tabuleiro[2][0] != 7: fora += 1
        if self.tabuleiro[2][1] != 6: fora += 1
        if self.tabuleiro[2][2] != 5: fora += 1
        return fora

    def h2(self):
        fora = 0
        for n in range(self.tam * self.tam):
            l = self.get_lin_nro(n)
            c = self.get_col_nro(n)
            l_meta = self.estado_meta.get_lin_nro(n)
            c_meta = self.estado_meta.get_col_nro(n)
            fora += abs(l - l_meta) + abs(c - c_meta)
        return fora

    def h3(self):
        fora = 0
        for n in range(1, self.tam * self.tam):
            l = self.get_lin_nro(n)
            c = self.get_col_nro(n)
            l_ant, c_ant = self.get_antecessor_pos(l, c)
            nro_ant = self.tabuleiro[l_ant][c_ant]
            if n == 1:
                if nro_ant != 8:
                    fora += 1
            elif nro_ant + 1 != n:
                fora += 1
        return fora

    def get_col_nro(self, n):
        for l in range(self.tam):
            for c in range(self.tam):
                if self.tabuleiro[l][c] == n:
                    return c
        return -1

    def get_lin_nro(self, n):
        for l in range(self.tam):
            for c in range(self.tam):
                if self.tabuleiro[l][c] == n:
                    return l
        return -1

    def get_antecessor_pos(self, l, c):
        if l == 0 and c == 0: return 1, 0
        if l == 0 and c == 1: return 0, 0
        if l == 0 and c == 2: return 0, 1
        if l == 1 and c == 0: return 2, 0
        if l == 1 and c == 2: return 0, 2
        if l == 2 and c == 0: return 2, 1
        if l == 2 and c == 1: return 2, 2
        if l == 2 and c == 2: return 1, 2
        return 0, 0

    def sucessores(self):
        suc = []
        if self.lin_branco > 0:
            novo = Estado8Puzzle(self.tabuleiro)
            novo.tabuleiro[self.lin_branco - 1][self.col_branco] = 0
            novo.tabuleiro[self.lin_branco][self.col_branco] = self.tabuleiro[self.lin_branco - 1][self.col_branco]
            novo.lin_branco = self.lin_branco - 1
            novo.col_branco = self.col_branco
            suc.append(novo)
        if self.lin_branco < self.tam - 1:
            novo = Estado8Puzzle(self.tabuleiro)
            novo.tabuleiro[self.lin_branco + 1][self.col_branco] = 0
            novo.tabuleiro[self.lin_branco][self.col_branco] = self.tabuleiro[self.lin_branco + 1][self.col_branco]
            novo.lin_branco = self.lin_branco + 1
            novo.col_branco = self.col_branco
            suc.append(novo)
        if self.col_branco > 0:
            novo = Estado8Puzzle(self.tabuleiro)
            novo.tabuleiro[self.lin_branco][self.col_branco - 1] = 0
            novo.tabuleiro[self.lin_branco][self.col_branco] = self.tabuleiro[self.lin_branco][self.col_branco - 1]
            novo.lin_branco = self.lin_branco
            novo.col_branco = self.col_branco - 1
            suc.append(novo)
        if self.col_branco < self.tam - 1:
            novo = Estado8Puzzle(self.tabuleiro)
            novo.tabuleiro[self.lin_branco][self.col_branco + 1] = 0
            novo.tabuleiro[self.lin_branco][self.col_branco] = self.tabuleiro[self.lin_branco][self.col_branco + 1]
            novo.lin_branco = self.lin_branco
            novo.col_branco = self.col_branco + 1
            suc.append(novo)
        return suc

    def antecessores(self):
        return self.sucessores()

    def tem_solucao(self):
        meta = self.estado_meta
        t = Estado8Puzzle(self.tabuleiro)
        cb = t.get_col_nro(0)
        lb = t.get_lin_nro(0)
        while lb < meta.get_lin_nro(0):
            self.troca(t.tabuleiro, lb, cb, lb + 1, cb)
            lb += 1
        while lb > meta.get_lin_nro(0):
            self.troca(t.tabuleiro, lb, cb, lb - 1, cb)
            lb -= 1
        while cb < meta.get_col_nro(0):
            self.troca(t.tabuleiro, lb, cb, lb, cb + 1)
            cb += 1
        while cb > meta.get_col_nro(0):
            self.troca(t.tabuleiro, lb, cb, lb, cb - 1)
            cb -= 1
        nro_trocas = 0
        for l in range(self.tam):
            for c in range(self.tam):
                if t.tabuleiro[l][c] != meta.tabuleiro[l][c]:
                    vl_ok = meta.tabuleiro[l][c]
                    self.troca(t.tabuleiro, l, c, t.get_lin_nro(vl_ok), t.get_col_nro(vl_ok))
                    nro_trocas += 1
        return nro_trocas % 2 == 0

    def troca(self, tab, l1, c1, l2, c2):
        tab[l1][c1], tab[l2][c2] = tab[l2][c2], tab[l1][c1]

    def __str__(self):
        if not hasattr(self, 'to_string_cache'):
            r = "\n"
            for i in range(self.tam):
                r += " ".join(map(str, self.tabuleiro[i])) + "\n"
            self.to_string_cache = r
        return self.to_string_cache

    def custo(self):
        return 1

    @staticmethod
    def get_estado_facil():
        e8 = Estado8Puzzle([[8, 1, 3], [0, 7, 2], [6, 5, 4]])
        e8.set_pos_branco()
        return e8

    @staticmethod
    def get_estado_dificil():
        e8 = Estado8Puzzle([[7, 8, 6], [2, 3, 5], [1, 4, 0]])
        e8.set_pos_branco()
        return e8

    @staticmethod
    def get_estado_muito_dificil():
        e8 = Estado8Puzzle([[2, 3, 4], [1, 0, 5], [8, 7, 6]])
        e8.set_pos_branco()
        return e8

    def get_estado_meta(self):
        return self.estado_meta
    
    def set_estado_meta(self):
        self.estado_meta = Estado8Puzzle([[1, 2, 3], [8, 0, 4], [7, 6, 5]])
        self.estado_meta.set_pos_branco()

        

if __name__ == "__main__":
    e8 = Estado8Puzzle.get_estado_dificil()
    e8.set_estado_meta()
    print(f"estado inicial (h={e8.h()}) = {e8}")

    if not e8.tem_solucao():
        print(f"{e8} nao tem solucao!")
    else:
        s = BuscaLargura(MostraStatusConsole()).busca(e8)
        if s is not None:
            print(f"solucao ({s.get_profundidade()})= {s.monta_caminho()}")
