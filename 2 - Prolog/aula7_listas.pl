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


adiciona(N,Lista):- adiciona(N,[],Lista).

adiciona(0,Acumulador,Acumulador).
adiciona(N,Acumulador,Resultado) :-
    N > 0,
    N1 is N - 1,
    adiciona(N1,[N|Acumulador],Resultado).

idades([ [andre,25], [jose,30] ]).
casas([casa(1, azul), casa(2, verde)]).

alunos([luiz,paulo,leo,alex]).
distancias([120,34,100,67,12]).


primeiro([P | _], P).

ultimo([U], U).
ultimo([_ | R], U) :- ultimo(R, U).

disciplinas([ia,ed,po,redes,so,modelagem]).

percorre([U]):- writeln(U).
percorre([P | R]) :- 
    writeln(P),
    percorre(R).

FOCO NESTE ESTUDO
- senteças ou cláusulas declarativas
- predicados que sao fatos
- predicados que são regras
- variáveis e o uso no parâmetro para receber respostas
- recursao
