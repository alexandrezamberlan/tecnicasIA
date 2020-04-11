/*
 * Classe PERCEPTRON responsável para aprendizado e resolução da tabela OR
 *
 */
 
public class Perceptron_Or {
 
    // pesos sinápticos [0] entrada 1, [1] entrada 2, [3]BIAS
    private double[] peso = new double[3];
 
    // variável responsável pelo somatório(rede).
    private double NET = 0;
 
    // variavél responsável pelo número máximo de vezes que havera treinamento
    private final int epocasMax = 30;
 
    // variável responsável pela contagem das épocas durante o treinamento
    int count = 0;
 
    // declara o vetor da matriz de aprendizado
    private int[][] matrizAprendizado = new int[4][3];
 
	// metodo de inicialização inicia o vetor da matriz de aprendizado
	public Perceptron_Or() {
 
		// Primeiro valor
		this.matrizAprendizado[0][0] = 0; // entrada 1
		this.matrizAprendizado[0][1] = 0; // entrada 2
		this.matrizAprendizado[0][2] = 0; // valor esperado
	 
		// Segundo Valor
		this.matrizAprendizado[1][0] = 0; // entrada 1
		this.matrizAprendizado[1][1] = 1; // entrada 2
		this.matrizAprendizado[1][2] = 1; // valor esperado
	 
		// terceiro valor
		this.matrizAprendizado[2][0] = 1; // entrada 1
		this.matrizAprendizado[2][1] = 0; // entrada 2
		this.matrizAprendizado[2][2] = 1; // valor esperado
	 
		// quarto valor
		this.matrizAprendizado[3][0] = 1; // entrada 1
		this.matrizAprendizado[3][1] = 1; // entrada 2
		this.matrizAprendizado[3][2] = 1; // valor esperado
	 
		// inicialização dos pesos sinápticos
	 
		// Peso sináptico para primeira entrada.
		peso[0] = 0;
		// Peso sináptico para segunda entrada.
		peso[1] = 0;
		// Peso sináptico para o BIAS
		peso[2]= 0;
	 
	}
 
	// Método responsávelpelo somatório e a função de ativação.
    int executar(int x1, int x2) {
 
        // Somatório (NET)
        NET = (x1 * peso[0]) + (x2 * peso[1]) + ((-1) * peso[2]);
 
        // Função de Ativação tipo STEP
        if (NET >= 0) {
            return 1;
        }
        return 0;
    }
 
    // Método para treinamento da rede
    public void treinar() {
	
        // variavel utilizada responsável pelo controlede treinamento recebefalso
        boolean treinou;
        // varável responsável para receber o valor da saída (y)
        int saida;
		
		do {
			treinou = true;
			// laço usado para fazer todas as entradas
			for (int i = 0; i < matrizAprendizado.length; i++) {
				// A saída recebe o resultado da rede que no caso é 1 ou 0
				saida = executar(matrizAprendizado[i][0], matrizAprendizado[i][1]);
	 
				if (saida != matrizAprendizado[i][2]) {
					// Caso a saída seja diferente do valor esperado
	 
					// os pesos sinápticos serão corrigidos
					corrigirPeso(i, saida);
					// a variavél responsável pelo controlede treinamento recebe falso
					treinou = false;
	 
				}
			}
			// acrescenta uma época
			this.count++;
	 
			// teste se houve algum erro duranteo treinamento e o número de epocas
			//é menor qe o definido
		} while (!treinou && this.count < this.epocasMax);
 
    }    // fim do método para treinamento
 
    // Método para a correção de pesos
    void corrigirPeso(int i, int saida) {
		
        peso[0] = peso[0] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0]);
        peso[1] = peso[1] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1]);
        peso[2] = peso[2] + (1 * (matrizAprendizado[i][2] - saida) * (-1));
		
    }
 
    void testar() {
 
        System.out.println(" Teste 01 para 0 e 0 " + executar(0, 0));
 
        System.out.println(" Teste 02 para 0 e 1 " + executar(0, 1));
 
        System.out.println(" Teste 03 para 1 e 0 " + executar(1, 0));      
 
        System.out.println(" Teste 05 para 1 e 1 " + executar(1, 1));
 
    }
 
    public static void main(String[] arguments) {
 
        Perceptron_Or p = new Perceptron_Or();
 
        p.treinar();
 
        System.out.println("Para aprender o algoritmo treinou " + p.count + " vezes! \n ");
 
        p.testar();
 
    }
}