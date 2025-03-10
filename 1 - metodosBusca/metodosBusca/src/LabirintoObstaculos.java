import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import busca.Heuristica;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.AEstrela;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;
import javax.swing.JOptionPane;

public class LabirintoObstaculos implements Estado, Heuristica {
    
    @Override
    public String getDescricao() {
        return "O jogo do labirinto é uma matriz NxM, onde são sorteadas duas peças:\n"
        		+ "\n"
        		+ "peça que representa o portal de entrada no labirinto;\n"
        		+ "peça que representa o portal de saída no labirinto.\n"
        		+ "A Entrada é o portal em que um personagem qualquer inicia no "
        		+ "labirinto e precisa se movimentar até a Saída. "
        		+ "O foco aqui, é chegar na Saída pelo menor número de movimentos (células). "
        		+ "Entretanto, não pode ser nas diagonais.";
    }

    final char matriz[][]; // preferir "immutable objects"
    int linhaEntrada, colunaEntrada; //guarda a posição do Entrada/E
    int linhaSaida, colunaSaida;
    final String op; // operacao que gerou o estado

    
    //atenção.... matrizes precisam ser clonadas ao gerarmos novos estados
    char [][]clonar(char origem[][]) {
        char destino[][] = new char[origem.length][origem.length];
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i][j] = origem[i][j];
            }
        }
        return destino;
    }
    
    /**
     * construtor para o estado gerado na evolução/resolução do problema, recebe cada valor de atributo
     */
    public LabirintoObstaculos(char m[][], int linhaEntrada, int colunaEntrada, int linhaSaida, int colunaSaida, String o) {
        this.matriz = m; //ter certeza que m foi clonado antes de entrar no construtor
        this.linhaEntrada = linhaEntrada;
        this.colunaEntrada = colunaEntrada;
        this.linhaSaida = linhaSaida;
        this.colunaSaida = colunaSaida;
        this.op = o;
    }
    
    /**
     * construtor para o estado INICIAL
     */
    public LabirintoObstaculos(int dimensao, String o, int porcentagemObstaculos) {
        this.matriz = new char[dimensao][dimensao];
        this.op = o;
        
        int quantidadeObstaculos = (dimensao*dimensao)* porcentagemObstaculos/100;
        System.out.println(quantidadeObstaculos);
        
        Random gerador = new Random();

        int entrada = gerador.nextInt(dimensao * dimensao); //13
        int saida;
        do {
            saida = gerador.nextInt(dimensao * dimensao); //3
        } while (entrada == saida);

        int contaPosicoes = 0;
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                if (contaPosicoes == entrada) {
                    this.matriz[i][j] = 'E';
                    this.linhaEntrada = i;
                    this.colunaEntrada = j;
                } else if (contaPosicoes == saida) {
                    this.matriz[i][j] = 'S';
                    this.linhaSaida = i;
                    this.colunaSaida = j;
                } else if (quantidadeObstaculos > 0 && gerador.nextInt(3) == 1) {
                    quantidadeObstaculos--;
                    this.matriz[i][j] = '@';
                } else {
                    this.matriz[i][j] = 'O';
                }
                contaPosicoes++;
            }
        }
    }

    /**
     * verifica se o estado e meta
     */
    @Override
    public boolean ehMeta() {
    	return this.linhaEntrada == this.linhaSaida && this.colunaEntrada == this.colunaSaida;
    }

    /**
     * ???
     *
     * @return Distancia
     */
    @Override
    public int custo() {
        return 1;
    }

    /**
     * ?????
     *
     * @return Quantidade
     */
    @Override 
    public int h() {
        int qtd = 0;

        //será que temos heurística
        return qtd;
    }

    /**
     * gera uma lista de sucessores do nodo.
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores

        paraCima(visitados);
        paraBaixo(visitados);
        paraEsquerda(visitados);
        paraDireita(visitados);
        
        return visitados;
    }

    private void paraCima(List<Estado> visitados) {
        if (this.linhaEntrada == 0 || this.matriz[this.linhaEntrada - 1][this.colunaEntrada] == '@') return; 

        char mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linhaEntrada - 1;
        int colunaTemp = this.colunaEntrada;
        
        mTemp[this.linhaEntrada][this.colunaEntrada] = 'O';
        mTemp[linhaTemp][colunaTemp] = 'E';
     
        LabirintoObstaculos novo = new LabirintoObstaculos(mTemp, linhaTemp, colunaTemp, this.linhaSaida, this.colunaSaida, "Movendo para cima");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraBaixo(List<Estado> visitados) {
        if (this.linhaEntrada == this.matriz.length-1 || this.matriz[this.linhaEntrada + 1][this.colunaEntrada] == '@') return;

        char mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linhaEntrada + 1;
        int colunaTemp = this.colunaEntrada;
        
        mTemp[this.linhaEntrada][this.colunaEntrada] = 'O';
        mTemp[linhaTemp][colunaTemp] = 'E';
               
        LabirintoObstaculos novo = new LabirintoObstaculos(mTemp, linhaTemp, colunaTemp, this.linhaSaida, this.colunaSaida, "Movendo para baixo");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraEsquerda(List<Estado> visitados) {
        if (this.colunaEntrada == 0 || this.matriz[this.linhaEntrada][this.colunaEntrada - 1] == '@') return;

        char mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linhaEntrada;
        int colunaTemp = this.colunaEntrada - 1;
        
        mTemp[this.linhaEntrada][this.colunaEntrada] = 'O';
        mTemp[linhaTemp][colunaTemp] = 'E';
     
        LabirintoObstaculos novo = new LabirintoObstaculos(mTemp, linhaTemp, colunaTemp, this.linhaSaida, this.colunaSaida,"Movendo para esquerda");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void paraDireita(List<Estado> visitados) {
        if (this.colunaEntrada == this.matriz.length-1 || this.matriz[this.linhaEntrada][this.colunaEntrada + 1] == '@') return;
        
        char mTemp[][];
        mTemp = clonar(this.matriz);
        int linhaTemp = this.linhaEntrada;
        int colunaTemp = this.colunaEntrada + 1;
        
        mTemp[this.linhaEntrada][this.colunaEntrada] = 'O';
        mTemp[linhaTemp][colunaTemp] = 'E';
               
        LabirintoObstaculos novo = new LabirintoObstaculos(mTemp, linhaTemp, colunaTemp, this.linhaSaida, this.colunaSaida,"Movendo para direita");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    
    /**
     * verifica se um estado e igual a outro (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof LabirintoObstaculos) {
            LabirintoObstaculos e = (LabirintoObstaculos) o;
            for (int i = 0; i < e.matriz.length; i++) {
                for (int j = 0; j < e.matriz.length; j++) {
                    if (e.matriz[i][j] != this.matriz[i][j]) {
                        return false;
                    }
                }
            }
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
        
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                estado = estado + this.matriz[i][j];
            }
        }
        return estado.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                resultado.append(this.matriz[i][j]);
                resultado.append("\t");
            }
            resultado.append("\n");
        }
        resultado.append("Posição Entrada: " + this.linhaEntrada + "," + this.colunaEntrada +"\n");
        resultado.append("Posição Saida: " + this.linhaSaida + "," + this.colunaSaida +"\n");
        return "\n"+ op + "\n" + resultado + "\n\n";
    }

    public static void main(String[] a) {
        LabirintoObstaculos estadoInicial = null;
        int dimensao;
        int porcentagemObstaculos;
        int qualMetodo;
        Nodo n;
        try {
            dimensao = 5;
            porcentagemObstaculos = 5;
            qualMetodo = 2; // 1 - Profundidade, 2 - Largura
            estadoInicial = new LabirintoObstaculos(dimensao, "estado inicial", porcentagemObstaculos);
            
            switch (qualMetodo) {
                case 1: 
                        System.out.println("busca em PROFUNDIDADE");
                        n = new BuscaProfundidade(new MostraStatusConsole()).busca(estadoInicial);
                        break;
                case 2: 
                        System.out.println("busca em LARGURA");
                        n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
                        break;
                default: 
                        n = null;
                        JOptionPane.showMessageDialog(null, "Método não implementado");
            }
//            Nodo n = new AEstrela(new MostraStatusConsole()).busca(estadoInicial); // Com Status de andamento
            if (n == null) {
                System.out.println("sem solucao!");
                System.out.println(estadoInicial);
            } else {
                System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        System.exit(0);
    }
}
