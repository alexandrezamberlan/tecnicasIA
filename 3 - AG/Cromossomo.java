class Cromossomo implements Comparable<Cromossomo> {

    StringBuffer valor;
    
    int aptidao;
    int porcentagemAptidao; //para o método de seleção roleta

    public Cromossomo(StringBuffer valor, String estadoFinal) {
        this.valor = valor;
        this.aptidao = calcularAptidao(estadoFinal);
    }

    //heuristica
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

    @Override
    public int compareTo(Cromossomo cromossomo) {
        if (this.aptidao > cromossomo.aptidao) {
            return -1;
        }
        return 1;
    }
}
