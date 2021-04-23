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


a_direita_de(maria,joao).
a_direita_de(jose,maria).
a_direita_de(julia,jose).
a_direita_de(jorge,julia).
a_direita_de(ana,jorge).
a_direita_de(iris,ana).	

a_esquerda_de(Pesq, Pdir) :- 
	a_direita_de(Pdir, Pesq).
	
noMeio(PessoaEsq, PessoaMeio, PessoaDir) :- 	
	a_esquerda_de(PessoaEsq, PessoaMeio),
	a_direita_de(PessoaDir, PessoaMeio).
	
%ta_na_ponta(Pessoa) :- 
%	a_direita_de(Pessoa,_),
%   not(noMeio(_,Pessoa,_)),
%	writeln(" ponta direita").

%ta_na_ponta(Pessoa) :- 
%	a_esquerda_de(Pessoa,_),
%   not(noMeio(_,Pessoa,_)),
%	writeln(" ponta esquerda").

ta_na_ponta(Pessoa) :- a_direita_de(Pessoa, _),
                       not(a_direita_de(_,Pessoa)).                       	
					   
ta_na_ponta(Pessoa) :- a_esquerda_de(Pessoa, _),
                       not(a_esquerda_de(_,Pessoa)).   