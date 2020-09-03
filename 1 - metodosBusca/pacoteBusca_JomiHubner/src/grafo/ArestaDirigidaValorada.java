package grafo;

public class ArestaDirigidaValorada extends ArestaValorada {
	
	public ArestaDirigidaValorada(Vertice vi, Vertice vj, int custo) {
		super(vi,vj, custo);
		vi.addAresta(this);
	}
	
}
