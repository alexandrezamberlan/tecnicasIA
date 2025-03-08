from app.core.busca.busca_bidirecional import BuscaBidirecional
from app.core.busca.busca_iterativo import BuscaIterativo
from app.core.busca.busca_largura import BuscaLargura
from app.core.busca.busca_profundidade import BuscaProfundidade
from app.core.busca.estado import Estado
from app.core.busca.antecessor import Antecessor

class MissionarioCanibal(Estado, Antecessor):
    def __init__(self, missionario, canibal, barco, op):
        self.missionario = missionario
        self.canibal = canibal
        self.barco = barco
        self.op = op
    
    def custo(self):
        return 1

    def eh_meta(self):
        return self.missionario == 0 and self.canibal == 0 and self.barco == 'd'
    
    def sucessores(self) -> list['Estado']:
        suc = []  # Lista de sucessores
        # Levar um missionario
        self.levar1m(suc)
        # Levar dois missionarios
        self.levar2m(suc)
        # Levar 1 missionario e 1 canibal
        self.levar1m1c(suc)
        # Levar 1 canibal
        self.levar1c(suc)
        # Levar 2 canibais
        self.levar2c(suc)
        
        # Retornar a lista de Sucessores
        return suc
    
    def antecessores(self) -> list['Estado']:
        return self.sucessores()
    
    def get_descricao(self):
        return (
            "Tres missionarios e tres canibais estao a beira de um rio e dispoem de\n"
            "um barco com capacidade para apenas duas pessoas. O problema e determinar\n"
            "as tripulacoes de uma serie de travessias de maneira que todo o grupo passe\n"
            "para o outro lado do rio, respeitada a condicao de que em momento algum os\n"
            "canibais sejam mais numerosos do que os missionarios em uma das margens do rio.\n\n"
            "Implementacao de Malcus Otavio Quinoto Imhof & Daniel Dalcastagne - 5. Semestre Matutino - BCC\n\n"
        )

    def __str__(self):
        return f"{self.missionario},{self.canibal},{self.barco} ({self.op})"
    

    def __eq__(self, o: object) -> bool:
        if not isinstance(o, MissionarioCanibal):
            return False
        return self.missionario == o.missionario and self.canibal == o.canibal and self.barco == o.barco
    
    def __hash__(self) -> int:
        return hash((self.missionario, self.canibal, self.barco))

    def eh_valido(self):
        if self.missionario < self.canibal and self.missionario != 0:
            return False
        if 3 - self.missionario < 3 - self.canibal and 3 - self.missionario != 0:
            return False
        return True
    
    # Movimenta um missionario de uma margem a outra
    def levar1m(self, suc):
        # Se o barco estiver do lado esquerdo e houver pelo menos 1 missionário nesse lado
        if self.barco == 'e' and self.missionario > 0:
            # Gera um sucessor
            novo = MissionarioCanibal(self.missionario - 1, self.canibal, 'd', "Levar 1 missionário para margem direita")
            # Verifica se o sucessor gerado é válido
            if novo.eh_valido():
                # Se for válido, adiciona na lista de sucessores
                suc.append(novo)
        else:
            # Se o barco estiver do lado direito e houver pelo menos 1 missionário neste lado
            if self.barco == 'd' and self.missionario < 3:
                # Gerar o sucessor
                novo = MissionarioCanibal(self.missionario + 1, self.canibal, 'e', "Levar 1 missionário para margem esquerda")
                # Verificar se ele é válido
                if novo.eh_valido():
                    # Adicionar na lista de sucessores
                    suc.append(novo)

    # Movimenta 2 missionarios de uma margem a outra
    def levar2m(self, suc):
        # Se o barco estiver do lado esquerdo e houver pelo menos 2 missionários deste lado
        if self.barco == 'e' and self.missionario > 1:
            # Gerar o sucessor
            novo = MissionarioCanibal(self.missionario - 2, self.canibal, 'd', "Levar 2 missionários para margem direita")
            # Verificar se ele é válido
            if novo.eh_valido():
                # Adicionar na lista de sucessores
                suc.append(novo)
        else:
            # Se o barco estiver do lado direito e houver dois missionários deste lado
            if self.barco == 'd' and self.missionario < 2:
                # Gerar sucessor
                novo = MissionarioCanibal(self.missionario + 2, self.canibal, 'e', "Levar 2 missionários para margem esquerda")
                # Verificar se ele é válido
                if novo.eh_valido():
                    # Adicionar na lista de sucessores
                    suc.append(novo)
    # Movimentar 1 missionário e 1 canibal
    def levar1m1c(self, suc):
        # Se o barco estiver do lado esquerdo e houver pelo menos um missionário e um canibal deste lado
        if self.barco == 'e' and self.missionario > 0 and self.canibal > 0:
            # Gerar sucessor
            novo = MissionarioCanibal(self.missionario - 1, self.canibal - 1, 'd', "Levar 1 missionário e 1 canibal para margem direita")
            # Verificar se ele é válido
            if novo.eh_valido():
                # Adicionar na lista de sucessores
                suc.append(novo)
        else:
            # Se o barco estiver do lado direito e houver pelo menos um missionário e um canibal deste lado
            if self.barco == 'd' and self.missionario < 3 and self.canibal < 3:
                # Gerar Sucessor
                novo = MissionarioCanibal(self.missionario + 1, self.canibal + 1, 'e', "Levar 1 missionário e 1 canibal para margem esquerda")
                # Verificar se ele é válido
                if novo.eh_valido():
                    # Adicionar na lista de sucessores
                    suc.append(novo)
    
    def levar1c(self, suc):
        # Se o barco estiver do lado esquerdo e houver pelo menos 1 canibal deste lado
        if self.barco == 'e' and self.canibal > 0:
            # Gerar Sucessor
            novo = MissionarioCanibal(self.missionario, self.canibal - 1, 'd', "Levar 1 canibal para margem direita")
            # Verificar se ele é válido
            if novo.eh_valido():
                # Adicionar na lista de sucessores
                suc.append(novo)
        else:
            # Se o barco estiver do lado direito e houver pelo menos 1 canibal deste lado
            if self.barco == 'd' and self.canibal < 3:
                # Gerar Sucessor
                novo = MissionarioCanibal(self.missionario, self.canibal + 1, 'e', "Levar 1 canibal para margem esquerda")
                # Verificar se ele é válido
                if novo.eh_valido():
                    # Adicionar na lista de sucessores
                    suc.append(novo)

    def levar2c(self, suc):
        # Se o barco estiver do lado esquerdo e houver pelo menos 2 canibais deste lado
        if self.barco == 'e' and self.canibal > 1:
            # Gerar Sucessor
            novo = MissionarioCanibal(self.missionario, self.canibal - 2, 'd', "Levar 2 canibais para margem direita")
            # Verificar se ele é válido
            if novo.eh_valido():
                # Adicionar na lista de sucessores
                suc.append(novo)
        else:
            # Se o barco estiver do lado direito e houver pelo menos 2 canibais deste lado
            if self.barco == 'd' and self.canibal < 2:
                # Gerar Sucessor
                novo = MissionarioCanibal(self.missionario, self.canibal + 2, 'e', "Levar 2 canibais para margem esquerda")
                # Verificar se ele é válido
                if novo.eh_valido():
                    # Adicionar na lista de sucessores
                    suc.append(novo)        


if __name__ == "__main__":
    inicial = MissionarioCanibal(3, 3, 'e', "")
    final = MissionarioCanibal(0, 0, 'd', "")

    while True:
        print("Digite sua opcao de busca { Digite S para finalizar }")
        print("\t1  -  Largura")
        print("\t2  -  Profundidade")
        print("\t3  -  Profundidade Iterativo")
        print("\t4  -  Bidirecional")
        opcao = input("Opcao: ").strip().upper()

        if opcao == "S":
            break

        n = None
        if opcao == "1":
            print("Busca em Largura")
            n = BuscaLargura().busca(inicial)
        elif opcao == "2":
            print("Busca em Profundidade")
            n = BuscaProfundidade(20).busca(inicial)
        elif opcao == "3":
            print("Busca em Profundidade Iterativo")
            n = BuscaIterativo().busca(inicial)
        elif opcao == "4":
            print("Busca Bidirecional")
            n = BuscaBidirecional().busca_with_meta(inicial=inicial, meta=final)
        else:
            print("Opcao invalida!")
            continue

        if n is None:
            print("Sem Solucao!")
        else:
            print("Solucao:\n")
            print(n.monta_caminho())
