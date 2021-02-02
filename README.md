### Projeto de automação - Web ###

Olá Analistas e recrutadores, 

Espero que vocês consigam navegar com tranquilidade por esse projeto. Estou seguindo o padrão POM para estruturação do projeto.

Neste exemplo utilizo a configuração de environments, onde trabalhamos atualmente temos a necessidade de executar um mesmo projeto em diferentes ambientes e portanto temos arquivos .properties onde as variáveis específicas de cada ambiente são armazenadas. No exemplo do projeto temos somente um ambiente, mas a ideia era mostrar a vocês que conheço esse tipo de implementação.

Um outro e último ponto também, o relatório de execução dos testes funcionais é armazenado na pasta target >> cucumber. Ele pode ser visto no browser com o 
arquivo index.html. Também inclui no repositório um .zip chamado "Report.zip" que contém essa pasta com a última execução que rodei aqui caso queiram vê-la 
antes de executar o projeto.

Espero que gostem, qualquer dúvida fiquem a vontade para entrar em contato - israfaioli@gmail.com

### Pré-requesitos ###

* Java 8.

* Maven

### Tecnologias Utilizadas ###

* Selenium WebDriver
* Cucumber
* Junit
* Lombok
* Maven
* Java

### Setup necessário ###


* Instalar o java 8.

  ```
  sudo apt-get install oracle-java8-installer
  ```

* Instalar o maven.

  ```
  sudo apt install maven
  ```

* Instalar o plugin do cucumber no eclipse.

  Ir no marketplace > Buscar por cucumber > Instalar "cucumber eclipe plugin"


### Estrutura do projeto ###

* Common - Classes onde terão métodos em comum que poderão ser usadas por outras classes.
* Common > Base - A base e onde temos os principais métodos onde utilizamos na automação.
* Pages - Onde será mapeado os elementos e funções.
* Models - Onde são criados os objetos que podem ser usados na automação.
* Runners - Suite principal para execução dos testes (API e Funcionais).
* Steps - Onde ficara os passos da automação.
* Utils - Classes com algumas funções uteis para o projeto.
* Features - Onde fica as funcionalidades do projeto.
* config.properties - Onde definimos variáveis para se usar no projeto.

### Rodando o projeto ###

Em alguns casos, a depender de suas configurações locais, pode ser necessário executar o projeto com permissões de administrador adicionando "sudo" antes dos comandos abaixo.

* Acessa a pasta do projeto
* Rode o seguinte comando do maven:
```

## Execução dos Testes Funcionais ##

- O projeto executa com chrome, não headless, por default:

```
mvn clean test -Dtest=CucumberRunner -Dbrowser=chrome -Denvironment=environmentA
```

* Rodar em headless: 

```
 mvn clean test -Dtest=CucumberRunner -Dbrowser=chromeHeadless -Denvironment=environmentA
```

* Rodar com firefox

```
 mvn clean test -Dtest=CucumberRunner -Dbrowser=firefox -Denvironment=environmentA
```

* Rodar uma determinada feature pela tag

```
 mvn clean test -Dtest=CucumberRunner -Dbrowser=chromeHeadless -Denvironment=environmentA -Dcucumber.options="-t @nome_da_tag"
