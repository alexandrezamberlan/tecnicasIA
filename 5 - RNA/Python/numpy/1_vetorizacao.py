import numpy 
import time
# lista = [1,2,3,4]
# vetor_numpy = numpy.array(lista)
# print(vetor_numpy)


# matriz = [(1, 2, 3, 4),
#           (5, 6, 7, 8)]
# matriz_numpy = numpy.array(matriz)
# print(matriz_numpy)

entradas = numpy.random.rand(50000000)
pesos = numpy.random.rand(50000000)


# soma = 0
# inicio = time.time()
# for i in range(20000000):
#   soma += entradas[i] * pesos[i]
# fim = time.time()
# #print(soma)
# print("Solucao com for: " + str(1000*(fim - inicio)) + " ms")



inicio = time.time()
soma = numpy.dot(entradas,pesos)
fim = time.time()
# print(soma)
print("Solucao vetorizada: " + str(1000*(fim - inicio)) + " ms")
