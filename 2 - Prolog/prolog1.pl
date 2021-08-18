%isso é um comentário
%código fonte prolog contém: fatos e regras (BASE DE CONHECIMENTO)

%fatos são sentenças verdadeiras
%regras são 'funções' que testam hipóteses

%fato ou regra são chamados de: sentenças, proposições, PREDICADO que lembram uma função com parâmetro

%exemplo de fatos com 1 parâmetro
homem(alexandre).
homem(amilton).

mulher(regina).

gato(miau).

cachorro(bilbo).

%exemplo de regras
serHumano(P) :-
	homem(P).

serHumano(P) :-
	mulher(P).

animal(A) :-
	gato(A).

animal(A) :-
	cachorro(A).


%exemplo de fatos com N parâmetros
gosto(alexandre,carne).
gosto(alexandre,pizza).

linha(347,centro,pucrs).

aluno("Douglas", 20, "Ciência Computação", "Trabalha", "Sem Filhos").
aluno("Samir", 24, "Sistemas de Informação", "Trabalha", "Com Filhos").

%exemplo minha família - predicado progenitor(progenitor, filho)
progenitor("Zeno","Jurandir").
progenitor("Jurandir","Alexandre").
progenitor("Jurandir","Cristina").
progenitor("Jurandir","Clarissa").
progenitor("Jurandir","Gustavo").
progenitor("Alexandre","Dante").
progenitor("Alexandre","Tales").
progenitor("Clarissa","Clara").

%exemplo de regras
avo(A,N) :-
	progenitor(A, I),
	progenitor(I, N).

irmaos(A,B) :-
	progenitor(P,A),
	progenitor(P,B),
	A \== B.

tio(T,S) :-
	irmaos(T,I),
	progenitor(I,S),
	T \== S.

descendente(D, A) :- progenitor(A, D).
descendente(D, A) :-
	progenitor(I, D),
	descendente(I, A).


