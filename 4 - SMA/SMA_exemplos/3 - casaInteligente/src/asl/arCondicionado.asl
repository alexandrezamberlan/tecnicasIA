// Agent arCondicionado in project casaInteligente

/* Initial beliefs and rules */

ligado(nao).

/* Initial goals */



/* Plans */


+pessoa(Pessoa) : ligado(nao)
	<- .print("Percebi o usuario ", Pessoa, " na sala... vou configurar a temperatura para ele");
	   .send(Pessoa,achieve,mandaPreferencias).

	   
+climatizacao(Estacao,Turno,T)[source(Pessoa)]: true
	<- .print(Pessoa," enviou sua preferencia de temperatura de ", T, " graus no(a) ", Estacao, " no turno ", Turno);
	   .print("Configurando climatizacao");
	   configurarClimatizacao(T).	   
	