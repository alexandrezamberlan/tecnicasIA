// Environment code for project almoxarifado

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

public class EntradaAlmoxarifado extends Environment {

    private Logger logger = Logger.getLogger("almoxarifado."+EntradaAlmoxarifado.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        try {
			addPercept(ASSyntax.parseLiteral("dia(quarta_feira)"));
			addPercept(ASSyntax.parseLiteral("temperatura(12)"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        //logger.info("executing: "+action+", but not implemented!");
        
        if (action.getFunctor().equals("darComida") && agName.equals("juliana")) {
        	System.out.println("[AMBIENTE FÍSICO] juliana está dando comida para o gato");
        	System.out.println("[AMBIENTE FÍSICO] gato está comendo....");
        	System.out.println("[AMBIENTE FÍSICO] gato indo para cama");
        	try {
				addPercept(ASSyntax.parseLiteral("gatoComeu"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TokenMgrError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
