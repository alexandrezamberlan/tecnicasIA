from app.core.grafo import GrafoNaoDirigido

if __name__ == "__main__":
    f_grafo = GrafoNaoDirigido()
    # f = GrafoDirigido()
    
    f_grafo.cria_vertice(1)
    f_grafo.cria_vertice(2)
    f_grafo.cria_vertice(3)
    f_grafo.cria_vertice(4)
    
    f_grafo.cria_aresta(2, 2, 1)
    f_grafo.cria_aresta(1, 2, 1)  # polimorfismo 
    f_grafo.cria_aresta(2, 3, 1)
    f_grafo.cria_aresta(1, 3, 1)
    f_grafo.cria_aresta(4, 3, 1)
    f_grafo.cria_aresta(1, 4, 1)
    f_grafo.cria_aresta(1, 4, 1)
    
    print(f_grafo)