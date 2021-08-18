'''
imagine que seja preciso aplicar o exponencial para cada elemento de uma matriz/vetor

v = [v1, ..., vn] -> u = [exp(v1), exp(v2), ..., exp(vn)]

solução não vetorizada seria Numpy é um pacote para computação científica. 
Mantida pela comunidade (www.numpy.org). 
'''
import numpy as np
import time
import math

n = 1000000
v = np.random.rand(n)

u = np.zeros((n,1))

print("solucao for tradicional")
for i in range(n):
  u[i] = math.exp(v[i])

print(u)

u = np.zeros((n,1))  

print("solucao vetorizada")
u = np.exp(v)
print(u)