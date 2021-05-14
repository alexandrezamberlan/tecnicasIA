// Agent r2 in project Almoxarifado.mas2j

/* Initial beliefs and rules */

viagens(4).


+temEnergia : viagens(T) & T < 4
	<- .print("vou me carregar um pouco");
	   X = T + 1;
	   -viagens(T);
	   +viagens(X).

+peca(P) : viagens(T) & T > 0 & P == grande
	<- 	
		X = T - 1;
		.print("percebi uma peca ", P, " e vou guarda-la, porem só tenho mais ", X, " viagens");
		-viagens(T);
		+viagens(X);
		guardarPecaGrande.


+peca(P) : viagens(T) & T > 0 & P == media
	<- 	
		X = T - 1;
		.print("percebi uma peca ", P, " e vou guarda-la, porem só tenho mais ", X, " viagens");
		-viagens(T);
		+viagens(X);
		guardarPecaMedia.
		
+peca(P) : P == grande
	<- 	
		.print("percebi uma peca ", P, " e vou madar empilhar na entrada");
		empilharPecaGrande.

+peca(P) : P == media
	<- 	
		.print("percebi uma peca ", P, " e vou mandar o r1 guarda-la");
		.send(r1,achieve,peca(P)).
