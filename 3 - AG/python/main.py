import os

from ag import AG

os.system('cls')

tamanho_populacao = 10 #int(input("Tamanho da população: "))
estado_final = 'teste' #input("Palavra desejada: ")
taxa_selecao = 30 #int(input("Taxa de seleção (entre 20 a 40%): "))
taxa_reproducao = 100 - taxa_selecao
taxa_mutacao = 5 #int(input("Taxa de mutação (entre 5 a 10%): "))
qtd_geracoes = 10 #int(input("Quantidade de gerações: "))

populacao = []
nova_populacao = []

AG.gerar_populacao(populacao, tamanho_populacao, estado_final)
populacao.sort()
print("Geracao 1")
AG.exibir(populacao)

for i in range(1, qtd_geracoes):
    AG.selecionar_por_torneio(populacao, nova_populacao, taxa_selecao)
    
    AG.reproduzir(populacao, nova_populacao, taxa_reproducao, estado_final)
    
    if (i % (len(populacao) / taxa_mutacao) == 0):
        AG.mutar(nova_populacao, estado_final)
    
    populacao.clear()
    nova_populacao = populacao.copy()
    nova_populacao.clear()
    populacao.sort()

    print(f"\n\nGeração   {(i + 1)}")
    # AG.exibir(populacao)