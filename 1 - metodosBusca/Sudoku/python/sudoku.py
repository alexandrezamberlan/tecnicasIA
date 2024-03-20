class Sudoku:
        
    def popular_do_arquivo(self, nome_arquivo_sudoku):
        try:
            arquivo = open(nome_arquivo_sudoku, "r")
            conteudo = arquivo.read()
            
            linhas = conteudo.split("\n")
            self.dimensao = len(linhas)
            self.matriz = []         

            for linha in linhas:
                self.matriz.append(linha)                
            
            arquivo.close()

            return True
        except Exception as e:
            print(e)
            return False

    def exibir_sudoku(self,frase):
        print(frase)
        for i in range(0, self.dimensao):
            if (i % 3 == 0 and i != 0):            
                print("---------------------")
            for j in range(0, self.dimensao):
                if (j % 3 == 0 and j != 0):                
                    print("|",end=" ")    
                print(self.matriz[i][j], end=" ")
            print()
        
    def numero_esta_na_linha(self, numero, linha):    
        for i in range(0, self.dimensao):       
            if (self.matriz[linha][i] == numero):            
                return True        
        return False

    def numero_esta_na_coluna(self, numero, coluna):    
        for i in range(0, self.dimensao):       
            if (self.matriz[i][coluna] == numero):            
                return True        
        return False
    
    def numero_esta_no_box(self, numero, linha, coluna):
        linhaBoxLocal = linha - (linha % 3)
        colunaBoxLocal = coluna - (coluna % 3)
        for i in range(linhaBoxLocal, linhaBoxLocal + 3):        
            for j in range(colunaBoxLocal, colunaBoxLocal + 3):
                if (self.matriz[i,j] == numero):                
                    return True
        return False


    def numero_esta_no_lugar_certo(self, numero, linha, coluna):
        return (not numero_esta_na_linha(numero, linha)) and (not numero_esta_na_coluna(numero, coluna)) and (not numero_esta_no_box(numero, linha, coluna))
    

    def resolver_sudoku(self):    
        for linha in range(0, self.dimensao):        
            for coluna in range(0, self.dimensao):                    
                if (self.matriz[linha][coluna] == 0):                
                    for tentandoNumero in range(1, self.dimensao+1):                    
                        if (numero_esta_no_lugar_certo(tentandoNumero, linha, coluna)):                        
                            self.matriz[linha][coluna] = tentandoNumero
                            
                            if (resolver_sudoku()):                
                                return True                            
                            else:                            
                                self.matriz[linha][coluna] = 0                            
                    return False        
        return True
    


