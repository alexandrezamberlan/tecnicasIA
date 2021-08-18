jogador('Alexandre',45).
jogador('Luiz Friess',19).
jogador('Victor',19).
jogador('Maria',55).

homem('Alexandre').
homem('Luiz Friess').
homem('Victor').

mulher(M) :- jogador(M,_),
             not(homem(M)).

joga('Alexandre','RPG').
joga('Luiz Friess', 'FPS').
joga('Victor','FPS').
joga('Victor','MMO').
joga('Maria','MMO').
joga('Maria','RPG').

jogo('The Witcher 3', 'RPG', 18).
jogo('Bioshock', 'FPS', 16).
jogo('WOW','MMO',0).

recomendaRPG(Pessoa,Jogo) :- jogador(Pessoa, IdadeJogador),
                             joga(Pessoa,'RPG'),
                             jogo(Jogo,'RPG',IdadeCensura),
                             IdadeJogador >= IdadeCensura.





