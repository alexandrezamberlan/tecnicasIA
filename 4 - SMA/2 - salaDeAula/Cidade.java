// Environment code for project salaDeAula.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;

public class Cidade extends Environment {

    private Logger logger = Logger.getLogger("salaDeAula.mas2j."+Cidade.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        addPercept(Literal.parseLiteral("dia(terca)"));
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        
        if (action.getFunctor().equals("queHorasSao")) { // you may improve this condition
			 //extrair a hora cheia do sistema
			 
             addPercept(Literal.parseLiteral("hora(11)"));
			 
        } else logger.info("executing: "+action+", but not implemented!");
		
		
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

