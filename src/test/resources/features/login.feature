# encoding: utf-8
# language: pt
@login @regression
Funcionalidade: Feature para testar a funcionalidade de login na plataforma de teste automationpractice

  Contexto:
    Dado que eu acesso a url de login

  @login_invalido
  Esquema do Cenário: Simular condições de login inválido
    Quando não preencho <userEmail> e ou <userPassword> corretamente
    E eu clico no botão entrar
    Então deve ser uma mensagem bloqueando acesso <message>
    Exemplos:
      |userEmail              | userPassword | message                        |
      |""                     | "12345"      | "An email address required."|
      |"automation1@teste.com"| ""           | "Password is required."|

  @login_com_sucesso
  Cenário: Um usuário cadastrado deve ser capaz de acessar a plataforma utilizando seu email e senha
    Quando preencho email e senha
    E eu clico no botão entrar
    Então devo ser redirecionado à página de minha conta e apresentar o nome do usuário logado corretamente