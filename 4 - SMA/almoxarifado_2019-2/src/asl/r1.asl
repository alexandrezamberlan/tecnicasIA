// Agent r1 in project almoxarifado
/* Initial beliefs and rules */
viagens(3).

/* Plans */
+peca(grande) : viagens(Qtd) & Qtd > 0
	<- .print("percebi uma peca grande e vou guarda-la");
	   V = Qtd - 1;
	   -viagens(Qtd);
	   +viagens(V);
	   .print("tenho mais ", V, " viagens");
	   guardarPecaGrande.

+peca(grande) : true
	<- .print("percebi uma peca grande mas não posso mais guardar... vamos empilhar");	   
	   empilharPecaGrande.
	   
+peca(media) : viagens(Qtd) & Qtd > 0
	<- .print("percebi uma peca media e vou guarda-la");
	   V = Qtd - 1;
	   -viagens(Qtd);
	   +viagens(V);
	   .print("tenho mais ", V, " viagens");
	   guardarPecaMedia.

+peca(media) : true
	<- .print("percebi uma peca media mas não posso mais guardar... vou chamar r2");	   
	   .send(r2,achieve,peca(media)).	
	   
+chegou(gasolina) : viagens(Qtd) & Qtd == 0	      
	<- -viagens(0);
	   +viagens(3);
	   abastecer.
