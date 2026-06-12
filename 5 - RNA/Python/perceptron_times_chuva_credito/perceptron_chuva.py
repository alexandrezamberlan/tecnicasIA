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
				print('Pesos: ', self.pesos)
				break
 
	## Testes para "novas" amostras
	def teste(self, amostra):
		print('Pesos: ', self.pesos)
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
			print('Classe: %d. Chuva' % saida_gerada)
		else:
			print('Classe: %d. Sol' % saida_gerada)
 
	## Função funcao_ativacao_signal
	def funcao_ativacao_signal(self, soma):
		if soma >= 0:
			return 1
		return -1
 
# Dados de treinamento: amostras e saídas - lidas de arquivo
amostras = []
# Saídas: -1 = Não Chove (Sol) | 1 = Chove
saidas = []
 
# Chamar classe e fazer treinamento
rede = Perceptron(amostras, saidas)
rede.treinar()

# Execução ou produção
while (True):
	# Entrando com amostra para teste
	umidade = float(input('Valor para umidade: '))
	pressao = float(input('Valor para pressao: '))
	print('Ponto: ', umidade , ' , ', pressao)
	rede.teste([umidade,pressao])
	
	#Os dados utilizados correspondem a pares de coordenadas (x,y) para classificação de cores: 
	# 1 é azul e -1 é vermelho. 

