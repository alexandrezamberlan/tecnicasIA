import random
import numpy

def matrizes(titulo):
    print(titulo)
    pesos = numpy.array([0,1,2])
    print(pesos)
    
    matriz = numpy.array([(0,1,2),(3,4,5),(6,7,8)])
    print(matriz)

def main():
    print("Aqui é processo principal")
   
    matrizes('Exemplo de array com numpy')

if __name__ == "__main__":
    main()

