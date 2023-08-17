from sudoku import Sudoku

nome_arquivo_sudoku = "sudoku.txt"#input("Qual o nome (caminho) do arquivo com um cenário sudoku inicial? ")
solucao = Sudoku()

if ( solucao.popular_do_arquivo(nome_arquivo_sudoku) ):           
    solucao.exibir_sudoku("Arquivo de cenário localizado!")

    if (solucao.resolver_sudoku()): 
        solucao.exibir_sudoku("\n\nCenário resolvido com sucesso!")        
    else:
        print("Cenário não resolvido! A configuração inicial do sudoku está complexa!")
    
else:
    print("Arquivo não localizado")            

    

