from abc import ABC, abstractmethod
from typing import List

class Estado(ABC):
    @abstractmethod
    def get_descricao(self) -> str:
        """
        Retorna uma descrição do problema que esta representação de estado resolve.
        """
        pass

    @abstractmethod
    def eh_meta(self) -> bool:
        """
        Verifica se o estado é meta.
        """
        pass

    @abstractmethod
    def custo(self) -> int:
        """
        Custo para geração deste estado (não é o custo acumulado --- g).
        """
        pass

    @abstractmethod
    def sucessores(self) -> List['Estado']:
        """
        Gera uma lista de sucessores do nodo.
        """
        pass