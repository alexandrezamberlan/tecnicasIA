import numpy as np

#uso da biblioteca numpy
#toda lista é transformada em array (matemática)
entradas = np.array([1,7,5])
pesos = np.array([0.8, 0.1, 0])

#somatório fica a encargo da função dot() que usa recursos multicore da CPU ou da GPU
def soma_listas(e, p):
    soma = e.dot(p)
    # soma = 0
    # for i in range(len(e)):
    #     soma += e[i] * p[i]

    return soma

def step(soma):
    if soma >= 1:
        return 1
    return 0

s = soma_listas(entradas, pesos)
ativacao = step(s)
print(s)
print(ativacao)
