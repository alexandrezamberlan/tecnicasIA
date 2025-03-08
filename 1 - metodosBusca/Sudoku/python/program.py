from sudoku import Sudoku
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("file", help="Nome do arquivo com o cenário inicial do sudoku")
args = parser.parse_args()


if __name__ == "__main__":
    if args.file:
        nome_arquivo_sudoku = args.file
    else:
        nome_arquivo_sudoku = "sudoku.txt"
    
    solucao = Sudoku()

    if ( solucao.popular_do_arquivo(nome_arquivo_sudoku) ):           
        solucao.exibir_sudoku("Arquivo de cenário localizado!")

        if (solucao.resolver_sudoku()): 
            solucao.exibir_sudoku("\n\nCenário resolvido com sucesso!")        
        else:
            print("Cenário não resolvido! A configuração inicial do sudoku está complexa!")
        
    else:
        print("Arquivo não localizado")

        

