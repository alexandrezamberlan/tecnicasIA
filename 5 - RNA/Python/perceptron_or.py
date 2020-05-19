import sys
import numpy
class Perceptron_Or:

    def __init__(self, geracoes_maximas, soma = 0):
        
        #variável responsável pelo somatório da rede 
        self.soma = 0
        
        #responsável pelo número máximo de gerações
        self.geracoes_maximas = geracoes_maximas
        
        #variável responsável pela contagem das gerações durante o treinamento
        self.conta_geracoes = 0

        #matriz de aprendizado 
        # tabela verdade
        # 0 ou 0 = 0 - Primeiro Valor
        # 1 ou 0 = 1 - Segundo Valor
        # 0 ou 1 = 1 - Terceiro Valor
        # 1 ou 1 = 1 - Quarto Valor
        self.matriz_aprendizado = numpy.array([(0,0,0),(0,1,1),(1,0,1),(1,1,1)])

        #inicialização dos pesos sinápticos
        #Peso sináptico para primeira,segunda e terceira entradas
        self.pesos = numpy.array([0,0,0])
        
        # print(self.pesos)
        # print(self.matriz_aprendizado)

    #Método responsável pelo somatório e a função de ativação.
    def executar(self, entrada1, entrada2):
        #somatório da rede
        soma = (entrada1 * self.pesos[0]) + (entrada2 * self.pesos[1]) + ((-1) * self.pesos[2])
        
        # print("Resultado soma: ",soma)
        
        #função ativação tipo STEP
        if soma >= 0:
            return 1
        else:
            return 0
    
 
    #Método para treinamento da rede
    def treinar(self):
        while (True):
            aprendeu = True
            #laço usado para fazer todas as entradas
            for i in range(4):
                #saída recebe o resultado da rede que no caso é 1 ou 0
                saida = self.executar(self.matriz_aprendizado[i][0], self.matriz_aprendizado[i][1]);
                print("saida..... no treinamento: ", saida)
                # print("saida esperada...........: ", self.matriz_aprendizado[i][2])
                if (saida != self.matriz_aprendizado[i][2]):
                    #caso a saída seja diferente do valor esperado
                    #os pesos sinápticos serão corrigidos, ou seja, calibrados
                    print("necessidade de calibragem da rede....")
                    self.corrigir_pesos(i, saida)
                    #a variavél responsável pelo controlede treinamento recebe falso
                    aprendeu = False
                
            #acrescenta uma época
            self.conta_geracoes += 1

            #teste se houve algum erro duranteo treinamento e o número de geracoes
            #menor qe o definido
            if (aprendeu or self.conta_geracoes == self.geracoes_maximas):
                print("Geracoes executadas: %d" % self.conta_geracoes)
                break
    
 
    #Método para a correção de pesos, conhecido como HEURÍSTICA
    def corrigir_pesos(self, i, saida):
        #esta parte é realmente o treinamento ou a calibragem

        # TRATAMENTO DO ERRO EM TREINAMENTO SUPERVISIONADO
        # - erro = saidaEsperada - saidaCalculadaGerada
        # - peso = peso + (taxaAprendizagem * entrada * erro)

        self.pesos[0] = self.pesos[0] + (1 * (self.matriz_aprendizado[i][2] - saida) * self.matriz_aprendizado[i][0])
        self.pesos[1] = self.pesos[1] + (1 * (self.matriz_aprendizado[i][2] - saida) * self.matriz_aprendizado[i][1])
        self.pesos[2] = self.pesos[2] + (1 * (self.matriz_aprendizado[i][2] - saida) * (-1))
 
    def testar(self): #colocar em prática o modelo rna treinado para reconhecer
        print(" Teste 1 para 0 OR 0: %d " % self.executar(0, 0))
        print(" Teste 2 para 0 OR 1: %d " % self.executar(0, 1))
        print(" Teste 3 para 1 OR 0: %d " % self.executar(1, 0))
        print(" Teste 4 para 1 OR 1: %d " % self.executar(1, 1))


print("Aqui é processo principal")
perceptron = Perceptron_Or(1000)

perceptron.treinar()

print("Para aprender o algoritmo treinou: %d vezes " % perceptron.conta_geracoes)

perceptron.testar()
