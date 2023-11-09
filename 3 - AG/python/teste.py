from util import Util
from cromossomo import Cromossomo

lista = []
lista.append(Cromossomo("asfas", "andre"))
lista.append(Cromossomo("xtfts", "andre"))

c = Cromossomo("xtfts","andre")

print(c not in lista)

for i in lista:
    print(i)

