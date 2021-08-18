import numpy

class Perceptron:
    def __init__(self, entradas, saidas, taxa_aprendizagem = 0.1):
        self.entradas = entradas
        self.saidas = saidas
        self.pesos = numpy.array([0.0, 0.0])
        self.taxa_aprendizagem = taxa_aprendizagem

    def funcao_ativacao_step(self,soma):
        if (soma >= 1):
            return 1
        else:
            return 0
        
    def somatorio(self,registro, pesos):
        soma = registro.dot(pesos)
        # soma = numpy.dot(entradas,pesos)
        return soma

    def treinar(self):
        while (True):
            aprendeu = True
            for i in len(self.saidas):
                saida_gerada = somatorio(numpy.array(entradas[i]))
                if (self.saidas[i] != saida_gerada):
                    aprendeu = False
                    erro = self.saidas[i] - saida_gerada
                    #calibrar os pesos
                    for j in len(pesos):
                        self.peso[i] = self.peso[i] + (self.taxa_aprendizagem * entradas[i][j] * erro)
                    
entradas = numpy.array([[0,0],[0,1],[1,0],[1,1]])
saidas = numpy.array([0,0,0,1])
p = Perceptron(entradas,saidas)
p.treinar()


