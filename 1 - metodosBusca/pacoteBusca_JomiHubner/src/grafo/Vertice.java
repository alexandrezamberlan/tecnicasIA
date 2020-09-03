package grafo;

import java.util.*;

public class Vertice {
	Set<Aresta> arestas = new HashSet<Aresta>();
	
	private int id;
	
	public Vertice(int id) {
		this.id = id;
	}
	
	public void addAresta(Aresta a) {
		arestas.add(a);	
	}
	
	public int getId() {
		return id;
	}
	
	public List<Vertice> getAdjacentes() {
		List<Vertice> adj = new ArrayList<Vertice>();
		for (Aresta a: arestas) {
			if (a.vi == this) {
				adj.add(a.vj);
			} else {
				adj.add(a.vi);
			}
		}
		return adj;
	}

    public Map<Vertice,Integer> getCustoAdjacentes() {
        Map<Vertice,Integer> adj = new HashMap<Vertice,Integer>();
        for (Aresta a: arestas) {
            ArestaValorada ac = (ArestaValorada)a; // TODO: is it ok?
            if (a.vi == this) {
                adj.put(a.vj, ac.getCusto());
            } else {
                adj.put(a.vi, ac.getCusto());
            }
        }
        return adj;
    }

    public Set<Aresta> getArestas() {
        return arestas;
    }
	
	public String toString() {
		return String.valueOf(id);
	}
}
