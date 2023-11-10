import os
import random

from cromossomo import Cromossomo
from util import Util

class AG:
    @staticmethod
    def gerar_populacao(populacao, tamanho_populacao, estado_final):
        for i in range(tamanho_populacao):
            populacao.append(Cromossomo(Util.gerar_palavra(len(estado_final)), estado_final))
    
    @staticmethod
    def exibir(populacao):
        print('PARADA...tamanho....', len(populacao))
        os.system("pause")
        for i in populacao:
            print(i)

    def selecionar_por_torneio(populacao, nova_populacao, taxa_selecao):
        # OBS.: a populacao nao pode ser pequena e nem a taxa de selecao ser muito alta
        torneio = []

        # calcular quantos devem ser selecionados a partir do tamanho da populacao com a taxa_selecao
        # populacao.size()	->	100
        # qtd_selecionados	-> 	taxa_selecao
        qtd_selecionados = taxa_selecao * len(populacao) / 100
        cromossomo = populacao[0]        

        nova_populacao.append( cromossomo ) #elistismo
        
        
        i = 1
        while (i <= qtd_selecionados):
            c1 = populacao[ random.randrange( len(populacao) ) ]
            
            while (True):            
                c2 = populacao[ random.randrange( len(populacao) ) ]
                if c2 != c1:
                    break
            
            while (True):            
                c3 = populacao[ random.randrange( len(populacao) ) ]
                if c3 != c2 != c1:
                    break            

            torneio.append(c1)
            torneio.append(c2)
            torneio.append(c3)
            torneio.sort() #o primeiro é o mais apto

            selecionado = torneio[0]

            if selecionado not in nova_populacao:
                nova_populacao.append(selecionado)
                i += 1
            
            torneio.clear() #FALTOU LIMPAR A LISTA torneio para a próxima rodada      
                

    @staticmethod
    def reproduzir(populacao, nova_populacao, taxa_reproducao, estado_final):
        sPai = sMae = sFilho1 = sFilho2 = ''
             
        #calcular quantos devem ser reproduzidos a partir do tamanho da populacao com a taxa_reproducao
        #populacao.size()	->	100
        #qtdReproduzido	-> 	taxa_reproducao
        qtd_reproduzidos = taxa_reproducao * len(populacao) / 100

        #sFilho1 = Alexone - primeiraMetadeDoPai + segundaMetadeDaMae
        #sFilho2 = Simandre - primeiraMetadeDaMae + segundaMetadeDoPai
        i = 0
        while (i < qtd_reproduzidos):            
            pai = populacao[ random.randrange( len(populacao) ) ]
                
            while (True):            
                mae = populacao[ random.randrange( len(populacao) ) ]
                if mae != pai:
                    break               

            sPai = pai.valor
            sMae = mae.valor
            
            sFilho1 = sPai[0 : int(len(sPai)/2)] + sMae[int(len(sMae) / 2) : len(sMae)]
            sFilho2 = sMae[0 : int(len(sMae)/2)] + sPai[int(len(sPai) / 2) : len(sPai)]

            nova_populacao.append(Cromossomo(sFilho1, estado_final)) #estado_final é passado para calcular aptidao do filho
            nova_populacao.append(Cromossomo(sFilho2, estado_final)) #estado_final é passado para calcular aptidao do filho
            i = i + 2
                 
        #podar a nova_populacao, retirando os excedentes do final
        while(len(nova_populacao) > len(populacao)):
            nova_populacao.pop()
      
    

    @staticmethod
    def mutar(populacao, estado_final):
        qtd_mutantes = random.randrange( int(len(populacao) / 5) ) #a qtd de mutantes será no máximo 20% da população        
        
        while (qtd_mutantes > 0):
            posicao_mutante = random.randrange( int(len(populacao)) )
            mutante = populacao[ posicao_mutante ]
            print("vai mutar " + mutante)
            
            #mudando
            valor_mutado = mutante.valor

            caracter_mutante = mutante.valor[random.randrange(len(mutante.valor))]
            caracter_sorteado = Util.letras[random.randrange(Util.tamanho)]
            valor_mutado = valor_mutado.replace(caracter_mutante, caracter_sorteado)          
            mutante = Cromossomo(valor_mutado, estado_final)
            
            populacao[posicao_mutante] = mutante
        
    