# iFace

**Update 30/03** -
Implementadas as funcionalidades:
  1. Criação de Conta
  2. Criação/Edição de Perfil
  3. Inicio da solicitação de amizade
 
 
**Update 05/04** -
Implementadas as funcionalidades:

  3. Adição de amigos
  4. Envio de mensagens
  5. Criação de comunidades
  6.  Adição de membros (às comunidades)
  7.  Recuperar informações sobre um determinado usuário
  8.  Remoção de conta
  9.  Envio de mensagens no feed
  10.  Controle da visualização do feed de noticias


**Update 03/05 -**
Feitas as seguintes refatorações:
  1. Inclusão de herança nas entidades User(pai abstrata) e UserData(filha).
  2. Inclusão de herança nas entidades Message(pai abstrata), PostMessage e TextMessage (filhas).
  3. Transformação da entidade Feed em uma interface que é implementada em UserFeed, CommunityFeed e MessageFeed.

       3.1. Feed recebe um tipo genérico que é definido nas classes que a implementam.
  4. Alteração de todos os Arraylist para aceitar um tipo definido (anteriormente usava-se casts para pegar os objetos na lista).
  5. Divisão das classes no diretorio "Pages" entre "Pages" e "Controllers".

**Update 18/05 -**
Implementado em sala a inclusão de tratamento de exceções nas funcionalidades:
1. Menu principal
2. Menus de Feed, Adição de Amigos, Comunidade, Update das informações do usuário, Login e Cadastro.

**Update 26/05 -**
Finalização do tratamento de exceções:
1. Criação da validação de inputs
2. Criação da exceção para inputs inválidos (InvalidInputException)
3. Adição da exceção de inputs inválidos nas funcionalidades 

   - Cadastro
   - Update profile
4. Ajustes nos menus para entradas númericas incorretas.