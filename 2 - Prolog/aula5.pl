localizadoEm('Santa Maria','RS').
localizadoEm('São Borja','RS').
localizadoEm('Cruz Alta', 'RS').
localizadoEm('Porto Alegre', 'RS').

localizadoEm('Florianópolis','SC').
localizadoEm('Sombrio','SC').

localizadoEm('Curitiba','PR').

localizadoEm('Montevidéo','Uruguai').
localizadoEm('Paris','França').


localizadoEm('RS','Brasil').
localizadoEm('SC','Brasil').
localizadoEm('PR','Brasil').

localizadoEm('Brasil','América do Sul').
localizadoEm('Uruguai', 'América do Sul').
localizadoEm('França', 'Europa').

estaEm(Lugar, OutroLugar) :- localizadoEm(Lugar, OutroLugar).
estaEm(Lugar, OutroLugar) :- localizadoEm(Lugar, I),
                              estaEm(I,OutroLugar).

nasceuEm('Renato','Santa Maria').
nasceuEm('João F','Cruz Alta').
nasceuEm('Victor','São Borja').   
nasceuEm('Arthur','Florianópolis').
nasceuEm('Nicholas','Paris').
nasceuEm(P,Lugar):- localizadoEm(OutroLugar, Lugar),
                    nasceuEm(P,OutroLugar).



gaucho(P) :- nasceuEm(P,'RS').

brasileiro(P) :- nasceuEm(P, 'Brasil'). 
