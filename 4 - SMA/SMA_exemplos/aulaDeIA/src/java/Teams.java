// Environment code for project aulaDeIA

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

public class Teams extends Environment {

    private Logger logger = Logger.getLogger("aulaDeIA."+Teams.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        try {
			addPercept(ASSyntax.parseLiteral("hora(7)"));
			addPercept(ASSyntax.parseLiteral("dia(sexta)"));
			addPercept(ASSyntax.parseLiteral("teams(problemaConexao)"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        
        if (action.getFunctor().equals("corrigirConexao")) {
        	System.out.println("[AMBIENTE FÍSICO] a conexão está sendo corrigida pelo " + agName);
        	System.out.println("[AMBIENTE FÍSICO] a conexão foi corrigida ");
        	try {
				removePercept(ASSyntax.parseLiteral("teams(problemaConexao)"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        } else logger.info("tentando executar: "+action+", mas ainda não implementada!");
        
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
