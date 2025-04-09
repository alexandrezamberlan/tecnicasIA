import random
from util import Util
from cromossomo import Cromossomo

mutante = Cromossomo("alexone", "simone")
print('antes da mutacao..', mutante)
posicao_gene_mutante = random.randrange(len(mutante.palavra))
novo_gene = Util.letras[ random.randrange(Util.tamanho) ]                        
print(posicao_gene_mutante, novo_gene)

nova_palavra = mutante.palavra[:4] + "@" + mutante.palavra[5:]
nome_mutado = nome[:4] + "@" + nome[5:]
# mutante.calcular_aptidao("simone")
# print('depois da mutacao..', mutante)
