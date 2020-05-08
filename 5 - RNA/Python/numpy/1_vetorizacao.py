
import numpy as np
import time

#a = np.array([1,2,3,4])
#print(a)

entradas = np.random.rand(10000000)
pesos = np.random.rand(10000000)


soma = 0
inicio = time.time()
for i in range(10000000):
  soma += entradas[i] * pesos[i]
fim = time.time()
print(soma)
print("Solucao com for: " + str(1000*(fim - inicio)) + " ms")



inicio = time.time()
soma = np.dot(entradas,pesos)
fim = time.time()
print(soma)
print("Solucao vetorizada: " + str(1000*(fim - inicio)) + " ms")
