from datetime import datetime

class Status:
    def __init__(self):
        self.nro_visitados = 0
        self.profundidade_max = 0
        self.tam_abertos = 0
        self.tempo_inicio = None
        self.ms = None
        self.resolveu = False

    def set_mostra(self, ms):
        self.ms = ms

    def inicia(self):
        self.nro_visitados = 0
        self.profundidade_max = 0
        self.tempo_inicio = datetime.now()

    def termina(self, resolveu):
        self.resolveu = resolveu
        if self.ms is not None:
            self.ms.para()

    def resolveu(self):
        return self.resolveu

    def get_tempo_decorrido(self):
        agora = datetime.now()
        return (agora - self.tempo_inicio).total_seconds()

    def get_visitados(self):
        return self.nro_visitados

    def get_profundidade(self):
        return self.profundidade_max

    def explorando(self, n, s):
        self.tam_abertos = s
        self.nro_visitados += 1

        if n.get_profundidade() > self.profundidade_max:
            self.profundidade_max = n.get_profundidade()