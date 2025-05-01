### **Atividade: Conversão de Descrição de Fantasia para Prolog**

**Contexto:**

Em um mundo de fantasia, temos diferentes tipos de seres que vivem em um reino mágico. Cada tipo de ser tem características específicas, como raça, classe e habilidades. O objetivo dessa atividade é transformar uma base de fatos descrita em português para uma base de fatos e regras em Prolog.

**Descrição:**

No reino, existem **arquétipos de personagens** que podem ser humanos, elfos, anões, duendes, guerreiros, arqueiros e mágicos. Além disso, alguns personagens têm habilidades especiais ou características que podem ser usadas para determinar interações entre eles, como:

- **Arqueiros**: têm habilidade de atacar à distância.
- **Guerreiros**: têm habilidades de combate corpo a corpo.
- **Mágicos**: usam feitiços para ataques mágicos.
- **Anões**: possuem grande força física e resistência.
- **Elfos**: são rápidos e habilidosos em combate à distância.
- **Duendes**: são travessos e rápidos, mas menos fortes.

Cada ser pode ser descrito com as seguintes características:
- **raça** (humano, elfo, anão, duende),
- **classe** (guerreiro, arqueiro, mágico),
- **habilidade** (distância, combate corpo a corpo, feitiço).

### **Base de Fatos em Português:**

1. O **Gandalf** é um **mágico** da raça **humano**.
2. O **Legolas** é um **arqueiro** da raça **elfo**.
3. O **Thor** é um **guerreiro** da raça **anão**.
4. O **Gimli** é um **guerreiro** da raça **anão**.
5. O **Bilbo** é um **duende** e não tem classe definida, mas é ágil.
6. O **Frodo** é um **humano** e é bom em combate corpo a corpo.
7. O **Aragorn** é um **guerreiro** da raça **humano**.
8. O **Gollum** é um **duende** e se destaca pela agilidade.

---

### **Perguntas**

1. **Parte 1**: Converta a base de fatos descrita acima para uma base de fatos em Prolog. Exemplo de formato:
   ```prolog
   personagem(gandalf, humano, magico).
   personagem(legolas, elfo, arqueiro).
   personagem(thor, anao, guerreiro).
   personagem(gimli, anao, guerreiro).
   personagem(bilbo, duende, nenhum).
   personagem(frodo, humano, guerreiro).
   personagem(aragorn, humano, guerreiro).
   personagem(gollum, duende, nenhum).
   ```

2. **Parte 2**: Crie regras que definem quais personagens são **bons em combate à distância**. Por exemplo:
   - **Arqueiros** e **Elfos** são bons em combate à distância.
   - **Guerreiros** e **Anões** são melhores em combate corpo a corpo.

   A regra para saber se um personagem é bom em combate à distância pode ser algo como:
   ```prolog
   bom_em_combate_distancia(X) :- personagem(X, elfo, _).
   bom_em_combate_distancia(X) :- personagem(X, _, arqueiro).
   ```

3. **Parte 3**: Defina uma regra para determinar se um personagem é **forte** (baseado nas classes e raças). Um **guerreiro** ou **anão** pode ser considerado forte devido à sua habilidade em combate físico.
   - **Guerreiros** são sempre fortes.
   - **Anões** são fortes pela sua resistência.

   A regra para saber se um personagem é forte pode ser assim:
   ```prolog
   forte(X) :- personagem(X, anao, _).
   forte(X) :- personagem(X, _, guerreiro).
   ```

4. **Parte 4**: Crie uma regra para determinar se um personagem pode **usar magia**. Apenas **mágicos** podem usar magia, então:
   - **Mágicos** são os únicos que têm essa habilidade.

   A regra para saber se um personagem pode usar magia pode ser:
   ```prolog
   pode_usar_magia(X) :- personagem(X, _, magico).
   ```

### **Desafio Extra (opcional):**

1. Crie uma regra que determine se dois personagens **se dão bem** em batalha, considerando suas habilidades. Por exemplo:
   - Um **guerreiro** pode derrotar um **mágico** devido à sua resistência no combate físico.
   - Um **arqueiro** pode derrotar um **guerreiro** à distância.
   - Um **mágico** pode derrotar um **guerreiro** com feitiços.
   
   A regra poderia ser algo assim:
   ```prolog
   se_dao_bem(X, Y) :- personagem(X, _, guerreiro), personagem(Y, _, magico).
   se_dao_bem(X, Y) :- personagem(X, _, arqueiro), personagem(Y, _, guerreiro).
   se_dao_bem(X, Y) :- personagem(X, _, magico), personagem(Y, _, guerreiro).
   ```

### **Instruções adicionais para os alunos:**

- **Passo 1**: Primeiramente, converta as descrições dos personagens para fatos em Prolog.
- **Passo 2**: Defina regras que fazem sentido dentro do contexto descrito (habilidades de combate, uso de magia, etc.).
- **Passo 3**: Teste suas regras com consultas (queries) no Prolog para verificar se estão funcionando corretamente.
  
Exemplo de consulta:
```prolog
?- bom_em_combate_distancia(legolas).
true.

?- forte(thor).
true.

?- pode_usar_magia(gandalf).
true.
```

---
