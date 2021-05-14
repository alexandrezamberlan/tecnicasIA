// Agent juliana in project almoxarifado

/* Initial beliefs and rules */
disciplina("Inteligência Artificial",sexta_feira, alexandre).
disciplina("Estruturas de Dados",quarta_feira, alexandre).


/* Initial goals */

!start.

/* Plans */

+!start : true
	<- .print("Bom dia vida....."). 


+dia(D): disciplina(Disciplina, D, Professor) 
	<- 
		.print("Oba....hoje eu tenho aula com o profe ", Professor, " na disciplina ", Disciplina).

+temperatura(T) : T < 20
	<- .print("Hoje está muito frio....odeio... vou chamar o gato para me esquentar");
	   .send(gato,achieve,chamandoPraCama).

+temperatura(T) : true
	<- .print("Hoje está boa a temperatura....").		
	
+!darComidaGato[source(Pessoa)] : true
	<- .print("sim ", Pessoa, " ... estou indo dar comida pra ele");
	   darComida.
	   
+gatoComeu : true
	<- -fome(F).