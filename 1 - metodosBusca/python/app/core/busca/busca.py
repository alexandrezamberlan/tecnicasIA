from abc import ABC, abstractmethod
from collections import defaultdict

from .estado import Estado
from .mostra_status_console import MostraStatusConsole
from .status import Status
from .nodo import Nodo

class Busca(ABC):
    def __init__(self, mostra_status_console=None):
        self.parar = False
        self.podar = True
        self.usar_fechado = True
        self.status = Status()
        self.mstatus = mostra_status_console
        self.fechados = None

        if mostra_status_console:
            self.set_mostra(mostra_status_console)

    def init_fechados(self):
        self.fechados = defaultdict(lambda: float('inf'))

    def get_status(self):
        return self.status

    def novo_status(self):
        self.status = Status()
        if self.mstatus:
            self.mstatus.set_status(self.status)
            self.status.set_mostra(self.mstatus)
        return self.status

    def set_mostra(self, mostra_status_console: MostraStatusConsole):
        self.mstatus = mostra_status_console
        self.mstatus.set_status(self.status)
        self.status.set_mostra(self.mstatus)

    def __str__(self):
        return "Algoritmo de busca geral"

    @abstractmethod
    def busca(self, inicial:Estado)->Nodo:
        pass

    def set_parar(self, b: bool):
        self.parar = b

    def para(self):
        self.parar = True
        self.status.termina(False)

    def set_podar(self, b):
        self.podar = b

    def usar_fechados(self, b):
        self.usar_fechado = b

    def sucessores(self, pai:Nodo)->list[Nodo]:
        return self.so_novos(pai.estado.sucessores(), pai)

    def antecessores(self, pai:Nodo)->list[Nodo]:
        try:
            return self.so_novos(pai.estado.antecessores(), pai)
        except AttributeError:
            print(f"O estado {pai.estado} nao implementa antecessores!")
            return []

    def so_novos(self, estados:list[Estado], pai:Nodo)->list[Nodo]:
        suc_nodo = []
        for e in estados:
            filho = Nodo(e, pai)
            if self.podar:
                if self.usar_fechado and self.fechados is not None:
                    custo = self.fechados[e]
                    if custo == float('inf') or filho.g < custo:
                        suc_nodo.append(filho)
                        self.fechados[e] = filho.g
                elif filho.eh_descendente_novo(pai):
                    suc_nodo.append(filho)
            else:
                suc_nodo.append(filho)
        return suc_nodo