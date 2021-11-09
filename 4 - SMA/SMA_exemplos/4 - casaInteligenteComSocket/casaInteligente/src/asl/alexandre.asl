// Agent alexandre in project casaInteligente

/* Initial beliefs and rules */
climatizacao(inverno,manha,20).
climatizacao(inverno,tarde,19).
climatizacao(inverno,noite,24).
climatizacao(primavera,manha,25).
climatizacao(primavera,tarde,20).
climatizacao(primavera,noite,24).

iluminacao(manha,leve).
iluminacao(tarde,media).
iluminacao(noite,leve).

cafe(manha,forte).
cafe(tarde,fraco).

	
+!mandaPreferencias[source(Dispositivo)] : estacao(Estacao) & turno(Turno)
	<- .print("enviando minhas preferencias ao ", Dispositivo);
	   ?climatizacao(Estacao,Turno,T);
	   .send(Dispositivo,tell,climatizacao(Estacao,Turno,T)).
	   

+!mandaPreferenciasCafe[source(Dispositivo)] : cafe(Turno,Intensidade) & turno(T) & T == Turno
	<- .print("enviando minhas preferencias a(ao) ", Dispositivo);
	   .send(Dispositivo,tell,cafe(Turno,Intensidade)).
	   
+!mandaPreferenciasCafe[source(Dispositivo)] : true
	<- .print("eu nao tomo cafe de noite");
	   .send(Dispositivo,tell,naoQueroCafe).

+!mandaPreferenciasIluminacao[source(Dispositivo)] : iluminacao(Turno, Intensidade)
	<- .print("enviando minhas preferencias a(ao) ", Dispositivo);
	   .send(Dispositivo,tell,iluminacao(Turno,Intensidade)).