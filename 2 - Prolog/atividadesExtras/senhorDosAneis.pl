localizado(norte,"Terra Media").
localizado(sul,"Terra Media").
localizado(centro-oeste,"Terra Media").
localizado(leste,"Terra Media").
localizado(oeste,"Terra Media").
localizado(condado, leste).
localizado(valfenda, centro-oeste).
localizado(mordor, norte).
localizado(sul,"Terra Media").
localizado(rohan, oeste).
localizado(bolsao, condado).

%raca(nomeRaca, tempoVidaMedio)
raca(orc, 110).
raca(elfo, 2000).
raca(anao, 500).
raca(humano, 90).
raca(hobbit, 100).

personagem(uruk, orc, rei, mordor).
personagem(bilbo, hobbit, ladrao, condado).
personagem(frodo, hobbit, _, condado).
personagem(gimmly, anao, guerreiro, _).
personagem(gandalf, humano, mago, valfenda).
personagem(saruman, elfo, mago, _).
personagem(legolas, elfo, guerreiro, valfenda).
personagem(boromir, humano, guerreiro, roahan).

nasceu(P, Lugar) :- personagem(P, _, _, Lugar).
nasceu(P, Lugar) :- personagem(P, _, _, I),
                    localizado(I, Lugar).

lingua(uruk, orcs).
lingua(bilbo, portugues).
lingua(frodo, portugues).
lingua(gandalf, elfico).
lingua(gandal, portugues).
lingua(boromir, portugues).
lingua(legolas, elfico).
lingua(legolas, portugues).
lingua(saruman, portugues).
lingua(saruman, elfico).
lingua(gimmly, portugues).

aliado(al1(Nome1, Raca1), al2(Nome2, Raca2)) :- 
        lingua(Nome1, Lingua),
        lingua(Nome2, Lingua),
        personagem(Nome1, Raca1, _, _),
        personagem(Nome2, Raca2, _, _),
        Nome1 \== Nome2.

quantoVive(P, T) :- personagem(P, Raca, Categoria, _),
                    raca(Raca, T),
                    Categoria \== mago.

quantoVive(P, T) :- personagem(P, Raca, _, _),
                    raca(Raca, Tempo),
                    T is Tempo + 100.