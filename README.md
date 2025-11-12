# Pokémon App - 2º Versão - Kotlin Android
## Lucas Lima

📱 Projeto final do desafio de desenvolvimento Android em Kotlin, consumindo a API do Pokémon (PokeAPI v2).

---

## 🎯 Descrição

Este aplicativo Android exibe uma lista de Pokémons e permite visualizar detalhes de cada um. Foi desenvolvido como desafio do curso, aplicando conhecimentos de Kotlin, RecyclerView, Retrofit, Glide/Coil, e navegação entre telas.

O app possui 3 telas principais:

1. **Splash Screen** - Tela inicial que mostra o logo/uma transição antes de abrir a lista de Pokémons.
2. **Lista de Pokémons (MainActivity)** - Exibe os Pokémons em uma RecyclerView com nome e imagem. Possui busca por nome.
3. **Detalhe do Pokémon (DetailActivity)** - Mostra informações detalhadas do Pokémon selecionado: nome, ID, imagem, tipos, altura, peso e status básicos (HP, Attack, Defense).

---

## 📝 Funcionalidades Implementadas

- ✅ Splash Screen inicial.
- ✅ Lista de Pokémons com imagens carregadas da API.
- ✅ Busca por nome (SearchView/ EditText).
- ❌ Filtros por tipo (Fire, Water, Grass, etc.) e geração (Gen I, Gen II) — Não implementado devido à complexidade da integração com a API e com o SearchView. (Eu não entendi como implementar)
- ✅ Detalhes do Pokémon ao clicar em um item da lista.
- ✅ Navegação correta entre telas.
---

## 🔧 Tecnologias Utilizadas

- Kotlin
- Android Studio
- RecyclerView
- Retrofit 2 (consumo da PokeAPI)
- Glide/Coil (carregamento de imagens)
- ConstraintLayout
- LiveData / ViewModel (opcional dependendo da implementação)
- Coroutines (para requisições assíncronas)

---

## 🌐 APIs Utilizadas

- **Lista de Pokémons:**  
`https://pokeapi.co/api/v2/pokemon?limit=100`

- **Detalhe do Pokémon:**  
`https://pokeapi.co/api/v2/pokemon/{id ou nome}`

Documentação oficial: [PokeAPI v2](https://pokeapi.co/docs/v2)

---

## 📸 Apresentação da Aplicação

[Assista ao vídeo no YouTube](https://youtube.com/shorts/as41jWC8mAM?feature=share)
  
---

## 🚀 Como Executar

1. Clone este repositório:
```bash
git clone https://github.com/erolkss/ProjectPokemonVenturus.git

```
## 🚀 2º Versão
Essa versão será atualizada após a data de entrega do projeto final (09/11 - 23:59)

Documentação oficial: [ProjectPokemonVenturus v2](https://github.com/erolkss/ProjectPokemonVenturus-v2)


