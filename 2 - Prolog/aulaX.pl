escreverD(N) :- N == 0,!.
escreverD(N) :- writeln(N),
                R is N - 1,
                escreverD(R).

escreverC(N) :- N == 0,!.
escreverC(N) :- R is N - 1,
                escreverC(R),
                writeln(N).

factorial(N,F) :-  N == 0, F is 1,!.                
factorial(N,F) :-
                    N > 0,
                    N1 is N - 1,
                    factorial(N1,F1),
                    F is N * F1.



notas(alex,8,3).
notas(carla,9,5).
notas(beto,7,7).
notas(sissa,4,2).
notas(fernanda,9,8).

status(P,Resposta) :- notas(P,N1,N2),
                      Media is (N1 + N2)/2,
                      Media >= 7,
                      Resposta = 'Aprovado',!.

status(P,Resposta) :- notas(P,N1,N2),
                      Media is (N1 + N2)/2,
                      Media < 3,
                      Resposta = 'Reprovado',!.

status(P,Resposta) :- notas(P,N1,N2),
                      Media is (N1 + N2)/2,
                      Media >= 3,
                      Media < 7,
                      Resposta = 'Em exame',!.

nasceuEm('Alexandre','Cruz Alta').
nasceuEm('Reiner','Sombrio').
nasceuEm('Luiz','Itaqui').
nasceuEm('Pierre','Sao Borja').
nasceuEm('Aloisio','Paris').
nasceuEm(P,Lugar):- localizadoEm(OutroLugar, Lugar),
                    nasceuEm(P,OutroLugar).
                     
localizadoEm('Cruz Alta','RS').
localizadoEm('Itaqui','RS').
localizadoEm('Sao Borja','RS').
localizadoEm('RS','Brasil').
localizadoEm('Brasil','America').
localizadoEm('Sombrio','SC').
localizadoEm('SC','Brasil').
localizadoEm('Paris','Franca').
localizadoEm('Franca','Europa').

estaEm(Lugar,OutroLugar) :- localizadoEm(Lugar,OutroLugar).%mecanismo de parada da recursão
estaEm(Lugar,OutroLugar) :- localizadoEm(Lugar, I),
                            estaEm(I,OutroLugar).%mescanismo de recursão

pai(zeno,jurandir).
pai(jurandir,alex).
pai(alex,dante).
ascendente(A,D) :- pai(A,D).
ascendente(A,D) :- pai(A,I),
                   ascendente(I,D).




%TAREFA. A partir dos códigos do factorial e do arco e caminho, 
%incrementar a regra caminho para ela exibir o custo total de 
%diferentes caminhos entre 'a' e 'e'.

arco(a,b,100).
arco(a,e, 400).
arco(b,c,50).
arco(c,d,60).
arco(d,e,50).
caminho(O,D,Custo) :- arco(O,D,Custo). %criterio de parada
caminho(O,D,Custo) :- arco(O,I,Custo1),
                      caminho(I,D,Custo2),
                      Custo is Custo1 + Custo2.
                      
%a b c d e = 100+50+60+50
%a e = 400

eh_um(frajola,gato).
eh_um(piupiu,passaro).
eh_um(goldie,peixe).
eh_um(squiggly,minhoca).

gosta(passaro,minhoca).
gosta(gato,peixe).
gosta(gato,passaro).

eh_dona(joana,frajola).

amigo(A,B) :- gosta(A,B),
              gosta(B,A),
              A \== B.

amigo(frajola,joana).


come(gato,Tudo) :- gosta(gato,Tudo).

comeFrajola(X) :- eh_um(frajola,Bicho),
                  gosta(Bicho,OutroBicho),
                  eh_um(X, OutroBicho).

comeFrajola(X) :- eh_um(frajola,Bicho),
                  gosta(Bicho,X).
                  
