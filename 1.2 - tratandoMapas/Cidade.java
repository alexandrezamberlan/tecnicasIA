public class Cidade {
    public int indice;
    public String nome;
    public int estimativa;

    public Cidade(int indice, String nome, int estimativa) {
        this.indice = indice;
        this.nome = nome;
        this.estimativa = estimativa;
    }

    @Override
    public String toString() {
        return this.indice + ": " + this.nome + ". Custo estimado: " + this.estimativa;
    }
    
}
