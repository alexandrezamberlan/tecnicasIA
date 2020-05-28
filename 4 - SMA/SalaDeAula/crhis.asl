// Agent crhis in project SalaDeAula.mas2j

/* Initial beliefs and rules */


cachorro(tooby).
professor(ia,alexandre).
doente(sim).

/* Initial goals */

/* Plans */

+hoje(terca) : doente(Status) & Status == nao 
	<- 
		.print("Bom dia turma");
		+disciplina(ia);
		?cachorro(NomeCachorro);
		.print("Vem aqui...", NomeCachorro);
		?professor(ia,NomeProfessor);
		.print("Obaaa tenho aula com o ", NomeProfessor);
		.print("Luiz você vai na aula?");
		.send(luiz,achieve,vaiNaAula).

+hoje(terca) : true
	<- 
		.print("Hoje estou mal e de mau humor");
		!tomarRemedio.
		
+!tomarRemedio : doente(sim)
	<- .print("estou domando um paracetamol....");
	   .wait(3000);
	   .print("aaaaahhh, estou bem melhor....");
	   -doente(sim);
	   +doente(nao);
	   +hoje(terca).
