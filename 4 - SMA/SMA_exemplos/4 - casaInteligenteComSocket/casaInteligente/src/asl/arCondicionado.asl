// Agent arCondicionado in project casaInteligente

/* Initial beliefs and rules */

ligado(nao).
autorizado(alexandre).
autorizado(ruan).
autorizado(thiago).


/* Initial goals */



/* Plans */


+pessoa(Pessoa) : ligado(nao) & autorizado(Pessoa)
	<- .print("Percebi o usuario ", Pessoa, " na sala... vou configurar a temperatura para ele");
	   .send(Pessoa,achieve,mandaPreferencias).
	   
+pessoa(Pessoa) : true.

	   
+climatizacao(Estacao,Turno,T)[source(Pessoa)]: true
	<- .print(Pessoa," enviou sua preferencia de temperatura de ", T, " graus no(a) ", Estacao, " no turno ", Turno);
	   .print("Configurando climatizacao");
	   .wait(7000);
	   configurarClimatizacao(T).	   
	