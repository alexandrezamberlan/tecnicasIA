import random

from cromossomo import Cromossomo
from util import Util

class AG:
    @staticmethod
    def gerar_populacao(populacao, tamanho_populacao, estado_final):
        for i in range(tamanho_populacao):
            populacao.append(Cromossomo(Util.gerar_palavra(len(estado_final)), estado_final))
    
    @staticmethod
    def exibir(populacao):
        for i in populacao:
            print(i)

    def selecionar_por_torneio(populacao, nova_populacao, taxa_selecao):
    #     OBS.: a populacao nao pode ser pequena e nem a taxa de selecao ser muito alta

        torneio = []
        
        Cromossomo c1, c2, c3; //elementos sorteados para o torneio
        Cromossomo selecionado;

        # calcular quantos devem ser selecionados a partir do tamanho da populacao com a taxa_selecao
        # populacao.size()	->	100
        # qtd_selecionados	-> 	taxa_selecao
        qtd_selecionados = taxa_selecao * len(populacao) / 100

        nova_populacao.append(populacao[0]) #elistismo
        
        i = 1
        while (i <= qtd_selecionados):
            c1 = populacao[ random.randrange( len(populacao) ) ]
            
            while (True):            
                c2 = populacao[ random.randrange( len(populacao) ) ]
                if c2 != c1:
                    break
            
            while (True):            
                c3 = populacao[ random.randrange( len(populacao) ) ]
                if c3 != c2 != c1:
                    break

            torneio.append(c1)
            torneio.append(c2)
            torneio.append(c3)
            torneio.sort() #o primeiro é o mais apto

            selecionado = torneio[0]

            if not selecionado in nova_populacao:
                nova_populacao.append(selecionado)
                i += 1
            
            torneio.clear() #FALTOU LIMPAR A LISTA torneio para a próxima rodada      

    
    @staticmethod
    # static void selecionarPorRoleta(populacao, novaPopulacao, int taxa_selecao) {
    #     //método da roleta
    #     //calcular a aptidao total
    #     int aptidaoTotal = 0;
    #     for (int i = 0; i < populacao.size(); i++) {
    #         aptidaoTotal += populacao.get(i).aptidao;
    #     }
    #     System.out.println("Aptidão total: " + aptidaoTotal);

    #     //aptidaoTotal -> 100
    #     //aptidao      -> porcentagemAptidao
    #     //porcentagemAptidao = aptidao * 100 / aptidaoTotal;
    #     for (int i = 0; i < populacao.size(); i++) {
    #         populacao.get(i).porcentagemAptidao = populacao.get(i).aptidao * 100 / aptidaoTotal;
    #         if (populacao.get(i).porcentagemAptidao == 0) {
    #             populacao.get(i).porcentagemAptidao = 1;
    #         }
    #     }
        
    #     sorteio = new ArrayList<>();
    #     for (int i = 0; i < populacao.size(); i++) {
    #         for (int j = 0; j < populacao.get(i).porcentagemAptidao; j++) {
    #             sorteio.append(populacao.get(i));
    #         }
    #     }

    #     System.out.println("Tamanho da lista sorteio: " + sorteio.size());

    #     Random gerador = new Random();
    #     int posicaoSorteio;
        
    #     //populacao.size()	->	100
    #     //qtd_selecionados	-> 	taxa_selecao
    #     int qtd_selecionados = taxa_selecao * populacao.size() / 100;
        
    #     //elitismo
    #     novaPopulacao.append(populacao.get(0));
    #     Cromossomo selecionado;

    #     for (int i = 1; i <= qtd_selecionados; i++) {
    #         posicaoSorteio = gerador.nextInt(sorteio.size());
            
    #         try {
    #             selecionado = sorteio.get(posicaoSorteio);

    #             novaPopulacao.append(selecionado);

    #             while (sorteio.remove(selecionado)){} //controle dos visitados
    #         } catch (Exception e) {
    #             System.out.println("Tentou pegar uma posição inválida do sorteio");
    #         }
            
    #     }
    # }

    @staticmethod
    # public static void reproduzir(populacao, novaPopulacao, int taxaReproducao, String estadoFinal) {
    #     String sPai,sMae,sFilho1,sFilho2;
    #     Random gerador = new Random();
    #     Cromossomo pai, mae;
        
    #     //calcular quantos devem ser reproduzidos a partir do tamanho da populacao com a taxaReproducao
    #     //populacao.size()	->	100
    #     //qtdReproduzido	-> 	taxaReproducao
    #     int qtdReproduzidos = taxaReproducao * populacao.size() / 100;

    #     //sFilho1 = Alexone - primeiraMetadeDoPai + segundaMetadeDaMae;
    #     //sFilho2 = Simandre - primeiraMetadeDaMae + segundaMetadeDoPai;
    #     int i = 0;
    #     do {
    #         pai = populacao.get( gerador.nextInt(populacao.size()) ); //futuramente, o pai pode ser um indivíduo bom
    #         do {
    #             mae = populacao.get( gerador.nextInt(populacao.size()) );
    #         } while (mae.equals(pai));

    #         sPai = pai.valor.toString();
    #         sMae = mae.valor.toString();
            
    #         sFilho1 = sPai.substring(0, sPai.length() / 2) + sMae.substring(sMae.length() / 2, sMae.length());
    #         sFilho2 = sMae.substring(0, sMae.length() / 2) + sPai.substring(sPai.length() / 2, sPai.length());

    #         novaPopulacao.append(new Cromossomo(new StringBuffer(sFilho1), estadoFinal)); //estadoFinal é passado para calcular aptidao do filho
    #         novaPopulacao.append(new Cromossomo(new StringBuffer(sFilho2), estadoFinal)); //estadoFinal é passado para calcular aptidao do filho
    #         i = i + 2;

    #     } while (i < qtdReproduzidos);
        
    #     //podar a novaPopulacao, retirando os excedentes do final
    #     while(novaPopulacao.size() > populacao.size()) {
    #         novaPopulacao.remove(novaPopulacao.size() - 1);
    #     }
    # }

    @staticmethod
    # public static void mutar(populacao, String estadoFinal) {
    #     Random gerador = new Random();
    #     int qtdMutantes = gerador.nextInt(populacao.size() / 5); //a qtd de mutantes será no máximo 20% da população
    #     Cromossomo mutante;
    #     int posicaoMutante;

    #     for (; qtdMutantes > 0; qtdMutantes--) {
    #         posicaoMutante = gerador.nextInt(populacao.size());
    #         mutante = populacao.get(posicaoMutante);
    #         System.out.println("vai mutar " + mutante.valor + "  " + mutante.aptidao);
    #         //mudando
    #         String valorMutado = mutante.valor.toString();
    #         char caracterMutante = mutante.valor.charAt(gerador.nextInt(mutante.valor.length()));
    #         char caracterSorteado = Util.letras.charAt(gerador.nextInt(Util.tamanho));
    #         valorMutado = valorMutado.replace(caracterMutante, caracterSorteado);          
    #         mutante = new Cromossomo(new StringBuffer(valorMutado), estadoFinal);
    #         //JOptionPane.showMessageDialog(null, "mudado " + mutante.valor + "  " + mutante.aptidao);
    #         //recalculando sua aptidao
    #         populacao.set(posicaoMutante, mutante);
    #     }
    # }