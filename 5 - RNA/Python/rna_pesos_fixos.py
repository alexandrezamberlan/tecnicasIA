# -*- coding: utf-8 -*-
"""
Created on Thu May  7 11:14:42 2020

@author: Crhistopher Lenhard
"""
import numpy as np

#classe do professor
class Professor:
    #contrutor
    def __init__(self, nome, disciplina, vetorNome, vetorDisciplina):
        self.nome = nome
        self.disciplina = disciplina
        self.vetorNome = np.array(vetorNome)
        self.vetorDisciplina = np.array(vetorDisciplina)
        
#função que cria uma matriz de pesos 
def criarMatrizPesos(lista):
    pesos = []
    for i in range(len(lista[0].vetorNome)):
        linhaPeso = []
        for j in range(len(lista[0].vetorDisciplina)):
            peso = 0
            for prof in lista:
                peso = peso + prof.vetorNome[i] * prof.vetorDisciplina[j]
            linhaPeso.append(peso)
        pesos.append(linhaPeso)
    return np.array(pesos)

#função que printa uma matriz/array
def printItem(item):
    for row in item:
        print(row)
    print("")

#função que calcula a saida de um professor
def calcularSaidaArea(prof, pesos): 
    saidaArea = []
    for j in range(len(prof.vetorDisciplina)):
        valor = 0
        for i in range(len(prof.vetorNome)):
            valor = pesos[i][j] * prof.vetorNome[i] + valor
        saidaArea.append(valor)
    return np.array(saidaArea)

#função que testa a saida
def testar(saidaArea, listaProfessores):
    for row in listaProfessores:
        if(hash(saidaArea.tostring()) == hash(row.vetorDisciplina.tostring())):
            print("SaidaArea: ", saidaArea, "= Professor: ",
                  row.vetorDisciplina, " --> ", row.nome)
            return
    print("Não encontrado")

#função que popula a lista com professores já determinados
def popularProfessoresPronto():
    vAlexandre = [1, 1, 1, 1]
    inteligenciaArtificial = [1, 1, 1]
    alexandre = Professor("Alexandre", "Inteligência Artificial", vAlexandre, inteligenciaArtificial) 
    vReiner = [1, 1, 1, -1]
    linguagemDeProgramacao = [1, 1, -1]
    reiner = Professor("Reiner", "Linguagem de Programação", vReiner, linguagemDeProgramacao) 
    vAna = [1, 1, -1, 1]
    programacaoParalela = [1, -1, 1]
    ana = Professor("Ana", "Programação Paralela", vAna, programacaoParalela)   
    vGustavo = [1, 1, -1, -1]
    bancoDeDados = [1, -1, -1]
    gustavo = Professor("Gustavo", "Banco de Dados", vGustavo, bancoDeDados)
    vSylvio = [1, -1, 1, 1]
    redesDeComputadores = [-1, 1, 1]
    sylvio = Professor("Sylvio", "Redes de Computadores", vSylvio, redesDeComputadores)   
    vAlessandro = [1, -1, 1, -1]
    sistemasDigitais = [-1, 1, -1]
    alessandro = Professor("Alessandro", "Sistemas Digitais", vAlessandro, sistemasDigitais)  
    vGuilherme = [1, -1, -1, 1]
    computacaoGrafica = [-1, -1, 1]
    guilherme = Professor("Guilherme", "Computação Gráfica", vGuilherme, computacaoGrafica)
    vMirkos = [1, -1, -1, -1]
    otimizacaoComputacional = [-1, -1, -1]
    mirkos = Professor("Mirkos", "Otimização Computacional", vMirkos, otimizacaoComputacional)
    
    listaProfessores = []
    listaProfessores.append(alexandre)
    listaProfessores.append(reiner)
    listaProfessores.append(ana)
    listaProfessores.append(gustavo)
    listaProfessores.append(sylvio)
    listaProfessores.append(alessandro)
    listaProfessores.append(guilherme)
    listaProfessores.append(mirkos)
    return np.array(listaProfessores)

#popula os professores 1 por 1
def popularProfessores():
    listaProfessores = []
    vetorNome = []
    vetorDis = []
    
    op = int(input("0 - Inserir professor\n1 - Terminar popular"))
    
    while(op == 0):
        nome = input("Nome do Professor: ")
        disciplina = input("Disciplina: ")
        #calcular vetor nome
        #calcular vetor disciplina
        
        listaProfessores.append(Professor(nome, disciplina, vetorNome, vetorDis))
        
        op = int(input("0 - Inserir professor\n1 - Terminar popular"))
    return np.array(listaProfessores)

def main():
    print("Popular professores")
    #lista de professores já com alguns professores
    listaProfessores = popularProfessoresPronto()
    
    #lista de professores popular do zero
    #listaProfessores = popularProfessores()
    print("Popular completo")
    
    #criar matriz de pesos
    pesos = criarMatrizPesos(listaProfessores)
    
    print("Pesos: ")
    printItem(pesos)
    
    print("Qual professor encontrar: ")
    for i in range(len(listaProfessores)):
        print(i, " - ", listaProfessores[i].nome)
    prof = int(input("Número do professor: "))
    #criar a saida de areas
    saidaArea = calcularSaidaArea(listaProfessores[prof], pesos)
    
    print("Saída Área: ")
    printItem(saidaArea)
    
    #função sign
    saidaArea = np.sign(saidaArea)
    
    #testar
    testar(saidaArea, listaProfessores)


if __name__ == '__main__':
    main()





















