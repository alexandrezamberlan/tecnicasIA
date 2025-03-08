from .busca_profundidade import BuscaProfundidade
from .status import Status

class BuscaIterativo(BuscaProfundidade):
    def __init__(self, mostra_status_console=None):
        super().__init__()
        self.status = Status()
        if mostra_status_console:
            self.set_mostra(mostra_status_console)

    def set_mostra(self, mostra_status_console):
        self.mstatus = mostra_status_console
        self.mstatus.set_status(self.status)
        self.status.set_mostra(self.mstatus)

    def novo_status(self):
        self.status = Status()
        if self.mstatus:
            self.mstatus.set_status(self.status)
            self.status.set_mostra(self.mstatus)
        return self.status

    def busca(self, inicial):
        self.status.inicia()
        self.init_fechados()

        prof = 0
        while not self.parar:
            self.status.profundidade_max = prof
            self.set_prof_max(prof)  # indica a profundidade maxima atual
            prof += 1
            n = super().busca(inicial)
            self.status.nro_visitados += self.status.nro_visitados  # acumula das varias buscas em profundidade
            if n is not None:
                self.status.termina(True)
                return n

        self.status.termina(False)
        return None

    def __str__(self):
        return "BPI - busca em profundidade iterativo"