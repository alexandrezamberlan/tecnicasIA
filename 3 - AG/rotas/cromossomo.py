import random
import copy

class Cromossomo:
    def __init__(self, rota):  
        self.rota = rota
        self.aptidao = self.calcular_aptidao()

    #representa a heurísitica dinamica da solução
    def calcular_aptidao(self):
        nota_restricao = 0
        
        #primeira restricao... uma cidade de número maior vier primeiro que uma cidade de número menor, deve ter restrição com nota 10
        #Por exemplo uma rota [2, 8, 4, 0, 1, 5, 3, 6, 7]
        #Qual seria a nota de aptidao?
        #10+10+10 = 30
        for i in range(0,len(self.rota)-1):
            if (self.rota[i] > self.rota[i+1]):
                nota_restricao += 10

        #segunda restrição... se na rota aparecer mais de uma vez a mesma cidade (número); para cada par de ocorrência dar nota 20.
        analisados = list()
        for cidade in self.rota:
            ocorrencias = 0
            if cidade not in analisados:
                analisados.append(cidade)
                for c in self.rota:
                    if cidade == c:
                        ocorrencias += 1

                if ocorrencias > 1:
                    # print('bingo...')
                    nota_restricao += 20
      
        return nota_restricao
    
    def __str__(self):
        return f'Rota: {self.rota}. Aptidao com penalidade: {self.aptidao}'
    
    def __eq__(self, other):
        if isinstance(other, Cromossomo):
            return self.rota == other.rota
        return False
        

    @staticmethod
    def gerar_populacao(populacao, tamanho_populacao):
        rota = list()
        for i in range(tamanho_populacao):
            for i in range(1,10):
                rota.append(i)
            #embaralhar a rota
            random.shuffle(rota)
            populacao.append(Cromossomo(copy.deepcopy(rota)))            
            rota.clear()    

    @staticmethod
    def exibir_populacao(populacao, numero_geracao):
        print('Geração...', numero_geracao)
        for individuo in populacao:
            print(individuo)

    @staticmethod
    def selecionar(populacao, nova_populacao, taxa_selecao):
        #definir quantos serao selecionados
        #len(populacao)             - 100
        #quantidade_selecionados    - taxa_selecao
        quantidade_selecionados = int(len(populacao) * taxa_selecao / 100)

        torneio = list()

        #elistimo - o mais apto sempre é selecionado
        nova_populacao.append(populacao[0])

        i = 1
        while (i < quantidade_selecionados):
            c1 = populacao[ random.randrange( len(populacao) ) ]

            while (True):
                c2 = populacao[ random.randrange( len(populacao) ) ]
                if c1 != c2:
                    break

            while (True):
                c3 = populacao[ random.randrange( len(populacao) ) ]
                if (c1 != c3) and (c2 != c3):
                    break
            
            torneio.append(c1)
            torneio.append(c2)
            torneio.append(c3)

            torneio.sort(key=lambda cromossomo: cromossomo.aptidao)
            selecionado = torneio[0]
            if selecionado not in nova_populacao:
                nova_populacao.append(selecionado)
                i+=1

            torneio.clear()

        # print("Total de selecionados....", quantidade_selecionados)
        # print(nova_populacao)


    @staticmethod
    def reproduzir(populacao, nova_populacao, taxa_reproducao):
        #definir a quantidade de reproduzidos
        #len(populacao)             - 100
        #quantidade_reproduzidos    - taxa_reproducao
        quantidade_reproduzidos = int(len(populacao) * taxa_reproducao / 100)

        for i in range(int(quantidade_reproduzidos/2)+1):
            #sorteia um pai entre os primeiros 20% da populacao
            cromossomo_pai = populacao[ random.randrange( len(populacao) ) ]

            while (True):
                cromossomo_mae = populacao[ random.randrange( len(populacao) ) ]
                if cromossomo_pai != cromossomo_mae:
                    break

            pai = cromossomo_pai.rota
            mae = cromossomo_mae.rota

            #primeira metade do pai + segunda metade da mae
            primeira_metade = pai[0 : int(len(pai)/2)] 
            segunda_metade = mae[int(len(mae)/2) : len(mae)]

            filho1 = primeira_metade + segunda_metade
            # print(filho1)

            #primeira metade da mae + segunda metade do pai
            primeira_metade = mae[0 : int(len(mae)/2)] 
            segunda_metade = pai[int(len(pai)/2) : len(pai)]
            filho2 = primeira_metade + segunda_metade
            # print(filho2)

            nova_populacao.append(Cromossomo(filho1))
            nova_populacao.append(Cromossomo(filho2))

            #podar os excedentes
            while (len(nova_populacao) > len(populacao)):
                nova_populacao.pop()

    @staticmethod
    def mutar(populacao):
        quantidade_mutantes = random.randrange(int(len(populacao)))
        while (quantidade_mutantes > 0):
            posicao_mutante = random.randrange( len(populacao) )
            mutante = populacao[ posicao_mutante ]
            print("vai mutar " , mutante)
            
            mutante.rota[ random.randrange(len(mutante.rota)) ] = random.randrange(1,10)
            mutante.calcular_aptidao()
            
            quantidade_mutantes -= 1

            