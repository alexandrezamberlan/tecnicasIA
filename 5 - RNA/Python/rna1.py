# class Perceptron :
#     def __init__(self, entrada, peso):
#         self.entrada = entrada
#         self.peso = peso

# entradas_pesos = [Perceptron(1,0.8), Perceptron(7,0.1), Perceptron(5,0)]
# for i in range(len(entradas_pesos)):
#     print(entradas_pesos[i].entrada)
#     print(entradas_pesos[i].peso)

# def soma_orientado_a_objetos(lista_OO):
#     s = 0
#     for i in range(len(lista_OO)):
#         s+= lista_OO[i].entrada * lista_OO[i].peso
    
#     return s

# s = soma_orientado_a_objetos(entradas_pesos)
# ativacao = step(s)
# print(s)
# print(ativacao)


# ===========================
entradas = [1,7,5]
pesos = [0.8, 0.1, 0]

def soma_listas(e, p):
    s = 0
    for i in range(len(e)):
        s += e[i] * p[i]

    return s

def step(soma):
    if soma >= 1:
        return 1
    return 0

s = soma_listas(entradas, pesos)
ativacao = step(s)
print(s)
print(ativacao)

