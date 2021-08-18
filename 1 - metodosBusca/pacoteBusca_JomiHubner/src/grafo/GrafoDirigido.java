
package grafo;


public class GrafoDirigido extends Grafo {

	public void criaAresta(int i, int j) {
		Vertice vi = vertices.get(i);
		Vertice vj = vertices.get(j);
		new ArestaDirigida(vi,vj);
	}

    public void criaAresta(int i, int j, int custo) {
        Vertice vi = vertices.get(i);
        Vertice vj = vertices.get(j);
        new ArestaDirigidaValorada(vi,vj, custo);        
    }
}
