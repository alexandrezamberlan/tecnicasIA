namespace Sudoku
{
    class Principal
    {
        public static void Main(String[] args)
        {
            string nomeArquivo;

            //Console.Write("Caminho e nome do arquivo: ");
            nomeArquivo = "C:\\Users\\laboratorio\\Downloads\\sudoku1.txt";

            Sudoku solucao = new Sudoku(nomeArquivo);
            solucao.exibir();

            if (solucao.resolver(1) == true)
            {
                Console.WriteLine("Solução");
                solucao.exibir();
                Console.WriteLine("Chamadas recursivas: " + solucao.TotalChamadasRecursivas);
            }
            else
            {
                Console.WriteLine("Solução não encontrada!");
            }
        }        
    }
}