
package grafo;

import java.util.*;

public abstract class Grafo {
	Map<Integer,Vertice> vertices = new HashMap<Integer,Vertice>();
	
	private void addVertice(Vertice v) {
		vertices.put(v.getId(),v);
	}
	
	public void criaVertice(int id) {
		addVertice(new Vertice(id));
	}
    
    public Vertice getVertice(int id) {
        return vertices.get(id);
    }
	
	public abstract void criaAresta(int i, int j);
    public abstract void criaAresta(int i, int j, int custo);
	
	public String toString() {
		StringBuffer out = new StringBuffer();
		for (Vertice v: vertices.values()) {
			out.append(v.getId() + ": " + v.getAdjacentes() + "\n");
		}
		return out.toString();
	}
}
