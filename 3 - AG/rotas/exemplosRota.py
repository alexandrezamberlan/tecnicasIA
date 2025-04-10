import copy
import random

populacao = list()

rota = list()
tamanho_populacao = 100

while (tamanho_populacao > 0):
    for i in range(9):
        rota.append(i)
        
    #embaralhar a rota
    random.shuffle(rota)
    populacao.append(copy.deepcopy(rota))
    tamanho_populacao -= 1
    rota.clear()

print('populacao de rotas geradas aleatorias....')
for individuo in populacao:
    print(individuo)

