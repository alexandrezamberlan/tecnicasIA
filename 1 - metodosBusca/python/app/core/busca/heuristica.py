from abc import ABC, abstractmethod

class Heuristica(ABC):
    @abstractmethod
    def h(self) -> int:
        """
        Estimativa de custo.
        """
        pass

# custo real -> g(n)
# custo heurístico -> g(h)