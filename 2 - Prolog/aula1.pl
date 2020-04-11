%--PREDICADOS varios que caracterizam os produtos de um supermercado
%  quanto a sua est�tica, dura��o e pre�o
bonito( caderno ).
bonito( candeeeiro ).
bonito( computador ).
bonito( vinho ).
bonito( cognac ).
bonito( fruta ).

bom( caderno ).
bom( leite ).
bom( ovos ).
bom( vinho ).
bom( arroz ).
bom( candeeiro ).
bom( carne ).
bom( computador ).

barato( caderno ).
barato( computador ).
barato( leite ).
barato( vinho ).
barato( fruta ).
barato( ovos ).
barato( arroz ).

%--Lista de compras (aquilo que eu preciso "mesmo")
% 
preciso( ovos ).
preciso( peixe ).
preciso( ervilhas ).
preciso( sabonete ).
preciso( 'pão' ).

%--As COISAS que na verdade vou comprar.
% 
compro( Coisa ):- preciso( Coisa ).
compro( Coisa ):- bbb( Coisa ).

bbb( Coisa ) :- bonito( Coisa ),
                bom( Coisa ),
                barato( Coisa ).


superfluo( Coisa ) :- bonito( Coisa ),
                      not(barato( Coisa )),
                      not(preciso( Coisa )).

inutil( Coisa ) :- not(bonito(Coisa)), not(barato(Coisa)).                      