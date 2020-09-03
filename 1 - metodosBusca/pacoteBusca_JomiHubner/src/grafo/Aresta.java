package grafo;

public abstract class Aresta {
	Vertice vi, vj;
	
	public Aresta(Vertice vi, Vertice vj) {
		this.vi = vi;
		this.vj = vj;
	}
	
    public String toString() {
		return vi + " <-> " + vj;
    }
}
