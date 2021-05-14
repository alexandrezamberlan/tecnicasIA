// Agent r1 in project Almoxarifado.mas2j

+peca(P) : P == pequena
	<- .print("percebi uma peça pequena e vou guarda-la");
	   guardarPecaPequena.
	   
+!peca(media)[source(Agente)] : true
	<- .print(Agente," mandou eu guardar peça média...");
	   guardarPecaMedia.

