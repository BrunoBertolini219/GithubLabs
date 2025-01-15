# Desafio LuizaLabs

<img align="center" alt="Android Studio" src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" />
<img align="center" alt="Kotlin" src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white" />


## 👨‍💻Tecnologias Utilizadas
* Modularização por feature
* Clean Architecture
* MVI
* Navigation Component
* Dagger
* Retrofit2
* Gson
* RxJava
* LiveData
* ViewBinding
* Glide

---------------------

## Sobre a API 
API utilizada para consumo de dados relacionados ao GitHub.

API: https://api.github.com/

---------------------

## Arquitetura do Projeto

A divisão dos módulos é a seguinte:
* app: Contém os assets relacionados ao ícone e launcher do aplicativo.
* base: Inclui componentes compartilhados, helpers, extensões, recursos, além da splash screen.
* github: Módulo responsável pela funcionalidade do GitHub.

O projeto utiliza arquitetura modular orientada a funcionalidades, facilitando a escalabilidade e manutenção 
do código. Cada módulo segue os princípios da Clean Architecture, garantindo separação de responsabilidades. 
A estrutura adota MVI, injeção de dependência com Dagger e manipulação assíncrona com RxJava.

---------------------

## Melhorias Desejáveis

* Testes (Unitários e Instrumentados)
* Cache dos dados
* Paginação dos dados
* Modulos de Assets e Design system
* Extração de configurações compartilhadas do Gradle para um .gradle separado
