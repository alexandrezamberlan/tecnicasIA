entradas = [1  , 7  , 5]
pesos    = [0.8, 0.1, 0]

def funcao_soma(e, p):
    s = 0
    for i in range(len(e)):
        s += e[i] * p[i]
    return s

def funcao_ativacao_step(soma):
    if soma >= 1:
        return 1
    return 0

somatorio = funcao_soma(entradas, pesos)
ativacao = funcao_ativacao_step(somatorio)
print("Somatório: " ,somatorio)
print("Resultado ativação: ", ativacao)


