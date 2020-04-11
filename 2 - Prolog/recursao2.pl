arco(a,b).
arco(b,d).
arco(a,d).
arco(a,c).
arco(c,d).
arco(d,e).
arco(e,f).
arco(c,f).

haLigacao(Origem, Destino) :- arco(Origem, Destino).

haLigacao(Origem, Destino) :- arco(Origem, Intermediario), 
                              haLigacao(Intermediario,Destino).
