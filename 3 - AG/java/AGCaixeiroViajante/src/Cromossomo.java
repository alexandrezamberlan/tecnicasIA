import java.util.LinkedList;
import java.util.List;


public class Cromossomo implements Comparable<Cromossomo>{
	public List<String> caminho = new LinkedList<String>();	
	public int aptidao;
	public int porcentagemAptidao;
	
	public Cromossomo(List<String> valor, Mapa mapa) {
		this.caminho.addAll(valor);
		this.aptidao = calcularAptidao(mapa);
		this.porcentagemAptidao = calcularPorcentagemAptidao();
	}
	
	private int calcularAptidao(Mapa mapa) { //heurística do sistema ou do AG
		int aptidao = 0;
		//valor [4 3 1 2 6 5 9 8 7]
		String conexao; //"4,3"
		for (int i = 0; i < caminho.size() - 1; i++) {
			conexao = "" + caminho.get(i) + "," + caminho.get(i + 1);
			
			if (!mapa.listaConexoes.contains(conexao)) {
				aptidao+=10; //feriu uma restrição de conexão
			}
		}

		//falta adicionar a restrição da cidade faltante
		
		return aptidao;
	}
	
	private int calcularPorcentagemAptidao() {
		return 1;
	}

	@Override
	public String toString() {
		return "rota=" + caminho + ", aptidao=" + aptidao;
	}
	
	@Override
    public int compareTo(Cromossomo cromossomo) {
        if (this.aptidao < cromossomo.aptidao) {
            return -1;
        }
        return 1;
    }
	
	
	
}
