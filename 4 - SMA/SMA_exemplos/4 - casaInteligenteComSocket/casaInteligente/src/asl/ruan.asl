climatizacao(inverno,manha,20).
climatizacao(inverno,tarde,20).
climatizacao(inverno,noite,20).
climatizacao(primavera,manha,16).
climatizacao(primavera,tarde,22).
climatizacao(primavera,noite,116).
climatizacao(verao,manha,18).
climatizacao(verao,tarde,18).
climatizacao(verao,noite,18).

iluminacao(manha,fraca).
iluminacao(tarde,media).
iluminacao(noite,forte).

cafe(manha,forte).
cafe(tarde,forte).
cafe(noite,_).
	
	
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