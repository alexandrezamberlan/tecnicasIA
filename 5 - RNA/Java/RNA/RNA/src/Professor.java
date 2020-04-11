/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yves
 */
public class Professor {

    public String nome;
    public String disciplina;
    public int[] vDisciplina;
    public int[] vNome;

    public Professor(String nome, String disciplina, int[] vNome, int[] vDisciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.vDisciplina = vDisciplina;
        this.vNome = vNome;
    }

}
