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


**Update 03/05**
Feitas as seguintes refatorações:
  1. Inclusão de herança nas entidades User(pai abstrata) e UserData(filha).
  2. Inclusão de herança nas entidades Message(pai abstrata), PostMessage e TextMessage (filhas).
  3. Transformação da entidade Feed em uma interface que é implementada em UserFeed, CommunityFeed e MessageFeed.
     
       3.1. Feed recebe um tipo genérico que é definido nas classes que a implementam.
  5. Alteração de todos os Arraylist para aceitar um tipo definido (anteriormente usava-se casts para pegar os objetos na lista).
  6. Divisão das classes no diretorio "Pages" entre "Pages" e "Controllers".
