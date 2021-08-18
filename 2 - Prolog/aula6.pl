arco(a,b).
arco(b,c).
arco(c,d).

arco(a,e).
arco(e,d).
caminho(O,D) :- arco(O,D).
caminho(O,D) :- arco(O,I),
                caminho(I,D).
