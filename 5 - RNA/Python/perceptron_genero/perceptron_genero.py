import sys
import random
import numpy

class Util:
    @staticmethod
    def popular_amostras(nome_arquivo):
        lista_tmp = []
        lista = []
        #abrir o arquivo de amostras e popular a lista
        try:
            with open(nome_arquivo, "r", encoding='utf8') as leitor:
                for linha in leitor:                    
                    dados_linha = linha.split(';')
                    for indice in range(len(dados_linha)):
                        lista_tmp.append(float(dados_linha[indice]))                    	
                    lista.append(lista_tmp)
                    lista_tmp = []
        except:
            pass
        return lista

    @staticmethod
    def popular_saidas(nome_arquivo):
        lista = []
        #abrir o arquivo de saidas e popular a lista
        try:
            with open(nome_arquivo, "r", encoding='utf8') as leitor:
                for linha in leitor:                    
                    lista.append(int(linha))
        except:
            pass
        
        return lista



class Perceptron:
	## Primeira função de uma classe (método construtor de objetos)
	## self é um parâmetro obrigatório que receberá a instância criada
	def __init__(self, amostras, saidas, taxa_aprendizado=0.1, geracoes=1000, limiar=1):
		self.amostras = amostras
		self.saidas = saidas
		self.taxa_aprendizado = taxa_aprendizado
		self.geracoes = geracoes
		self.limiar = limiar
		self.n_amostras = len(amostras) # número de linhas (amostras)
		self.n_atributos = len(amostras[0]) # número de colunas (atributos)
		self.pesos = []
 
	## Realizar o treinamento com conjunto de amostras fornecidas: relação entrada x saída
	def treinar(self):
		# Inserir o valor do limiar na posição "0" para cada amostra da lista "amostras"
		# Ex.: [[0.72, 0.82], ...] vira [[1, 0.72, 0.82], ...]
		for amostra in self.amostras:
			amostra.insert(0, self.limiar)
		
		# Gerar valores aleatórios entre 0 e 1 (pesos) conforme o número de atributos
		# a lista de pesos tem tamanho da quantidade de atributos de uma amostra
		for i in range(self.n_atributos):
			self.pesos.append(random.random()) 

		# Inserir o valor do limiar na posição "0" do vetor de pesos
		# Ex.: [0.8, 0.1] vira [1, 0.8, 0.1]
		self.pesos.insert(0, self.limiar)

		# Inicializar contador de gerações
		geracoes = 0
		
		while True:
			# Assume-se que o treinamento vai ser eficiente e numa geração o algoritmo possa aprender
			aprendeu = True 
			# Para cada amostra
			for i in range(self.n_amostras):
				# Inicializar potencial de ativação
			
				# print(soma)
				# Para cada atributo
				soma = 0
				for j in range(self.n_atributos + 1):
					# Multiplicar amostra e seu peso e também somar com o potencial que já tinha
					soma += self.pesos[j] * self.amostras[i][j]
				#print(soma)
				# Obter a saída da rede considerando a função sinal
				saida_gerada = self.funcao_ativacao_signal(soma)
				print(saida_gerada)
				# Verificar se a saída da rede é diferente da saída desejada
				# se sim, calibrar ou treinar ou ajustar ou fazer aprender
				if saida_gerada != self.saidas[i]:
					# Calcular o erro
					erro = self.saidas[i] - saida_gerada
					# Fazer o ajuste dos pesos para cada elemento da amostra
					for j in range(self.n_atributos + 1):
						self.pesos[j] = self.pesos[j] + self.taxa_aprendizado * erro * self.amostras[i][j]
					
					# se entrou no if é porque ainda não aprendeu
					aprendeu = False
			geracoes += 1
			if aprendeu or geracoes > self.geracoes:
				print('Quantidade de gerações para aprender: %d\n' % geracoes)
				break
 
	## Testes para "novas" amostras
	def teste(self, amostra):
		# Inserir o valor do limiar na posição "0" para cada amostra da lista "amostras"
		amostra.insert(0, self.limiar)
		# Inicializar potencial de ativação
		soma = 0
		# Para cada atributo...
		for i in range(self.n_atributos + 1):
			# Multiplicar amostra e seu peso e também somar com o potencial que já tinha
			soma += self.pesos[i] * amostra[i]
		# Obter a saída da rede considerando g a função funcao_ativacao_signal
		saida_gerada = self.funcao_ativacao_signal(soma)

		if saida_gerada == 1:
			print('Classe: %d. Homem' % saida_gerada)
		else:
			print('Classe: %d. Mulher' % saida_gerada)
 
	## Função funcao_ativacao_signal
	def funcao_ativacao_signal(self, soma):
		if soma >= 0:
			return 1
		return -1
 
# Amostras (entrada e saída) para treinamento
nome_arquivo_amostra = input("Nome do arquivo de amostra para o treinamento: ")
nome_arquivo_saida = input("Nome do arquivo com saídas para o treinamento: ")

amostras = Util.popular_amostras(nome_arquivo_amostra)
saidas = Util.popular_saidas(nome_arquivo_saida)


print("Amostras...")
print(amostras)

print("Saidas...")
print(saidas)
 
# # Chamar classe e fazer treinamento
perceptron = Perceptron(amostras, saidas)
perceptron.treinar()
 
# # Entrando com amostra para teste
barba = float(input("-1 a 1, mais perto do 1, mais barburdo: "))
voz = float(input("-1 a 1, mais perto do 1, mais grave a voz: "))
gogo = float(input("-1 a 1, mais perto do 1, mais gogo: "))

perceptron.teste([barba,voz])
