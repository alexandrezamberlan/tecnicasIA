import random
import numpy

def sortear(titulo):
    print(titulo)
    x = random.random()
    print("Um número sorteado entre 0 e 1: ",x)
    
def repetir(titulo,inicio,fim):
    print(titulo)
    # for i in range(inicio,fim):
    #     print(i)


    for i in range(4):
        print(i)

def orientacao_a_objetos(titulo):
    print(titulo)

    a = Aluno(100,'Alexandre')
    print(a.matricula)
    print(a.nome)

def lista_orientacao_a_objetos(titulo,quantidade):
    print(titulo)

    lista_alunos = []
    for i in range(quantidade):
        matricula = input('Informe matricula: ')
        nome = input('Informe nome: ')
        lista_alunos.append(Aluno(matricula,nome))

    for i in lista_alunos:
        print(i.matricula)
        print(i.nome)
        print('===================')

    matricula = input('Qual matrícula procura? ')
    if lista_alunos.__contains__(matricula):
        print("Encontrado")
    else:
        print("Não encontrado")
    
class Aluno:
    def __init__(self, matricula, nome):
        self.matricula = matricula
        self.nome = nome    

def main():
    print("Aqui é processo principal")
    # sortear('Método para testar o random:')
    repetir('Método para testar repetição com for',2,7)
    # orientacao_a_objetos('Método para testar OO:')
    # lista_orientacao_a_objetos('Método para testar lista com objetos:',3)

if __name__ == "__main__":
    main()

