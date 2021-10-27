// Environment code for project casaInteligente

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

public class Sala extends Environment {

	private Logger logger = Logger.getLogger("casaInteligente." + Sala.class.getName());

	String estacao = "primavera";
	String turno = "manha";
	String temperatura = "28";

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		super.init(args);
		try {
			addPercept(ASSyntax.parseLiteral("estacao(" + estacao + ")"));
			addPercept(ASSyntax.parseLiteral("turno(" + turno + ")"));
			addPercept(ASSyntax.parseLiteral("temperatura(" + temperatura + ")"));
			addPercept(ASSyntax.parseLiteral("pessoa(thiago)"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean executeAction(String agName, Structure action) {

		// logger.info(agName + " mandou executar a acao " + action.getFunctor());
		String tDesejo;
		String intensidadeCafe;
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
			logger.info(agName + " acionou o rele oara fazer o cafe " + intensidadeCafe);
		}

		// else logger.info("tentando executar a acao "+action+", mas ainda nao foi
		// implementada!");

		try {
			Thread.sleep(5000);
			logger.info("Thiago saiu da sala");
			removePercept(ASSyntax.parseLiteral("pessoa(thiago)"));
			Thread.sleep(5000);
			logger.info("Alexandre entrou da sala");
			addPercept(ASSyntax.parseLiteral("pessoa(alexandre)"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TokenMgrError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true; // the action was executed with success
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}
}
