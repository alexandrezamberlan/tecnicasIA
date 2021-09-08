public class Cidade {
    public String nome;
    public int estimativa;

    public Cidade(String nome, int estimativa) {
        this.nome = nome;
        this.estimativa = estimativa;
    }

    @Override
    public String toString() {
        return this.nome + ". Custo estimado: " + this.estimativa;
    }
    
}
