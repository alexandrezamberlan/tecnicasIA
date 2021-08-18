package grafo;

public abstract class ArestaValorada extends Aresta {
    int custo;
		
    public ArestaValorada(Vertice vi, Vertice vj, int custo) {
        super(vi,vj);
        this.custo = custo;
    }

    public int getCusto() {
        return custo;
    }
    
    public String toString() {
		return vi + " <- "+custo+" -> " + vj;
    }
}
