import random
import numpy

def matrizes(titulo):
    print(titulo)
    #pesos = numpy.array([0,1,2])
    pesos = numpy.random.rand(500)
    print(pesos)
    
    matriz = numpy.random.randint(10,size=(500,500))
    print(matriz)

    saida = numpy.dot(matriz,pesos)
    print(saida)

def main():
    print("Aqui é processo principal")
   
    matrizes('Exemplo de array com numpy')

if __name__ == "__main__":
    main()

