from typing import List
from app.core.busca import BuscaIterativo, BuscaLargura, BuscaProfundidade, Estado


class EstadoJarros(Estado):
    def __init__(self, j4: int, j3: int, op: str):
        self.j4 = j4
        self.j3 = j3
        self.op = op

    def get_descricao(self) -> str:
        return (
            "Neste problema existem dois jarros, um com capacidade\n"
            "para 4 litros e outro com capacidade para 3 litros.\n"
            "Pode-se encher os jarros, esvazia-los ou derramar a agua\n"
            "de um deles no outro.\n"
            "O objetivo e deixar o jarro de 3l com 2l de agua.\n"
        )

    def eh_meta(self) -> bool:
        return self.j4 == 0 and self.j3 == 2

    def __eq__(self, other) -> bool:
        if isinstance(other, EstadoJarros):
            return self.j4 == other.j4 and self.j3 == other.j3
        return False

    def __hash__(self) -> int:
        return hash((self.j4, self.j3))

    def sucessores(self) -> List[Estado]:
        suc = []

        # encher o jarro de 4 litros
        if self.j4 < 4:
            suc.append(EstadoJarros(4, self.j3, "encher(4)"))

        # encher o jarro de 3 litros
        if self.j3 < 3:
            suc.append(EstadoJarros(self.j4, 3, "encher(3)"))

        # esvaziar o jarro de 4 litros
        if self.j4 > 0:
            suc.append(EstadoJarros(0, self.j3, "esvaziar(4)"))

        # esvaziar o jarro de 3 litros
        if self.j3 > 0:
            suc.append(EstadoJarros(self.j4, 0, "esvaziar(3)"))

        # jogar agua do jarro de 4 litros no de 3l
        folga_j3 = 3 - self.j3
        if self.j4 > 0 and folga_j3 > 0:
            qdt_derramada = min(folga_j3, self.j4)
            fica_j4 = self.j4 - qdt_derramada
            fica_j3 = self.j3 + qdt_derramada
            suc.append(EstadoJarros(fica_j4, fica_j3, "jogar(4,3)"))

        # jogar agua do jarro de 3l no de 4l
        folga_j4 = 4 - self.j4
        if self.j3 > 0 and folga_j4 > 0:
            qdt_derramada = min(folga_j4, self.j3)
            fica_j4 = self.j4 + qdt_derramada
            fica_j3 = self.j3 - qdt_derramada
            suc.append(EstadoJarros(fica_j4, fica_j3, "jogar(3,4)"))

        return suc

    def __str__(self) -> str:
        return f"\n({self.j4},{self.j3}) - {self.op}"

    def custo(self) -> int:
        return 1

    @staticmethod
    def main():
        inicial = EstadoJarros(0, 0, "inicial")

        # chama busca em largura
        print("busca em largura")
        n = BuscaLargura().busca(inicial)
        if n is None:
            print("sem solucao!")
        else:
            print(f"solucao:\n{n.monta_caminho()}\n\n")

        # chama busca em profundidade
        print("busca em profundidade")
        n = BuscaProfundidade(15).busca(inicial)
        if n is None:
            print("sem solucao!")
        else:
            print(f"solucao:\n{n.monta_caminho()}\n\n")

        # chama busca em profundidade iterativo
        print("busca em profundidade iterativo")
        n = BuscaIterativo().busca(inicial)
        if n is None:
            print("sem solucao!")
        else:
            print(f"solucao:\n{n.monta_caminho()}\n\n")


if __name__ == "__main__":
    inicial = EstadoJarros(0, 0, "inicial")

    # chama busca em largura
    print("busca em largura")
    n = BuscaLargura().busca(inicial)
    if n is None:
        print("sem solucao!")
    else:
        print(f"solucao:\n{n.monta_caminho()}\n\n")
    
    # chama busca em profundidade
    print("busca em profundidade")
    n = BuscaProfundidade(15).busca(inicial)
    if n is None:
        print("sem solucao!")
    else:
        print(f"solucao:\n{n.monta_caminho()}\n\n")
    
    # chama busca em profundidade iterativo
    print("busca em profundidade iterativo")
    n = BuscaIterativo().busca(inicial)
    if n is None:
        print("sem solucao!")
    else:
        print(f"solucao:\n{n.monta_caminho()}\n\n")
