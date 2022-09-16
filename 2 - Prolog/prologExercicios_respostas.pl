%Escrever um contador que vai de 0 até 10 e escreve FIM na tela.

escrever(Numero) :- Numero == -1, !.
escrever(Numero) :- R is Numero - 1,  
               escrever(R),
               writeln(Numero).


escreverD(Numero) :- Numero == -1, !.
escreverD(Numero) :- writeln(Numero),
                     R is Numero - 1,
                     escreverD(R).

%As cidades de Santa Maria e Porto Alegre estão localizadas no Rio Grande do Sul. Salvador na Bahia. 
%Grenoble e Paris na França, que está na Europa. O Rio Grande do Sul e Bahia estão localizados no Brasil.
%O Andre nasceu em Santa Maria. José em Salvador. Marc nasceu na cidade de Grenoble e a Maria em Porto Alegre.
%Atualmente, André mora em Paris, José em Grenoble, Marc em Porto Alegre, Maria em Salvador.
%As idades são as seguintes: André - 25; José - 30; Marc - 28; e Maria - 32 anos.

nasceu('Andre', 'Santa Maria').
nasceu('Jose', 'Salvador').
nasceu('Marc', 'Grenoble').
nasceu('Maria', 'Porto Alegre').
nasceu(P,L) :-
    nasceu(P,I),
    localizadaEm(I,L).

localizadaEm('Santa Maria', 'RS').
localizadaEm('Porto Alegre', 'RS').
localizadaEm('Salvador', 'BA').
localizadaEm('Grenoble', 'França').
localizadaEm('Paris', 'França').
localizadaEm('França', 'Europa').
localizadaEm('RS', 'Brasil').
localizadaEm('BA', 'Brasil').
localizadaEm('Brasil', 'America do Sul').
localizadaEm(Lugar, OutroLugar) :- localizadaEm(Lugar, I),
                                   localizadaEm(I, OutroLugar).
    
moraEm('Andre','Paris').
moraEm('Jose', 'Grenoble').
moraEm('Marc', 'Porto Alegre').
moraEm('Maria', 'Salvador').
moraEm(P,L) :-
    moraEm(P,I),
    localizadaEm(I,L).

idade('Andre', 25).
idade('Jose', 30).
idade('Marc', 28).
idade('Maria', 32).

nota(joao, 5, 3). %4 - reprovado
nota(maria, 6, 6). %6 - recuperacao
nota(joana, 8, 2). %5 - recuperacao
nota(mariana, 9, 10). %9.5 - aprovado 
nota(cleuza, 10, 0). %5 - recuperacao
nota(jose, 7, 4). %5.5 - recuperacao 
nota(jaoquim, 6, 4). %5 - recuperacao

status(Aluno, Resultado) :- nota(Aluno, N1, N2),
                            Media is (N1+N2)/2,
                            Media >= 7,
                            Resultado = 'Aprovado'.

status(Aluno, Resultado) :- nota(Aluno, N1, N2),
                            Media is (N1+N2)/2,
                            Media < 5,
                            Resultado = 'Reprovado'.

status(Aluno, Resultado) :- nota(Aluno, N1, N2),
                            Media is (N1+N2)/2,
                            Media >= 5,
                            Media < 7,
                            Resultado = 'Recuperacao'.


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

persongagem(uruk, orc, rei, mordor).
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

aliado(al1(Nome1, Raca1), al2(Nome2, Raca2))) :- 
        lingua(Nome1, Lingua),
        lingua(Nome2, Lingua),
        personagem(Nome1, Raca1, _, _),
        personagem(Nome2, Raca2, _, _),
        Nome1 \== Nome2.

quantoVive(P, T) :- personagem(P, Raca, Categoria, _),
                    raca(Raca, T),
                    Categoria \== mago.

quantoVive(P, T) :- personagem(P, Raca, Categoria, _),
                    raca(Raca, Tempo),
                    T is Tempo + 100.
                    



