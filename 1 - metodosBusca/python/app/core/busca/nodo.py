from .estado import Estado


class Nodo:
    def __init__(self, estado: Estado, pai=None):
        self.estado = estado
        self.pai = pai
        self.profundidade = 0 if pai is None else pai.get_profundidade() + 1
        self.g = estado.custo() if pai is None else estado.custo() + pai.g
        self.f = -1

    def get_profundidade(self):
        return self.profundidade

    def get_estado(self):
        return self.estado

    def get_pai(self):
        return self.pai

    def g(self):
        return self.g

    def f(self):
        if self.f == -1:
            self.f = self.g + self.estado.h()
        return self.f

    def inverte_paternidade(self):
        if self.pai.pai is not None:
            self.pai.inverte_paternidade()
        self.pai.pai = self

    def set_profundidade(self):
        if self.pai is None:
            self.profundidade = 0
        else:
            self.pai.set_profundidade()
            self.profundidade = self.pai.get_profundidade() + 1

    def eh_descendente_novo(self, ascensor):
        if ascensor is None:
            return True
        elif ascensor.estado == self.estado:
            return False
        else:
            return self.eh_descendente_novo(ascensor.pai)

    def __eq__(self, other):
        if isinstance(other, Nodo):
            return self.estado == other.estado
        return False

    def __lt__(self, other):
        return self.g < other.g

    def monta_caminho(self):
        return self._monta_caminho(self)

    def _monta_caminho(self, n):
        if n is not None:
            return self._monta_caminho(n.pai) + str(n) + "\n"
        return ""

    def __str__(self):
        return str(self.estado)