// Agent gato in project almoxarifado

/* Initial beliefs and rules */

fome(sim).

/* Initial goals */



/* Plans */
+!chamandoPraCama[source(Agente)] : Agente = juliana & fome(sim)
	<- .print("Miau... quero comida....");
	   .broadcast(tell, fome(F));
	   .print("indo para a cama...").

+gatoComeu : true
	<- -fome(sim);
	   +fome(nao).
