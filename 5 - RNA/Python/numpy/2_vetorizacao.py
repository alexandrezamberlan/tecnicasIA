'''
imagine que seja preciso aplicar o exponencial para cada elemento de uma matriz/vetor

v = [v1, ..., vn] -> u = [exp(v1), exp(v2), ..., exp(vn)]

solução não vetorizada seria Numpy é um pacote para computação científica. 
Mantida pela comunidade (www.numpy.org). 
'''
import numpy
import time
import math

n = 10000000
vetor = numpy.random.rand(n)
saida = numpy.zeros((n,1))


inicio = time.time()
print("solucao for tradicional")
for i in range(n):
  saida[i] = math.exp(vetor[i])
fim = time.time()
print("Solucao com for: " + str(1000*(fim - inicio)) + " ms")
# print(u)



saida = numpy.zeros((n,1))  
inicio = time.time()
print("solucao vetorizada")
saida = numpy.exp(vetor)
fim = time.time()
print("Solucao vetorizada: " + str(1000*(fim - inicio)) + " ms")
#print(u)