// Agent pierre in project salaDeAula.mas2j

/* Initial beliefs and rules */

/* Initial goals */

+chamando(pierre)[source(Agente)] : Agente == professor
	<- .print("presente");
	   .broadcast(tell,presente(pierre)).

