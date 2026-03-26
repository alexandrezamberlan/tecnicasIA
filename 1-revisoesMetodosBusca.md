Problemas que a IA resolve
    - Problemas de diagnóstico -> reconhecer padrões
    - Problemas em que não se sabe como chegar no estado final -> 'empacotamento'

Técnicas de IA geram ou constroem SISTEMAS DE COMPORTAMENTO INTELIGENTE
    - Base de conhecimento
    - Motores de raciocínio -> DEDUÇÃO e INDUÇÃO
        - algoritmos de busca
            - cega ou força bruta
                - profundidade -> PILHA
                - largura ou amplitude - FILA
            
            - informados ou heurísticos
                - CUSTOS
                    - real g(n)
                    - estimado ou heurístico h(n) -> ADMISSÍVEL OU INADIMISSÍVEL
                - Métodos
                    - Subida de Encosta -> baseado no profundidade e foca no custo real
                    - Guloso -> baseado no amplitude e foca no custo estimado
                    - A* -> baseado no amplitude e soma custo real (acumulado) com custo estimado -> com isso, pode corrigir a heurísitca
                        - a heurística é ESTÁTICA

Métodos de Busca
    - SOLUÇÕES PARA PROBLEMAS
        - Gerar ou atingir Estado(s) desejado(s)
        - Gerar ou produzir passo-a-passo até o Estado(s) desejado(s)

    