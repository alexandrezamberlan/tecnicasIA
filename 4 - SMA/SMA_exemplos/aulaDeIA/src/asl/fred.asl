// Agent fred in project aulaDeIA

/* Initial beliefs and rules */


/* Initial goals */

!start.

/* Plans */

+!start : true 
	<- 
		.wait(3000);
		.print("Bom dia!!");
	    .broadcast(tell,bomDia).

+!tudoBemContigo[source(Pessoa)] : humor(H) & H = bom
	<- .print("ahammm.. tudo bem ", Pessoa);
	   .send(Pessoa, tell, fred(tudoBem));
	   .wait(2000);
	   .print(Pessoa," você está bem?");
	   .send(Pessoa, achieve, tudoBemContigo). 
	   
+dia(D) : D = sexta
	<- 
	   +humor(bom);
	   .wait(1000);
	   ?humor(H);
	   .print("Hoje estou com meu humor ", H, " vou dizer pro lucas");
	   .print("Lucas... hoje estou com meu humor ", H);
	   .send(lucas, tell, humor(H)).
	   
+melhorouConexao[source(Pessoa)] : true
	<- .print("melhouuuuu");
	   .broadcast(tell, conexaoBoa).