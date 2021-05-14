// Agent aloisio in project salaDeAula.mas2j

/* Initial beliefs and rules */

/* Initial goals */

+dia(terca) : true
	<-
       .print("Fazendo meu cafe pra ir pra aula do Zamber....");
	   .wait(4000);
	   .print("entrei na sala......");
	   .broadcast(tell,bomDia).

+chamando(aloisio)[source(Agente)] : Agente == professor
	<- .print("presente");
	   .broadcast(tell,presente(aloisio)).
