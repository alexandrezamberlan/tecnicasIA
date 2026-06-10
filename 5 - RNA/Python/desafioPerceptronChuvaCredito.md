# RNA no cenário de chuva

Para o cenário de chuva, substituir o plano cartesiano por duas variáveis climáticas: 
    
    - Umidade do Ar (%) e 
    - Pressão Atmosférica (hPa). 
    

O Perceptron vai aprender a separar os dias em que choveu dos dias de sol. Para manter o código funcionando sem erros, os valores precisam ser normalizados entre 0 e 1, igual aos seus dados originais.


Dados Sugeridos (Clima):

    - Entradas = X: [Umidade Normalizada, Pressão Normalizada]
    - Saídas = Y: -1 para Sol / 1 para Chuva
    
Substitua as  listas de amostras e saídas do Perceptron de times por estas:

```
# Amostras: [Umidade, Pressão] (Valores normalizados entre 0 e 1)

amostras = [
    [0.20, 0.90],  # Ar seco, pressão alta -> Sol
    [0.35, 0.85],  # Ar seco, pressão alta -> Sol
    [0.15, 0.75],  # Muito seco, pressão média -> Sol
    [0.40, 0.80],  # Umidade média, pressão alta -> Sol
    [0.55, 0.45],  # Umidade média, pressão baixa -> Chuva
    [0.85, 0.30],  # Muito úmido, pressão baixa -> Chuva
    [0.90, 0.20],  # Muito úmido, pressão muito baixa -> Chuva
    [0.75, 0.35]   # Úmido, pressão baixa -> Chuva
]
```


```
# Saídas: -1 = Não Chove (Sol) | 1 = Chove

saidas = [-1, -1, -1, -1, 1, 1, 1, 1]
```

## Entendimento do cenário de chuva
    - Padrão do Sol (-1): Alta pressão estende o tempo firme, e baixa umidade não gera nuvens. Os dados ficam concentrados no canto superior esquerdo do gráfico (X baixo, Y alto).

    - Padrão da Chuva (1): Baixa pressão atrai frentes frias, e alta umidade fornece a água necessária. Os dados ficam no canto inferior direito (X alto, Y baixo).
    
    - O Desafio do Perceptron: Encontrar a linha diagonal perfeita que separa o "grupo do sol" do "grupo da chuva".

-------------------------------------------

# RNA no cenário de crédito

Análise de Risco de Crédito para a aprovação de empréstimo bancário.

O Perceptron vai aprender a separar os clientes de:

    - Baixo Risco (Aprovado) 
    - Alto Risco (Recusado).

Aprovação de Empréstimo Bancário:

    - Entradas = X: [Histórico de Crédito, Renda Mensal] (Valores normalizados entre 0
    - Saídas = Y: -1 para Cliente de Risco (Recusado) / 1 para Cliente Confiável (Aprovado).

```
# Amostras: [Histórico de Crédito, Renda Mensal] (Normalizados de 0 a 1)

# Quanto mais próximo de 1, melhor o histórico e maior a renda.

amostras = [
    [0.10, 0.20],  # Histórico ruim, renda baixa -> Recusado
    [0.25, 0.30],  # Histórico ruim, renda média-baixa -> Recusado
    [0.40, 0.15],  # Histórico médio, renda muito baixa -> Recusado
    [0.20, 0.50],  # Histórico ruim, renda média -> Recusado
    [0.70, 0.65],  # Histórico bom, renda média-alta -> Aprovado
    [0.90, 0.80],  # Histórico excelente, renda alta -> Aprovado
    [0.60, 0.90],  # Histórico bom, renda muito alta -> Aprovado
    [0.85, 0.55]   # Histórico excelente, renda média -> Aprovado
]
````


```
# Saídas: -1 = Alto Risco (Recusado) | 1 = Baixo Risco (Aprovado)

saidas = [-1, -1, -1, -1, 1, 1, 1, 1]
```