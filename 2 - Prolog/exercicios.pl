masculino(joao).
masculino(carlos).
masculino(pedro).
feminino(claudia).
feminino(cecilia).

not(gosta(joao, carlos)).
gosta(claudia,joao).
gosta(joao, claudia).
gosta(joao, cecilia).
gosta(joao, pedro).
gosta(claudia,pedro).
gosta(pedro, joao).

namorados(A,B) :- masculino(A),
                  feminino(B),
                  gosta(A,B),
                  gosta(B,A).

namorados(A,B) :- feminino(A),
                  masculino(B),
                  gosta(A,B),
                  gosta(B,A).

amigos(A,B) :- gosta(A,B),
               gosta(B,A).

inimigos(A,B) :- not(gosta(A,B)),
                 not(gosta(B,A)).


%!  %%%%%%%%

aluno(lucas, si).
aluno(marlon, si).
aluno(douglas, cc).

turma(ia, lucas, 8, 9).
turma(ia, marlon, 10, 3).
turma(ia, douglas, 9, 3).
turma(sd, douglas, 10, 8).
turma(sd, marlon, 9, 9).

status(Aluno, Disciplina, Media, Curso, Situacao) :- turma(Disciplina, Aluno, N1, N2),
                                                     aluno(Aluno, Curso),
                                                     Media is (N1 + N2)/2,
                                                     Media >= 7,
                                                     Situacao = 'Aprovado'.



status(Aluno, Disciplina, Media, Curso, Situacao) :- turma(Disciplina, Aluno, N1, N2),
                                                     aluno(Aluno, Curso),
                                                     Media is (N1 + N2)/2,
                                                     Media >= 5,
                                                     Media < 7,
                                                     Situacao = 'Em exame'.


status(Aluno, Disciplina, Media, Curso, Situacao) :- turma(Disciplina, Aluno, N1, N2),
                                                     aluno(Aluno, Curso),
                                                     Media is (N1 + N2)/2,
                                                     Media < 5,
                                                     Situacao = 'Reprovado'.

time(gremio,poa).
time(inter,poa).
time(interzinho,sm).
time(guarani,ca).
time(gloria,vacaria).
time(avenida,sc).

jogo(gremio,3,1,inter).
jogo(gremio,0,0,interzinho).
jogo(gremio,4,0,guarani).
jogo(gremio,1,3,gloria).
jogo(gremio,2,2,avenida).

jogo(inter,3,2,gremio).
jogo(inter,0,0,interzinho).
jogo(inter,1,0,guarani).
jogo(inter,2,1,gloria).
jogo(inter,0,0,avenida).

vitoria(TA, TB, SG) :- time(TA,_),
                       time(TB,_),
                       jogo(TA,SA,SB,TB),
                       SG is SA - SB.

vitoria(TA, TB, SG) :- time(TA,_),
                       time(TB,_),
                       jogo(TB,SB,SA,TA),
                       SG is SA - SB.
