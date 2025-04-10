pai = [6,7,8,9,10]
mae = [1,2,3,4,5]

#primeira metade do pai + segunda metade da mae
primeira_metade = pai[0 : int(len(pai)/2)] 
segunda_metade = mae[int(len(mae)/2) : len(mae)]

filho1 = primeira_metade + segunda_metade
print(filho1)

#primeira metade da mae + segunda metade do pai
primeira_metade = mae[0 : int(len(mae)/2)] 
segunda_metade = pai[int(len(pai)/2) : len(pai)]
filho2 = primeira_metade + segunda_metade
print(filho2)



