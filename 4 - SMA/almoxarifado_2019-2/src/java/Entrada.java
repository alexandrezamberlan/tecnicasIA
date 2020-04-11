// Environment code for project almoxarifado
import jason.asSyntax.*;
import jason.environment.*;
import java.util.Random;
import java.util.logging.*;

public class Entrada extends Environment {
    private Logger logger = Logger.getLogger("almoxarifado."+Entrada.class.getName());
    private int contaEmpilhamentos = 0;
    private String sorteiaPeca() {
    	Random gerador = new Random();
    	switch (gerador.nextInt(3)) {
    		case 0 : return "peca(pequena)";
    		case 1 : return "peca(media)";
    		case 2 : return "peca(grande)";
    	}
    	return "";
    }
    
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        addPercept(Literal.parseLiteral("peca(media)"));
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        if (action.getFunctor().equals("guardarPecaGrande")) {
        	System.out.println("[ambiente] " + agName + " está guardando peça grande");
        	removePercept(Literal.parseLiteral("peca(grande)"));
        } else if (action.getFunctor().equals("empilharPecaGrande")) {        	
        	contaEmpilhamentos++;
        	System.out.println("[ambiente] mais uma peça grande empilhada na entrada: " + contaEmpilhamentos);
        	removePercept(Literal.parseLiteral("peca(grande)"));
        } else if (action.getFunctor().equals("guardarPecaMedia")) {
        	System.out.println("[ambiente] " + agName + " está guardando peça média");
        	removePercept(Literal.parseLiteral("peca(media)"));
        } else if (action.getFunctor().equals("guardarPecaPequena")) {
        	System.out.println("[ambiente] " + agName + " está guardando peça pequena");
        	removePercept(Literal.parseLiteral("peca(pequena)"));
        } else if (action.getFunctor().equals("abastecer")) {
        	System.out.println("[ambiente] " + agName + " está reabastecendo");
        	removePercept(Literal.parseLiteral("chegou(gasolina)"));
        } else logger.info(" tentando executar  "+action+", mas ainda não implementada!");
        
        try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        if (contaEmpilhamentos == 3) {
        	addPercept(Literal.parseLiteral("chegou(gasolina)"));
        	contaEmpilhamentos = 0;        	
        }
        addPercept(Literal.parseLiteral(sorteiaPeca()));
        
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
