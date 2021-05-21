// Agent alexandre in project aulaDeIA

/* Initial beliefs and rules */
humor(medio).

/* Initial goals */


/* Plans */

+bomDia[source(Pessoa)] : true
	<- .print("Oi ", Pessoa, " bom dia. Tudo bem contigo?");
	   .send(Pessoa,achieve,tudoBemContigo).
	   
+!tudoBemContigo[source(Pessoa)] : humor(H) & H = bom
	<- .print("siimmmm... obrigado por perguntar", Pessoa);
	   .send(Pessoa, tell, alexandre(tudoBem)).
	   
+!tudoBemContigo[source(Pessoa)] : true
	<- .print("Sim... obrigado por perguntar  ", Pessoa);
	   .send(Pessoa, tell, alexandre(maisOuMenos)).
	   
+teams(problemaConexao) : true
	<- .print("Hichiiiiii... me lasquei....conexão tá ruim....vou tentar arrumar");
	   corrigirConexao. //ação que deve ser implementada no Java
	   
-teams(problemaConexao): true
	<- 
	 .print("Acho que melhorou...a conexao... vou perguntar para o pessoal... ");
	 .print("E aí galera... melhorou a conexão???");
	 .broadcast(tell, melhorouConexao).