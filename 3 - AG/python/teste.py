# from util import Util
# from cromossomo import Cromossomo

# lista = []
# lista.append(Cromossomo("asfas", "andre"))
# lista.append(Cromossomo("xtfts", "andre"))

# c = Cromossomo("xtfts","andre")

# print(c not in lista)

# for i in lista:
#     print(i)



# sPai = "gabriela"
# sMae = "simonesa"


# sFilho1 = sPai[0 : int(len(sPai)/2)] + sMae[int(len(sMae) / 2) : len(sMae)]
# sFilho2 = sMae[0 : int(len(sMae)/2)] + sPai[int(len(sPai) / 2) : len(sPai)]

# print(sFilho1)
# print(sFilho2)

import random

letras = "abcdefghijklmnopqrstuvxwyz"

valor = "abcd"

print(valor[ random.randrange(len(valor)) ])
print(letras[ random.randrange(len(letras)) ])

lista = [1,2,3,4,5]
lista[0] = 100
print(lista)

