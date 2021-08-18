// Agent professor in project salaDeAula.mas2j

/* Initial beliefs and rules */
time(gremio).
filho(dante).
filho(tales).
idade(45).

aula(segunda,noite,si,ed).
aula(terca,manha,cc,ia).
aula(terca,tarde,jd,ia).
aula(quarta,manha,cc,ed).
aula(quarta,tarde,jd,poo).


turma(ia,aloisio).
turma(ia,luiz).
turma(ia,iago).
turma(ia,pierre).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").


+dia(Dia): aula(Dia,Turno,Curso,Disciplina) & Disciplina == ia & Turno == manha & Dia == terca
	<- 
		.print("obaaa aula com a turma do Aloisio......");
		+entrouSala(Disciplina).
		
		
+bomDia[source(Agente)] : entrouSala(ia)
	<- 
	   .print("Bem vindo...", Agente);
	   .print("bom pessoal... iniciamos a aula.....");
	   .print("bla bla bla bla");
	   .wait(5000);
	   queHorasSao.
	   
	   
+hora(11) : true
	<- .print("atençao pessoal... vou fazer a chamada ....");
	   !fazerChamada.

+!fazerChamada : turma(ia,Aluno) & not jaChamei(Aluno)
	<- .print("chamando ....", Aluno);
	   //paramos aqui
	   +jaChamei(Aluno);
	   !fazerChamada.
	   
+!fazerChamada.	  	   

