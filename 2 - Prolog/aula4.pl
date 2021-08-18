%--classes de comboios
% classe( Sigla, Nome, Preco ) :- Nome descreve a classe (tipo) de comboio identificado pela Sigla
%                                  sendo o bilhete cobrado a Preco por Km.
classe( a, alfa, 50 ).
classe( ic, intercidades, 40 ).
classe( r, regional, 25 ).
classe( su, suburbano, 15 ).

%--comboios em marcha nas linhas da CP
% comboio( Cod, Classe, Orig, Dest ) :- Cod � o c�digo do comboio da Classe (sigla) que parte de Orig
%                                      e vai at� Dest.
comboio( 40001,a,lisboa,porto ).
comboio( 40002,a,lisboa,porto ).
comboio( 40003,a,lisboa,braga ).
comboio( 50001,ic,braga,porto ).
comboio( 50002,ic,porto,braga ).
comboio( 62001,su,braga,porto ).

%--hor�rio de partida de cada comboio em cada esta��o (igual a hora de chegada)
% horario( Cod, Local, Hora ) :- O comboio, descrito pelo Codigo, chega/parte �  esta��o Local � Hora (hh:mm)
horario( 40001,lisboa,07:05 ).
horario( 40001,santarem,07:35 ).
horario( 40001,entroncamento,08:05 ).
horario( 40001,porto,10:45 ).

horario( 40002,lisboa,10:15 ).
horario( 40002,coimbra,12:00 ).
horario( 40002,aveiro,12:35 ).
horario( 40002,porto,13:05 ).

horario( 40003,lisboa,12:05 ).
horario( 40003,santarem,12:35 ).
horario( 40003,pombal,13:45 ).
horario( 40003,coimbra,14:10 ).
horario( 40003,aveiro,14:50 ).
horario( 40003,porto,15:30 ).
horario( 40003,famalicao,15:15 ).
horario( 40003,braga,15:40 ).

%--partidas de uma esta��o
% partida( E, H, CodC,Cl,D ) :- Da esta��o E parte �s Hhoras um comboio de refer�ncia CodC e classe Cl com destino D.
partida( E, H, CodC,Cl,D ) :- horario( CodC, E, H ),
                              comboio( CodC, C, _,D ), 
                              D \== E, 
                              classe( C, Cl, _ ).





