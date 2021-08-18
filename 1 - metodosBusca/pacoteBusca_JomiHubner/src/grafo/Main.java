package grafo;

public class Main {

	public static void main(String[] args) {
		Grafo f = new GrafoNaoDirigido();
		// f = GrafoDirigido();
		
		f.criaVertice(1);
		f.criaVertice(2);
		f.criaVertice(3);
		f.criaVertice(4);
		
		f.criaAresta(1,2); // polimorfismo 
		f.criaAresta(2,2);
		f.criaAresta(2,3);
		f.criaAresta(1,3);
		f.criaAresta(4,3);
		f.criaAresta(1,4);
		f.criaAresta(1,4);
		
		System.out.println(f);
	
	}
}
