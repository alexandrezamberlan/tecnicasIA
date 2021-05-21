// Agent lucas in project aulaDeIA

/* Initial beliefs and rules */

humor(bom).

/* Initial goals */

!start.

/* Plans */

+!start : true 
	<- .print("Bom dia!!");
	   .broadcast(tell,bomDia).
	  
+dia(D) : D = sexta
	<-
	 .print("Beeeiiii... sextou....").
	   
+!tudoBemContigo[source(Pessoa)] : humor(H) & H = bom
	<- .print("sim... tudo bem ", Pessoa);
	   .send(Pessoa, tell, lucas(tudoBem)). 
	   
+melhorouConexao[source(Pessoa)] : Pessoa = alexandre
	<- 
	   .wait(4000);
	   .print("simm... tá bem boa professor");
	   .broadcast(tell, conexaoBoa).