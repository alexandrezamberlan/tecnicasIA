from typing import List
from app.core.busca import BuscaLargura, Estado

class HLAC(Estado):
    def __init__(self, homem: str, lobo: str, carneiro: str, alface: str, op: str):
        self.homem = homem
        self.lobo = lobo
        self.carneiro = carneiro
        self.alface = alface
        self.op = op

    def get_descricao(self) -> str:
        return (
            "Uma pessoa, um lobo, um carneiro e um cesto de alface estao a beira \n"
            "de um rio. Dispondo de um barco no qual pode carregar apenas um dos \n"
            "outros tres, a pessoa deve transportar tudo para a outra margem. \n"
            "Determine uma serie de travessias que respeite a seguinte condicao: \n"
            "em nenhum momento devem ser deixados juntos e sozinhos o lobo e o \n"
            "carneiro ou o carneiro e o cesto de alface. \n\n"
        )

    def eh_meta(self) -> bool:
        return self.homem == 'd' and self.lobo == 'd' and self.carneiro == 'd' and self.alface == 'd'

    def custo(self) -> int:
        return 1

    def sucessores(self) -> List[Estado]:
        suc = []
        self.levar_carneiro(suc)
        self.levar_lobo(suc)
        self.levar_alface(suc)
        self.levar_nada(suc)
        return suc

    def levar_carneiro(self, suc: List[Estado]):
        if self.carneiro == self.homem:
            nova_margem = self.lado_oposto(self.homem)
            novo = HLAC(nova_margem, self.lobo, nova_margem, self.alface, f"levarC-{self.carneiro}{nova_margem}")
            if novo.eh_valido():
                suc.append(novo)

    def levar_lobo(self, suc: List[Estado]):
        if self.lobo == self.homem:
            nova_margem = self.lado_oposto(self.homem)
            novo = HLAC(nova_margem, nova_margem, self.carneiro, self.alface, f"levarL-{self.lobo}{nova_margem}")
            if novo.eh_valido():
                suc.append(novo)

    def levar_alface(self, suc: List[Estado]):
        if self.alface == self.homem:
            nova_margem = self.lado_oposto(self.homem)
            novo = HLAC(nova_margem, self.lobo, self.carneiro, nova_margem, f"levarA-{self.alface}{nova_margem}")
            if novo.eh_valido():
                suc.append(novo)

    def levar_nada(self, suc: List[Estado]):
        nova_margem = self.lado_oposto(self.homem)
        novo = HLAC(nova_margem, self.lobo, self.carneiro, self.alface, f"levarN-{self.homem}{nova_margem}")
        if novo.eh_valido():
            suc.append(novo)

    def lado_oposto(self, l: str) -> str:
        return 'd' if l == 'e' else 'e'

    def eh_valido(self) -> bool:
        if self.lobo == self.carneiro and self.lobo != self.homem:
            return False
        if self.carneiro == self.alface and self.carneiro != self.homem:
            return False
        return True

    def __eq__(self, other) -> bool:
        if isinstance(other, HLAC):
            return (
                self.homem == other.homem and
                self.lobo == other.lobo and
                self.carneiro == other.carneiro and
                self.alface == other.alface
            )
        return False

    def __hash__(self) -> int:
        return hash(f"{self.homem}{self.lobo}{self.carneiro}{self.alface}")

    def __str__(self) -> str:
        dir = ""
        esq = ""
        if self.homem == 'd':
            dir += 'h'
        else:
            esq += 'h'
        if self.lobo == 'd':
            dir += 'l'
        else:
            esq += 'l'
        if self.carneiro == 'd':
            dir += 'c'
        else:
            esq += 'c'
        if self.alface == 'd':
            dir += 'a'
        else:
            esq += 'a'
        return f"{esq}|{dir} ({self.op})\n"

if __name__ == "__main__":
    inicial = HLAC('e', 'e', 'e', 'e', "inicial")
    print("busca em largura")
    n = BuscaLargura().busca(inicial)
    if n is None:
        print("sem solucao!")
    else:
        print(f"solucao:\n{n.monta_caminho()}\n\n")