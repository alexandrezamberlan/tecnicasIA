// Environment code for project Almoxarifado.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;
import java.util.Random;

public class Entrada extends Environment {

    private Logger logger = Logger.getLogger("Almoxarifado.mas2j."+Entrada.class.getName());
	
	
	private String sorteiaPeca() {
		Random gerador = new Random();
		
		switch (gerador.nextInt(3)) {
			case 0 : return "peca(pequena)";
			case 1 : return "peca(media)";
			case 2 : return "peca(grande)";
		}
		return "erro";
	}
	
	private boolean chegouEnergia() {
		Random gerador = new Random();
		
		if (gerador.nextInt(4) == 0)
			return true;
		
		return false;
	}

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        
		addPercept(Literal.parseLiteral(sorteiaPeca()));
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        
        if (action.getFunctor().equals("guardarPecaPequena")) { 
             
			 logger.info("peça pequena sendo guardada pelo " + agName);

			 removePercept(Literal.parseLiteral("peca(pequena)"));
			 
        } else  if (action.getFunctor().equals("guardarPecaMedia")) { 
             
			 logger.info("peça média sendo guardada pelo " + agName);
			 removePercept(Literal.parseLiteral("peca(media)"));
			 
        } else if (action.getFunctor().equals("guardarPecaGrande")) { 
             
			 logger.info("peça grande sendo guardada pelo " + agName);
			 removePercept(Literal.parseLiteral("peca(grande)"));
		
		} else if (action.getFunctor().equals("empilharPecaGrande")) { 
             
			 logger.info("peça grande sendo empilhada na entrada do Almoxarifado");
			 removePercept(Literal.parseLiteral("peca(grande)"));
		
		} else logger.info("tentando executar  "+action+", mas não foi implementada ainda...");
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			logger.info("Erro ao tentar fazer o sleep");
		}
		
		addPercept(Literal.parseLiteral(sorteiaPeca()));
		
		if (chegouEnergia()) {
			logger.info("Energia ativa.... robos podem se recarregar.....");
			addPercept(Literal.parseLiteral("temEnergia"));
		}
		
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

