// Agent r2 in project almoxarifado

/* Plans */
+!peca(media)[source(Agt)] : true
	<-
		.print(Agt," me mandou guardar peça média");
		guardarPecaMedia.
		
+peca(pequena) : true
	<- .print("percebi uma peça pequena e vou guarda-la");
	   guardarPecaPequena.
				
