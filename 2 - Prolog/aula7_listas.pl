listaCores([azul, verde, amarelo]).

listaGenerica(['Fulano', 35, 'Alex', 0.6, 0.7, azul]).

listaCasas([casa(1, azul), casa(2, verde)]).

idades([[andre,25], [jose,30], [pedro,45], [sofia,13], [jose,67]]).

alunos([luiz,aloisio,bruno,iago,pierre,vini]).


%primeiro(Lista, Resposta).
primeiro([P | _], P).

%ultimo(Lista, Resposta).
ultimo([U], U).
ultimo([_ | R], U) :- ultimo(R, U).

distancias([450,90,110,55]).