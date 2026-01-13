# Fruit Informer 🍎🍌🍇

Universidade Federal do Ceará
Disciplina: Programação para Dispositivos Móveis
Professor: Windson Viana
Autor: Rodrigo Rodrigues Santos

O **Fruit Informer** é um aplicativo Android moderno construído com Jetpack Compose que permite aos usuários pesquisar informações nutricionais sobre várias frutas e gerenciar uma lista de suas favoritas.

## 📱 Funcionalidades

*   **Pesquisar Frutas:** Busque informações detalhadas sobre qualquer fruta.
*   **Informações Detalhadas:** Veja classificações taxonômicas (família, gênero, ordem) e valores nutricionais (calorias, carboidratos, proteínas, gordura, açúcar).
*   **Favoritos:** Salve suas frutas preferidas para acesso rápido posteriormente.
*   **UI Moderna:** Uma interface de usuário limpa e responsiva construída com Material Design 3.

## 🖼️ Capturas de Tela
### Tela de Pesquisa
<img src="./screenshots/search_screen.png">

### Tela de Detalhes da Fruta
<img src="./screenshots/fruit_detail_screen.png">

### Tela de Favoritos
<img src="./screenshots/favorites_screen.png">

### Vídeo do App
![Vídeo de Demonstração](./screenshots/video.webm)

## 🛠️ Tecnologias Utilizadas

*   **Linguagem:** [Kotlin](https://kotlinlang.org/)
*   **Framework de UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Arquitetura:** MVVM (Model-View-ViewModel)
*   **Injeção de Dependência:** [Hilt](https://dagger.dev/hilt/)
*   **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
*   **Consumo de API RESTful:** [Retrofit](https://square.github.io/retrofit/) para fazer requisições REST e [Gson](https://github.com/google/gson) para análise de JSON.
*   **Concorrência:** Kotlin Coroutines & Flow
*   **Armazenamento Local:** SharedPreferences (para salvar favoritos)
*   **API:** [Fruityvice API](https://www.fruityvice.com/)

## 🚀 Como Começar

### Pré-requisitos
*   Android Studio Ladybug ou mais recente.
*   JDK 17 ou mais recente.

### Instalação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/yourusername/fruitInformer.git
    ```
2.  **Abra no Android Studio:**
    Abra o Android Studio e selecione "Open an existing Android Studio project", apontando para o diretório clonado.
3.  **Compile e Execute:**
    Aguarde a sincronização do Gradle e, em seguida, execute o aplicativo em um emulador ou dispositivo físico.

## 📂 Estrutura do Projeto

*   `di`: Módulos Hilt para injeção de dependência.
*   `ui/screens`: Funções Composable que representam as telas do aplicativo (Pesquisa, Detalhes, Favoritos).
*   `ui/viewmodel`: ViewModels que gerenciam o estado da UI e a lógica de negócios.
*   `data/remote`: Configuração de rede e interfaces de API.
*   `data/repository`: Implementação do padrão Repository para manipulação de dados.
*   `data/model`: Classes de dados que representam a resposta da API.
