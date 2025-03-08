from abc import ABC, abstractmethod
from .estado import Estado

class Aleatorio(ABC):
    @abstractmethod
    def gera_aleatorio(self) -> Estado:
        """
        Gera um estado aleatório.
        """
        pass