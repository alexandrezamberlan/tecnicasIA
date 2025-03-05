# INTELIGÊNCIA ARTIFICIAL
    ## Técnicas para construção de Sistemas de Comportamento Inteligente (SCI)
        - BASE DE CONHECIMENTO
            - fatos e experiências
        - RACIOCÍNIO AUTOMATIZADO
            - dedutivo -> genérico ao específico
        - APRENDIZADO DE MÁQUINA
            - reconhecimento de padrões -> treino ou seja, repetição de amostras

    ## Categorias de problemas
        - A - diagnóstico
            - SCI
        - B - passos a executar (empacotamento)
            - força bruta
    
    ## Técnicas clássicas
        - métodos de busca - B
        - representação do conhecimento - A e B
        - sistemas multiagentes - A e B
        - redes neurais - A
        
# Projetando soluções para problemas de 'empacotamento' - quais os passos até o estado final
    - modelar alguns elementos
        - Estados possíveis da solução
            - mapear classe e seus atributos
            - mapear o estado inicial e o(s) estado(s) final(ais)
        - Regras de transição - métodos
            -  mapear dentro da classe os métodos
        - Restrições -> método ehValido()
        - Visitados ou gerados
        - Meta ou objetivo

# Problemas de descoberta de passo-a-passo (empacotamento)
A) Num rio, com duas margens, há um homem, lobo, alface e um carneiro. Na beirada do rio há um barco em que é pilotado obrigatoriamente pelo homem. O objetivo é atravessar todos os integrantes da margem, da esquerda para direita. Sempre com o homem pilotando o barco e com no máximo dois integrantes no barco. Porém, não é possível deixar sozinhos na margem o lobo e o carneio; e o carneiro e a alface.
Qual os estados, regras de transição, restrições, visitados e função objetivo para este problema.

1) Estados
    - char homem - 'e' ou 'd'
    - char lobo - 'e' ou 'd'
    - char carneiro - 'e' ou 'd'
    - char alface - 'e' ou 'd'

    - Estado inicial:
        - homem = 'e'
        - lobo = 'e'
        - carneiro = 'e'
        - alface = 'e'

    - Estado final
        - homem = 'd'
        - lobo = 'd'
        - carneiro = 'd'
        - alface = 'd'

2) Regras de transição - métodos
    - levarNada()
    - levarLobo()
    - levarCarneiro()
    - levarAlface()

3) ehValido() {
    if (homem != lobo E lobo == carneiro) OU (homem != carneiro E carneiro == alface)
    
        retorna falso
    
    retorna verdadeiro

}         

4) visitados - lista, arvore ou um hashset
    - concatenar homem + lobo + carneiro + alface
    - Ex.: "eeee" ...... "dddd"
  

5) função objetivo
 
    ehMeta() {
 
        if homem == 'd' E lobo == 'd' E carneiro == 'd' E alface == 'd'
 
            retorna verdadeiro
 
        retorna falso
 
    }
        
B) Há uma torneira que jorra agua infinitamente. Há também duas jarras: jarra1 com capacidade de 4 litros e jarra2 com capacidade de 3 litros. As operações nas jarras com a torneira são:
    - encher jarra1
    - encher jarra2
    - esvaziar jarra1
    - esvaziar jarra2
    - despejar jarra1Emjarra2
    - depejar jarra2Emjarra1

 O objetivo, via essas operações é chegar com 2 litros em uma das jarras. Qual os estados, regras de transição, restrições, visitados e função objetivo para este problema.

 1) Estados 
    - int jarra1 -> 0 a 4
    - int jarra2 -> 0 a 3

    - Estado inicial  
        - jarra1 = 0
        - jarra2 = 0
    - Estado final
        - (jarra1 == 2 E jarra2 == 0) OU (jarra1 == 0 E jarra2 == 2)

2) Regras de transição - métodos
    - encher jarra1
    - encher jarra2
    - esvaziar jarra1
    - esvaziar jarra2
    - despejar jarra1Emjarra2
    - depejar jarra2Emjarra1

3) Restrições -> não há

4) visitados - lista, arvore ou um hashset
    - concatenar jarra1 + jarra2
    - jarra1 = 4
    - jarra2 = 3
    - "43"

5) função objetivo

ehMeta() {

    if jarra1 == 2 E jarra2 == 0 OU jarra1 == 0 E jarra2 == 2
 
        retorna verdadeiro
 
    retorna falso

}

c) Problema das torres de hannoi

d) Problema dos Missionários e Canibais

Há 3 missionários e 3 canibais. Há também um barco que vai da margem esquerda para a margem direita e vice-versa, sempre levando um ou duas pessoas. Todas as pessoas estão na margem esquerda e precisam ir para a margem direita. Porém, há restrições: em momento algum pode ficar mais canibais do que missionários em uma das margens.


e) Problema da travesia da ponte

Há 4 pessoas: cientista, zelador, professora e aluno. Que precisam passar pela ponte.

Cada um tem um tempo de travessia:

    - aluno: leva 1 minuto para cruzar a ponte
    - professora: leva 2 minutos
    - zelador: 5 minutos
    - cientista: 10 minutos

O problema é qual a sequência de travessia para que o tempo total seja no máximo de 17 minutos.

Contudo, para cruzar a ponte é preciso usar uma lanterna e no máximo duas pessoas podem cruzar a ponte. Qualquer pessoa cruzando a ponte precisa segurar a lanterna.
