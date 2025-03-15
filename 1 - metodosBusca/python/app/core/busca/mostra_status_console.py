import threading
import time

from app.core.busca.status import Status

class MostraStatusConsole(threading.Thread):
    def __init__(self, status: Status=None):
        super().__init__()
        self.status = status
        self.stop = False
        self.start()

    def get_status(self):
        return self.status

    def set_status(self, status):
        self.status = status

    def para(self):
        if not self.stop:
            self.stop = True
            self.join()

    def run(self):
        while not self.stop:
            try:
                time.sleep(1)
                if not self.stop and self.status is not None:
                    self.mostra()
            except Exception as e:
                print(f"Erro ao mostrar status: {e}")
        self.mostra_fim()

    def mostra_fim(self):
        print(f": Fim da busca. {self.status.nro_visitados} nodos visitados em {self.status.get_tempo_decorrido()} segundos.\n")

    def mostra(self):
        print("Status:")
        print(f"\t{self.status.nro_visitados} nodos visitados, nodos em aberto={self.status.tam_abertos}")
        print(f"\tProfundidade atual={self.status.profundidade_max}")
        # print(f"\tTempo decorrido={self.status.get_tempo_decorrido()}")
