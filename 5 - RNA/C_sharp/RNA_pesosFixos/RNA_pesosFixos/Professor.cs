using System;
namespace RNA_pesosFixos
{
    public class Professor
    {
        string nome;
        string area;
        int[] vetor_nome;
        int[] vetor_area;

        public string Nome { get => nome; set => nome = value; }
        public string Area { get => area; set => area = value; }
        public int[] Vetor_nome { get => vetor_nome; set => vetor_nome = value; }
        public int[] Vetor_area { get => vetor_area; set => vetor_area = value; }

        public Professor(string nome, string area, int[] vetor_nome, int[] vetor_area)
        {
            this.Nome = nome;
            this.Area = area;
            this.Vetor_nome = vetor_nome;
            this.Vetor_area = vetor_area;
        } 
    }
}
