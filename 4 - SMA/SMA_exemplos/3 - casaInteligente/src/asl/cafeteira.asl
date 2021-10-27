+pessoa(Pessoa) : true
	<- .print("Percebi o usuario ", Pessoa, " na sala... vou fazer o cafe para ele");
	   .send(Pessoa,achieve,mandaPreferenciasCafe).

	   
+cafe(Turno,Intensidade)[source(Pessoa)]: true
	<- .print(Pessoa," enviou sua preferencia cafe como ", Intensidade, " no turno ", Turno);
	   .print("Preparando o cafe");
	   prepararCafe(Intensidade).	   
	   
	   
+naoQueroCafe[source(Pessoa)]: true
	<- .print(Pessoa," avisou que nao quer cafe").
	