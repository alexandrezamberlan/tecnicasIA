import sys
import random
 
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
				soma = 0
				# Para cada atributo
				for j in range(self.n_atributos + 1):
					# Multiplicar amostra e seu peso e também somar com o potencial que já tinha
					soma += self.pesos[j] * self.amostras[i][j]
				# Obter a saída da rede considerando a função sinal
				saida_gerada = self.funcao_ativacao_signal(soma)
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
			print('Classe: %d. Time Azul' % saida_gerada)
		else:
			print('Classe: %d. Time Vermelho' % saida_gerada)
 
	## Função funcao_ativacao_signal
	def funcao_ativacao_signal(self, soma):
		if soma >= 0:
			return 1
		return -1
 
# Amostras (entrada e saída) para treinamento
amostras = [[0.72, 0.82],   [0.91, -0.69],
	[0.46, 0.80],   [0.03, 0.93],
	[0.12, 0.25],   [0.96, 0.47],
	[0.8, -0.75],   [0.46, 0.98],
	[0.66, 0.24],   [0.72, -0.15],
	[0.35, 0.01],   [-0.16, 0.84],
	[-0.04, 0.68],  [-0.11, 0.1],
	[0.31, -0.96],  [0.0, -0.26],
	[-0.43, -0.65], [0.57, -0.97],
	[-0.47, -0.03], [-0.72, -0.64],
	[-0.57, 0.15],  [-0.25, -0.43],
	[0.47, -0.88],  [-0.12, -0.9],
	[-0.58, 0.62],  [-0.48, 0.05],
	[-0.79, -0.92], [-0.42, -0.09],
	[-0.76, 0.65],  [-0.77, -0.76]]

saidas = [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 
# Chamar classe e fazer treinamento
rede = Perceptron(amostras, saidas)
rede.treinar()
 
# Entrando com amostra para teste
x = 0
y = 0
print('Ponto: ', x , ' , ', y)
rede.teste([x,y])
#sys.exit("fim de teste")

#Os dados utilizados correspondem a pares de coordenadas (x,y) para classificação de cores: 
# 1 é azul e -1 é vermelho. 

