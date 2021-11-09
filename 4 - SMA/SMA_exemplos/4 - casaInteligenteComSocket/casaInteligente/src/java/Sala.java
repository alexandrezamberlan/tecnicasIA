// Environment code for project casaInteligente

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.*;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Sala extends Environment {

	private Logger logger = Logger.getLogger("casaInteligente." + Sala.class.getName());

	String estacao = "primavera";
	String turno = "noite";
	String temperatura = "29";
	Random gerador = new Random();
	String pessoaEscolhida;
	LinkedList<String> pessoas;
	
	ServerSocket server;
    Socket client;
    DatagramSocket socket;
	
	

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		super.init(args);
		pessoas = new LinkedList<String>();
		pessoas.add("alexandre");
		pessoas.add("ruan");
		pessoas.add("thiago");
		pessoaEscolhida = pessoas.get(gerador.nextInt(pessoas.size()));
		
		this.createServerSocketTCP(1234);
        this.waitClientsTCP();
		
		try {
			addPercept(ASSyntax.parseLiteral("estacao(" + estacao + ")"));
			addPercept(ASSyntax.parseLiteral("turno(" + turno + ")"));
			addPercept(ASSyntax.parseLiteral("temperatura(" + temperatura + ")"));
//			addPercept(ASSyntax.parseLiteral("pessoa(" + pessoaEscolhida + ")"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean executeAction(String agName, Structure action) {

		// logger.info(agName + " mandou executar a acao " + action.getFunctor());
		String tDesejo;
		String intensidadeCafe;
		String intensidadeIluminacao;
		
		if (agName.equals("arCondicionado")) {
			tDesejo = action.getTerm(0).toString();
			
			if (action.getFunctor().equals("configurarClimatizacao")
					&& Integer.parseInt(tDesejo) < Integer.parseInt(temperatura)
					&& (estacao.equals("primavera") || estacao.equals("verao"))) { // you may improve this condition

				logger.info(agName + " acionou o rele (cool) em " + tDesejo + " graus");
			}
			if (action.getFunctor().equals("configurarClimatizacao")
					&& Integer.parseInt(tDesejo) >= Integer.parseInt(temperatura)
					&& (estacao.equals("outono") || estacao.equals("inverno"))) {
				
				logger.info(agName + " acionou o rele (heat) em " + tDesejo + " graus");
			}
		}

		if (agName.equals("cafeteira") && action.getFunctor().equals("prepararCafe")) {
			intensidadeCafe = action.getTerm(0).toString();
			
			logger.info(agName + " acionou o rele para fazer o cafe " + intensidadeCafe);
		}

		if (agName.equals("iluminacao") && action.getFunctor().equals("configurarIluminacao")) {
			intensidadeIluminacao = action.getTerm(0).toString();
			
			logger.info(agName + " acionou o rele para confirgurar a iluminacao na intensidade " + intensidadeIluminacao);
		}
		
		Comunicador.sendMessageTCP(client, action.toString());
		System.out.println("passei");
		
		return true; // the action was executed with success
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}
	
	private void createServerSocketTCP(int port) {
        try {
            server = new ServerSocket(port,10);
            logger.info("Server MAS waiting device at port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitClientsTCP() {  	
        try {
            client = server.accept();
            logger.info("Server MAS received a conection with device");
            new Thread() {
	            public void run() {
	            	String jsonStringReceived = new String();
	            
	            	logger.info("All threads to handle percepts are online!!");
	                try {
	                    while (true) {
	                        jsonStringReceived = Comunicador.receiveMessageTCP(client); 	                        
	                        
	                        logger.info("A socket json received: " + jsonStringReceived);
	                        
	                        if (jsonStringReceived.charAt(0) == '-') {
	                        	removePercept(ASSyntax.parseLiteral(jsonStringReceived.substring(1, jsonStringReceived.length())));
	                        	logger.info(jsonStringReceived.substring(1, jsonStringReceived.length()));
	                        } else  {
	                        	addPercept(ASSyntax.parseLiteral(jsonStringReceived));
	                        }
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
            }.start();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
