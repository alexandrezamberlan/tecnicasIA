
#exemplo de rede neural feed forward multicamada porta lógica XOR


import numpy as np

def sigmoid(soma):
    return 1 / (1 + np.exp(-soma))


#porta lógica XOR
entradas = np.array([[0,0],
                    [0,1],
                    [1,0],
                    [1,1]])
#print(entradas)


saidas = np.array([[0],[1],[1],[0]])
#print(saidas)


pesos_0 = np.array([[-0.424, -0.740, -0.961],
                  [0.358, -0.577, -0.469]])
#print(pesos_0)                  

pesos_1 = np.array([[-0.017], [-0.893], [0.148]])
#print(pesos_1)


camada_entrada = entradas
soma_sinapses_0 = np.dot(camada_entrada, pesos_0)
#print(soma_sinapses_0)
camada_oculta = sigmoid(soma_sinapses_0)
#print(camada_oculta)
soma_sinapses_1 = np.dot(camada_oculta, pesos_1)
#print(resultado)
camada_saida = sigmoid(soma_sinapses_1)
#print(camada_saida)

erro_camada_saida = saidas - camada_saida
#print(erro_camada_saida)

media_absoluta = np.mean(np.abs(erro_camada_saida))
print(media_absoluta)


