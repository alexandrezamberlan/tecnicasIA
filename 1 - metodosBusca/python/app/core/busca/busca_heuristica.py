from abc import ABC
from functools import cmp_to_key
from app.core.busca.busca import Busca

class BuscaHeuristica(Busca, ABC):
    def __init__(self, mostra_status_console=None):
        super().__init__(mostra_status_console)

    def get_nodo_comparator_f(self):
        def comparator(no1, no2):
            try:
                f1 = no1.f()
                f2 = no2.f()
                if f1 > f2:
                    return 1
                elif f1 == f2:
                    return 0
                else:
                    return -1
            except Exception as e:
                print(e)
                return 0

        return cmp_to_key(comparator)

    # Uncomment and implement if needed
    # def get_estado_comparator_h(self):
    #     def comparator(eo1, eo2):
    #         try:
    #             h1 = eo1.h()
    #             h2 = eo2.h()
    #             if h1 > h2:
    #                 return 1
    #             elif h1 == h2:
    #                 return 0
    #             else:
    #                 return -1
    #         except Exception as e:
    #             print(e)
    #             return 0
    #
    #     return cmp_to_key(comparator)