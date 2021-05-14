// Agent mae in project almoxarifado

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+fome(sim)[source(gato)] : true
	<- .print("juliana... levanta da cama e vai dar comida para o gato");
	   .send(juliana,achieve,darComidaGato).


+gatoComeu : true
	<- -fome(F).