import java.util.LinkedList;
import java.util.List;
import busca.Heuristica;
import busca.Estado;

public class Mapa implements Estado, Heuristica {

    public static List<Cidade> listaHeuristica;
    public static int matrizAdjacencia[][];
    
    @Override
    public String getDescricao() {
        return "Mapa da Romênia + a tabela de custo heurístico estimado, ambos do livro IA de Stuart Russel";
    }

    final int origem;
    final int destino;
    final String op; // operacao que gerou o estado

    //atenção.... matrizes precisam ser clonadas ao gerarmos novos estados
    int[][] clonar(int origem[][]) {
        int destino[][] = new int[origem.length][origem.length];
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = origem[i][j];
            }
        }
        return destino;
    }
    
    public Mapa(int origem, int destino, String op) {
        this.origem = origem;
        this.destino = destino;
        this.op = op;
    }

    /**
     * verifica se o estado e meta
     */
    @Override
    public boolean ehMeta() {
        return origem == destino;
    }

    
    @Override
    public int custo() {
        //ter como base a matriz de adjacência, ou melhor, o valor da distancia entre origem e destino
        if (matrizAdjacencia[origem][destino] != 0) {
            return matrizAdjacencia[origem][destino];
        }
        return 0;
        //return 1;
    }

   
    @Override
    public int h() {
        //ter como base a lista heuristica
        return listaHeuristica.get(destino).estimativa;
        //return 1;
    }

    /**
     * gera uma lista de sucessores do nodo.
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores

        irOrigemDestino(origem, destino, visitados);

        return visitados;
    }

    private void irOrigemDestino(int origem, int destino, List<Estado> visitados) {
        //...
        
        
        
        Mapa novo = new Mapa(origem, destino, op);
        if (!visitados.contains(novo)) {
            visitados.add(novo);
        }
    }
    
    
    /**
     * verifica se um estado e igual a outro (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Mapa) {
            Mapa e = (Mapa) o;
            
            return true;
        }
        return false;
    }

    /**
     * retorna o hashCode desse estado (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() {
        String estado = "";

        
        return estado.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer resultado = new StringBuffer();
        
        return "\n" + op + "\n" + resultado + "\n\n";
    }

    public static void main(String[] a) {
        Mapa.listaHeuristica = new LinkedList();
        Util.lerArquivoHeuristica(Mapa.listaHeuristica);
        Grafo.mostrarListaHeuristica(Mapa.listaHeuristica);
        Mapa.matrizAdjacencia = new int[listaHeuristica.size()][listaHeuristica.size()];
        Util.lerArquivoMapa(Mapa.matrizAdjacencia, Mapa.listaHeuristica);
        Grafo.mostrarMatrizAdjacencia(Mapa.matrizAdjacencia, Mapa.listaHeuristica);
        
        
//        int qualMetodo;
//        Nodo n;
//        try {
//        
//            qualMetodo = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Profundidade\n2 - Largura\n3 - A*"));
//            int origem = Grafo.pegaIndice("Arad", listaHeuristica);
//            int destino = Grafo.pegaIndice("Bucharest", listaHeuristica);
//            estadoInicial = new Mapa(origem, destino, "Estado Inicial");
//
//            switch (qualMetodo) {
//                case 1:
//                    System.out.println("busca em PROFUNDIDADE");
//                    n = new BuscaProfundidade(new MostraStatusConsole()).busca(estadoInicial);
//                    break;
//                case 2:
//                    System.out.println("busca em LARGURA");
//                    n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
//                    break;
//                case 3:
//                    System.out.println("busca em A*");
//                    n = new AEstrela(new MostraStatusConsole()).busca(estadoInicial);
//                    break;
//                default:
//                    n = null;
//                    JOptionPane.showMessageDialog(null, "Método não implementado");
//            }
//            if (n == null) {
//                System.out.println("sem solucao!");
//                System.out.println(estadoInicial);
//            } else {
//                System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
//            }
//        } catch (HeadlessException | NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
        System.exit(0);
    }
}
