from abc import ABC, abstractmethod
from typing import List
from .estado import Estado

class Antecessor(ABC):
    @abstractmethod
    def antecessores(self) -> List[Estado]:
        """
        Retorna uma lista de estados antecessores.
        """
        pass