import os

from cromossomo import Cromossomo

estado_final = input('Entre com a palavra do estado final: ')
tamanho_populacao = int(input('Tamanho da população: '))
quantidade_geracoes = int(input('Gerações: '))
taxa_selecao = int(input('Taxa de seleção [25 a 25]: '))
taxa_reproducao = 100 - taxa_selecao
frequencia_mutacao = int(input('Frequencia de mutação: '))

populacao = list()
nova_populacao = list()


#estados totalmente aleatorios
Cromossomo.gerar_populacao(populacao, tamanho_populacao, estado_final)
populacao.sort(key=lambda cromossomo: cromossomo.aptidao, reverse=True)
Cromossomo.exibir_populacao(populacao, 0)

for i in range(1, quantidade_geracoes):
    Cromossomo.selecionar(populacao, nova_populacao, taxa_selecao)
    Cromossomo.reproduzir(populacao, nova_populacao, taxa_reproducao, estado_final)

    #mutacao quebra a estagnacao ou máximo locais
    if i % frequencia_mutacao == 0:
        Cromossomo.mutar(nova_populacao, estado_final)
 

    populacao.clear()
    populacao.extend(nova_populacao)
    nova_populacao.clear()
    populacao.sort(key=lambda cromossomo: cromossomo.aptidao, reverse=True)
    Cromossomo.exibir_populacao(populacao, i)
