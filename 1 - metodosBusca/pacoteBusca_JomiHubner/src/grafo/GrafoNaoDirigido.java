
package grafo;

public class GrafoNaoDirigido extends Grafo {
	public void criaAresta(int i, int j) {
		Vertice vi = vertices.get(i);
		Vertice vj = vertices.get(j);
		new ArestaNaoDirigida(vi,vj);
	}

    public void criaAresta(int i, int j, int custo) {
        Vertice vi = vertices.get(i);
        Vertice vj = vertices.get(j);
        new ArestaNaoDirigidaValorada(vi,vj,custo);
    }
}
