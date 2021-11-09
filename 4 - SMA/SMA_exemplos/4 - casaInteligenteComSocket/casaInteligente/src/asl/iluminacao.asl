ligado(nao).
autorizado(alexandre).
autorizado(ruan).
autorizado(thiago).

/* Initial goals */



/* Plans */


+pessoa(Pessoa) : ligado(nao) & autorizado(Pessoa)
	<- .print("Percebi o usuario ", Pessoa, " na sala... vou configurar a iluminacao para ele");
	   .send(Pessoa,achieve,mandaPreferenciasIluminacao).

+pessoa(Pessoa) : true.
	   
+iluminacao(Turno,Intensidade)[source(Pessoa)]: true
	<- .print(Pessoa," enviou sua preferencia de iluminacao de intensidade ", Intensidasde,  " no turno ", Turno);
	   .print(iluminacao);
	   .wait(5000);
	   configurarIluminacao(Intensidade).	   
	