from app.core.busca.busca_profundidade import BuscaProfundidade
from app.core.busca.estado import Estado
from app.core.busca.stack import Stack
from app.core.busca.mostra_status_console import MostraStatusConsole


class Hannoi(Estado):


    def __init__(self, torre1: Stack, torre2: Stack, torre3: Stack, op: str):
        super().__init__()
        self.torre1 = torre1
        self.torre2 = torre2
        self.torre3 = torre3
        self.op = op
    
    def get_descricao(self):
        return 'Problema das torres de Hannoi....'

    def sucessores(self) -> list:
        visitados: list[Estado] = []
        visitados_temp: list[Estado] = []
        self.mover_torre_1_torre_2(visitados=visitados_temp)
        self.mover_torre_1_torre_3(visitados=visitados_temp)
        self.mover_torre_2_torre_1(visitados=visitados_temp)
        self.mover_torre_2_torre_3(visitados=visitados_temp)
        self.mover_torre_3_torre_1(visitados=visitados_temp)
        self.mover_torre_3_torre_2(visitados=visitados_temp)

        visitados.clear()
        visitados.extend(visitados_temp)
        return visitados


    def __str__(self):
        return f'{self.torre1} {self.torre2} {self.torre3}->{self.op} \n'
    
    def __eq__(self, other):
        return self.torre1 == other.torre1 and self.torre2 == other.torre2 and self.torre3 == other.torre3

    def __hash__(self):
        return hash(f"{self.torre1}{self.torre2}{self.torre3}")

    def custo(self):
        return 1
    
    def eh_meta(self):
        return self.torre1.is_empty() and self.torre2.is_empty() and len(self.torre3) == 3
    
    def eh_valido(self, origem: Stack, destino: Stack) -> bool:
        if origem.is_empty():
            return False
        if destino.is_empty():
            return True
        return origem.peek() < destino.peek()
    
    def mover(self, origem: Stack, destino: Stack, aux: Stack, visitados: list[Estado]):
        if self.eh_valido(origem, destino):
            origem, destino, aux = origem.clone(), destino.clone(), aux.clone()
            destino.push(origem.pop())
            novo: Estado = Hannoi(origem, destino, aux, f'{origem} -> {destino}')
            
            if novo not in visitados:
                visitados.append(novo)
    
    def mover_torre_1_torre_2(self, visitados: list[Estado]):
        origem, destino, aux = self.torre1.clone(), self.torre2.clone(), self.torre3.clone()
        if self.eh_valido(origem, destino):
            destino.push(origem.pop())
            novo: Estado = Hannoi(origem, destino, aux, 'Movendo torre 1 -> torre 2')
            
            if novo not in visitados:
                visitados.append(novo)
    
    def mover_torre_1_torre_3(self, visitados: list[Estado]):
        origem, destino, aux = self.torre1.clone(), self.torre3.clone(), self.torre2.clone()
        if self.eh_valido(origem, destino):
            destino.push(origem.pop())
            novo: Estado = Hannoi(origem, aux, destino, 'Movendo torre 1 -> torre 3')
            
            if novo not in visitados:
                visitados.append(novo)
    
    def mover_torre_2_torre_1(self, visitados: list[Estado]):
        origem, destino, aux = self.torre2.clone(), self.torre1.clone(), self.torre3.clone()
        if self.eh_valido(origem, destino):
            destino.push(origem.pop())
            novo: Estado = Hannoi(destino, origem, aux, 'Movendo torre 2 -> torre 1')
            
            if novo not in visitados:
                visitados.append(novo)
    
    def mover_torre_2_torre_3(self, visitados: list[Estado]):
        origem, destino, aux = self.torre2.clone(), self.torre3.clone(), self.torre1.clone()
        if self.eh_valido(origem, destino):
            destino.push(origem.pop())
            novo: Estado = Hannoi(aux, origem, destino, 'Movendo torre 2 -> torre 3')
            
            if novo not in visitados:
                visitados.append(novo)
    
    def mover_torre_3_torre_1(self, visitados: list[Estado]):
        origem, destino, aux = self.torre3.clone(), self.torre1.clone(), self.torre2.clone()
        if self.eh_valido(origem, destino):
            destino.push(origem.pop())
            novo: Estado = Hannoi(destino, aux, origem, 'Movendo torre 3 -> torre 1')
            
            if novo not in visitados:
                visitados.append(novo)
    
    def mover_torre_3_torre_2(self, visitados: list[Estado]):
        origem, destino, aux = self.torre3.clone(), self.torre2.clone(), self.torre1.clone()
        if self.eh_valido(origem, destino):
            origem, destino, aux = origem.clone(), destino.clone(), aux.clone()
            destino.push(origem.pop())
            novo: Estado = Hannoi(aux, destino, origem, 'Movendo torre 3 -> torre 2')
            
            if novo not in visitados:
                visitados.append(novo)


if __name__ == "__main__":
    discos = 3
    torre1 = Stack()
    torre2 = Stack()
    torre3 = Stack()
    for i in range(discos, 0, -1):
        torre1.push(i)
    estado = Hannoi(torre1, torre2, torre3, 'inicio')
    print(estado)

    console_show = MostraStatusConsole()

    node = BuscaProfundidade().busca(estado)
    if node is None:
        print('Não foi possivel encontrar uma solução')
    else:    
        while node is not None:
            print(node)
            node = node.pai
        print('Fim')
