/**
 * Classe que representa um estado ou indivíduo de um problema para o AG
 * @author alexandrezamberlan
 */
class Cromossomo implements Comparable<Cromossomo> {

    /**
     * atributo que armazena uma palavra qualquer gerada aleatoriamente
     */
    StringBuffer valor; 
    /**
     * variável que armazena o fitness de um estado/cromossomo/indivíduo. Quanto maior seu valor, mais próxima da solução
     */
    int aptidao;
    /**
     * atributo que converte a aptidao/fitness para um valor sobre 100 (porcentagem)
     */
    int porcentagemAptidao; //para o método de seleção roleta

    /**
     * Construtor que recebe um valor/palavra qualquer e a palavra final, calculando o fitness desse indivíduo (valor/palavra)
     * @param valor palavra ou valor ou estado ou indivíduo
     * @param estadoFinal palavra ou valor ou indivíduo que se deseja gerar
     */
    public Cromossomo(StringBuffer valor, String estadoFinal) {
        this.valor = valor;
        this.aptidao = calcularAptidao(estadoFinal);
    }

    /**
     * Método que recebe um estado/indivíduo e retorna sua aptidão, ou seja, quão próximo o estado está da solução
     * @param estadoFinal palavra ou valor ou indivíduo que se deseja gerar
     * @return o valor da aptidao do estado
     */
    
    int calcularAptidao(String estadoFinal) {
        int nota = 0;
        for (int i = 0; i < estadoFinal.length(); i++) {
            if (this.valor.toString().contains(estadoFinal.charAt(i) + "")) {
                nota += 5;
            }
            if (this.valor.toString().charAt(i) == estadoFinal.charAt(i)) {
                nota += 50;
            }
        }
        return nota;
    }

    /**
     * Método sobreescrito para ser utilizado na ordenação, em que o critério da ordenação (decrescente) é a aptidao
     * @param cromossomo representa o indivíduo, aqui, uma palavra
     * @return -1 se está ordenado decrescente
     */
    @Override
    public int compareTo(Cromossomo cromossomo) {
        if (this.aptidao > cromossomo.aptidao) {
            return -1;
        }
        return 1;
    }

    /**
     * Método sobreescrito para ser utilizado no método add de uma lista, garantindo que só objetos tipo Cromossomo seja
     * inseridos na lista, tendo como referência o atributo valor que representa a palavra/indivíduo
     * @param o objeto inserido na lista
     * @return true se o objeto inserido na lista é do tipo Cromossomo
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Cromossomo) {
            Cromossomo c = (Cromossomo)o;
            return this.valor.equals(c.valor);
        }
        return false;
    }
}
