/*
 * Classe PERCEPTRON responsável para aprendizado e resolução da tabela AND
 *
 * @author Dimas Kastibergue <k45t1b@gmail.com>;
 */
 
public class Perceptron_And {//RNA mais simples... feedforward .... uma camada... supervisionado
 
    // pesos sinápticos [0] entrada 1, [1] entrada 2, [3]BIAS
    double[] pesos = new double[3];//vetor que vai receber treinamento
 
    // variável responsável pelo somatório(rede).
    double SOMATORIO_REDE = 0;
 
    // constante responsável pelo número máximo de gerações
    final int geracoesMax = 100000;
 
    // variável responsável pela contagem das gerações durante o treinamento
    int contaGeracoes = 0;
 
    // declara o vetor da matriz de aprendizado
    int[][] matrizAprendizado = new int[4][3];
 
	// metodo de inicialização inicia o vetor da matriz de aprendizado
	Perceptron_And() {
        //tabela verdade
        //0 e 0 = 0 - Primeiro Valor
        //1 e 0 = 0 - Segundo Valor
        //0 e 1 = 0 - Terceiro Valor
        //1 e 1 = 1 - Quarto Valor
 
		// Primeiro valor
		this.matrizAprendizado[0][0] = 0; // entrada 1
		this.matrizAprendizado[0][1] = 0; // entrada 2
		this.matrizAprendizado[0][2] = 0; // valor esperado
	 
		// Segundo Valor
		this.matrizAprendizado[1][0] = 0; // entrada 1
		this.matrizAprendizado[1][1] = 1; // entrada 2
		this.matrizAprendizado[1][2] = 0; // valor esperado
	 
		// terceiro valor
		this.matrizAprendizado[2][0] = 1; // entrada 1
		this.matrizAprendizado[2][1] = 0; // entrada 2
		this.matrizAprendizado[2][2] = 0; // valor esperado
	 
		// quarto valor
		this.matrizAprendizado[3][0] = 1; // entrada 1
		this.matrizAprendizado[3][1] = 1; // entrada 2
		this.matrizAprendizado[3][2] = 1; // valor esperado
	 
		// inicialização dos pesos sinápticos
	 
		// Peso sináptico para primeira entrada.
		pesos[0] = 0;
		// Peso sináptico para segunda entrada.
		pesos[1] = 0;
		// Peso sináptico para o BIAS
		pesos[2]= 0;
	}
 
	// Método responsável pelo somatório e a função de ativação.
    int executar(int entrada1, int entrada2) {
 
        // Somatório (SOMATORIO_REDE)
        SOMATORIO_REDE = (entrada1 * pesos[0]) + (entrada2 * pesos[1]) + ((-1) * pesos[2]);
 
        // Função de Ativação tipo STEP
        if (SOMATORIO_REDE >= 0) {
            return 1;
        }
        return 0;
    }
 
    // Método para treinamento da rede
    public void treinar() {
 
        // variavel utilizada responsável pelo controle do treinamento recebe falso
        boolean treinou; //aprendeu
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
    
                    // os pesos sinápticos serão corrigidos, ou seja, calibrados
                    corrigirPeso(i, saida); 
                    // a variavél responsável pelo controlede treinamento recebe falso
                    treinou = false; //nao aprendeu
                }
            }
            // acrescenta uma época
            this.contaGeracoes++;
    
            // teste se houve algum erro duranteo treinamento e o número de geracoes
            //é menor qe o definido
        } while (!treinou && this.contaGeracoes < this.geracoesMax);
    }    // fim do método para treinamento
 
    // Método para a correção de pesos, conhecido como HEURÍSTICA
    void corrigirPeso(int i, int saida) {
        //esta parte é realmente o treinamento ou a calibragem
        pesos[0] = pesos[0] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0]);
        pesos[1] = pesos[1] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1]);
        pesos[2] = pesos[2] + (1 * (matrizAprendizado[i][2] - saida) * (-1));
 
    }
 
    void testar() { //colocar em prática o modelo rna treinado para reconhecer

        System.out.println(" Teste 01 para 0 and 0 " + executar(0, 0));
 
        System.out.println(" Teste 02 para 0 and 1 " + executar(0, 1));
 
        System.out.println(" Teste 03 para 1 and 0 " + executar(1, 0));
 
        System.out.println(" Teste 05 para 1 and 1 " + executar(1, 1));
 
    }
 
    public static void main(String[] arguments) {
 
        Perceptron_And perceptron = new Perceptron_And();
 
        perceptron.treinar();
 
        System.out.println("Para aprender o algoritmo treinou " + perceptron.contaGeracoes + " geracoes! \n ");
 
        perceptron.testar();
 
    }
}