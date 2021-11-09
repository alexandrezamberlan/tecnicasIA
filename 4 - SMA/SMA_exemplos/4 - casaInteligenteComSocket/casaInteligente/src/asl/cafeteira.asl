ligado(nao).

autorizado(alexandre).
autorizado(ruan).
autorizado(thiago).


+pessoa(Pessoa) : autorizado(Pessoa)
	<- .print("Percebi o usuario ", Pessoa, " na sala... vou fazer o cafe para ele");
	   .send(Pessoa,achieve,mandaPreferenciasCafe).
	   
+pessoa(Pessoa) : true.

	   
+cafe(Turno,Intensidade)[source(Pessoa)]: true
	<- .print(Pessoa," enviou sua preferencia cafe como ", Intensidade, " no turno ", Turno);
	   .print("Preparando o cafe");
	   .wait(3000);
	   prepararCafe(Intensidade).	   
	   
	   
+naoQueroCafe[source(Pessoa)]: true
	<- .print(Pessoa," avisou que nao quer cafe").
	