from .busca_heuristica import BuscaHeuristica
from .nodo import Nodo

class SubidaMontanha(BuscaHeuristica):
    def __init__(self, mostra_status_console=None):
        super().__init__(mostra_status_console)

    def busca(self, corrente):
        self.status.inicia()
        self.init_fechados()

        melhor = corrente
        while not self.parar and corrente is not None:
            filhos = corrente.sucessores()
            if not filhos:
                corrente = corrente.gera_aleatorio()  # começa em outro lugar aleatório
                continue

            # acha o menor h entre os filhos
            melhor_filho = None
            for e in filhos:
                if melhor_filho is None or e.h() < melhor_filho.h():
                    melhor_filho = e

            self.status.nro_visitados += 1

            # se não tem filho melhor que corrente
            if melhor_filho.h() >= corrente.h():
                if corrente.eh_meta():
                    self.status.termina(True)
                    return Nodo(corrente, None)
                else:  # máximo local
                    if corrente.h() < melhor.h():
                        melhor = corrente
                    corrente = corrente.gera_aleatorio()  # começa em outro lugar aleatório
            else:  # tem filho melhor que corrente
                corrente = melhor_filho

        self.status.termina(False)
        return None

    def __str__(self):
        return "BSM - busca com subida da montanha"