# Inteligência Artificial
    - Técnicas para construção de Sistemas de Comportamento Inteligente
        - base de conhecimento: fatos ou experiências
        - raciocínio automatizado: dedução e indução
        - aprendizagem de máquina: treinamento ou repetição de amostras - reconhecimento de padrões
    - Resolve alguns problemas:
        A) problemas em que se quer os passos até um estado final (conhecido ou não)
        B) diagnóstico - reconhecer padrões

    - Exemplos:
        1) Qual o melhor caminho de um pacote em uma rede de dados cabeada com fibra ótica?
            - A
        2) Saindo da pizzaria e entregando n pizzas em um tempo mínimo.
            - A
        3) Um programa de computador que jogue damas contra um ser humano.
            - A
        4) Uma função que carregue foto, obrigatoriamente, de somente um rosto de uma pessoa.
            - B
                - Desafio:
                    1) fazer a máquina reconhecer pessoas
                        - treinamento:    
                            - imagens positivas: imagens de pessoas
                                - há pacotes/bibliotecas com funções que reconhecem pessoas
                            - imagens negativas: sem imagens de pessoas
                            - imagens sobrepostas: 

                    2) fazer a máquina reconhecer somente um rosto
                        - treinamento:
                            - imagens positivas: imagens de somente um rosto
                                - há pacotes/bibliotecas com funções que reconhecem um rosto
                            - imagens negativas: sem imagens de um rosto somente
                            - imagens sobrepostas: 
        5) A partir da foto de uma folha de uma planta, informar se a planta está ou não doente
            - B
                - Desafio
                    - juntar imagens de n tipos de plantas
                    - para cada tipo de planta, trazer imagens de doentes e não doentes
        
# Resolução de problemas por métodos de busca
    - Técnica de IA para encontrar um conjunto de passos a um estado final (conhecido ou não)
        - modelagem: estados, regras de transição, restrições, visitados, função meta
        - estratégias para escolher as regras de transição
            - MÉTODOS DE BUSCA
                - cegos ou de força bruta: 
                    - quando aplicar:
                        - não se tem informção privilegiada (heurística)
                        - há hardware poderoso (sobrando)
                        - há pouca restrição
                    - largura ou amplitude
                        - fila
                    - profundidade
                        - pilha (recursiva)
                - informados ou heurísticos
                    - o que é heurística: informação privilegiada para 
                     aplicar uma ou outra regra de transição
                    - quando aplicar:
                        - quando se tem dica ou heurística
                            - como saber se a heurística é admissível (boa):
                                - veio de um especialista
                                - foi calculada/gerada pelo computador
                        - quando se tem pouco hardware
                        - quando se tem muitas restrições
                    - o que seria uma dica ou uma heurística?
                        - custo:
                            - real
                            - estimado - aqui pode haver a falha ou inadimissão
                    - quais são os principais métodos
                        - Subida de Encosta (Climb Hill)
                            - profundidade
                            - custo real - g(n)
                        - Guloso
                            - amplitude
                            - custo estimado - h(n)
                        - A*
                            - amplitude
                            - mistura custo real com custo estimado (correção)
                        
                        
