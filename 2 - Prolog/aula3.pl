%--Nome � pai de Nome

epai(zeno,jurandir).
epai(jurandir,alex).
epai(jurandir,gustavo).
epai(jurandir,tina).
epai(jurandir,sissa).
epai(alex,dante).
epai(alex,tales).

%--Nome � mae de Nome

emae(regina,alex).
emae(regina,tina).
emae(regina,sissa).
emae(regina,gustavo).
emae(tina,guilherme).
emae(tina,gabriela).

%--Rela�oes

pais(Filho,Pai,Mae) :-  emae(Mae,Filho),
                        epai(Pai,Filho).

avo(A,N):- epai(A,P),
           epai(P,N).

avo(A,N):- epai(A,P),
           emae(P,N).

avo(A,N):- emae(A,P),
           epai(P,N).           

avo(A,N):- emae(A,P),
           emae(P,N).


irmao(A,B):- emae(M,A),
             emae(M,B), 
             A \== B.            

irmao(A,B):- epai(P,A),
             epai(P,B), 
             A \== B.

progenitor(I,F):- epai(I,F).
progenitor(I,F):- emae(I,F).

antepassado(A,D):- progenitor(A,D). %ponto de parada da recursão
antepassado(A,D):- progenitor(A,I),
                   antepassado(I,D).




