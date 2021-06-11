import java.util.LinkedList;
import java.util.List;

public class Mapa {
	public List<String> listaConexoes;
	public int quantidadePontos;
	
	public Mapa(int quantidadePontos) {
		this.quantidadePontos = quantidadePontos;
		listaConexoes = new LinkedList<String>();
	}
}