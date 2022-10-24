import numpy
lista = numpy.array([1,2,3,4,6])
print(lista)

amostras = numpy.random.randint(10,size=(5,5))
print(amostras)

vetor_soma = lista.dot(amostras)
print(vetor_soma)
print(numpy.sum(vetor_soma))