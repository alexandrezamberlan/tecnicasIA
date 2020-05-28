// Agent luiz in project SalaDeAula.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("Bom dia!!!").

+!vaiNaAula[source(Colega)]: true
	<- .print("Vou certo......", Colega).
	   

