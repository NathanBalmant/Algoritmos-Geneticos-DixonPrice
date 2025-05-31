# Algoritmo Genético - Função Dixon-Price

Este projeto implementa um algoritmo genético (AG) para otimização contínua da função Dixon-Price, uma função matemática multimodal frequentemente utilizada como benchmark em problemas de otimização global.

## 🧠 O que são Algoritmos Genéticos?

Algoritmos Genéticos (AGs) são métodos de busca e otimização inspirados no processo de seleção natural da biologia. Eles operam sobre uma população de soluções (indivíduos), aplicando operadores como:

- **Seleção**: escolha dos melhores indivíduos;
- **Recombinação (crossover)**: combinação de genes de dois pais para formar filhos;
- **Mutação**: pequenas alterações aleatórias nos genes para manter a diversidade.

## 🤖 Relação com Inteligência Artificial

Os AGs fazem parte da área de **Inteligência Artificial (IA)**, especialmente dentro do ramo de **algoritmos evolutivos**. Eles são amplamente utilizados em problemas de otimização difíceis de resolver por métodos tradicionais, sendo aplicados em áreas como:

- Otimização contínua e combinatória
- Robótica
- Engenharia
- Planejamento
- Aprendizado de máquina

## 📌 Objetivo

O objetivo do algoritmo é encontrar a melhor solução possível (mínimo global) da função Dixon-Price utilizando estratégias evolutivas como:

- Recombinação (crossover) BLX-α e aritmética
- Mutação gaussiana
- Seleção elitista
- Representação dos indivíduos por vetores de números reais

## 🔢 Função Dixon-Price

A função objetivo utilizada é:

```
f(x) = (x₁ - 1)² + ∑_{i=2}^{n} i * (2xᵢ² - xᵢ₋₁)²
```

- Domínio típico: cada variável ∈ [-10, 10]
- Ótimo global conhecido: **f(x) = 0** em **x = (1, 1, ..., 1)**

## 🧬 Características do AG

- **Tamanho da população**: 100
- **Elitismo**: 4 melhores indivíduos preservados
- **Número de gerações**: 2000
- **Mutação**: adição de ruído gaussiano com `taxa = 0.05`
- **Recombinação**: BLX-α adaptativa (`α ~ N(0.3, 0.1)`), com versão aritmética também disponível
- **Codificação**: vetores `double[]`

## 📁 Estrutura do Projeto

- `Ind` – Interface de indivíduos
- `DixonPriceInd` – Implementação dos indivíduos para Dixon-Price
- `DixonPriceIndFactory` – Fábrica de indivíduos
- `AgFga` – Algoritmo Genético com seleção elitista e operadores genéricos
- `Principal` – Classe principal que executa o AG e salva resultados em um arquivo `.txt`

## 📄 Exemplo de Saída (resultado.txt)

```
Geração 2000 | Melhor: [1.0000, 1.0000, 1.0000, ..., 1.0000] | Avaliação: 0.000000
```

## 🛠️ Como Executar

1. Compile todos os arquivos `.java`:

```bash
javac *.java
```

2. Execute a classe principal:

```bash
java Principal
```

3. O resultado será salvo em `resultado.txt`.

## 📚 Referências

- Dixon, L.C.W., & Price, R.S. (1987). "The global optimization problem: an introduction".
- Molga & Smutnicki (2005). "Test functions for optimization needs".

## 📌 Observações

Você também pode adaptar o código facilmente para outras funções de benchmark, como Langermann ou Schwefel, trocando apenas a classe de indivíduo (`Ind`).
