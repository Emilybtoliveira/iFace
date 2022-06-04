# Code Smells

_Identificação e descrição de code smells no projeto_


### Funcionalidade: Menu principal

- **Métodos que fazem demais**
  
      i. Muitas variáveis "temporárias".
  ```java
  //variáveis de objetos
  List<User> users = new ArrayList();
  User current_user = new UserData();
  UserFeed mainPublicFeed = new UserFeed();

  SignIn signInPage = new SignIn(users);
  UpdateProfile updateProfilePage = new UpdateProfile(users);
  MainMenu mainMenuPage = new MainMenu();
  LogIn logInPage = new LogIn(users);
  MessagingService messagingServicePage = new MessagingService(users);
  CommunityManagement communityManagementPage = new CommunityManagement(users);
  FeedService feedServicePage = new FeedService(mainPublicFeed);
  FriendsManagement friendsManagementPage = new FriendsManagement(users);
  ```
  ###### _Main.java_

      ii. Quantidade extensa de condicionais.

  ```java
  op = mainMenuPage.Menu(current_user);
  //SignIn
  if (op.equals("signin")) {
      current_user = signInPage.Menu();
      //users.add(current_user);
      //signInPage.printAllUsers();
  } else if(op.equals("login")){
      current_user = logInPage.Menu();
  }
  [...]
  else{
      System.out.println("Isso não é uma opção.\n");
  }
  ```
  ###### _Main.java_

  ```java
  switch (op) {
          case 1:
              return "update";
          case 2:
              return "friendsmanage";
          [...]
          default:
              return "";
      }
  }
  ```

  ###### _MainMenu.java_



### Funcionalidade: Menus das funcionalidades UpdateProfile, FriendsManagement, FeedService e CommunityManagement

- **Métodos que fazem demais**
  1. Mistura entre menu, entrada de usuário e chamada de controller, além de quantidade grande de condicionais.

```java
System.out.println("\nEssas são suas informações cadastradas:");
System.out.println(current_login);

while (true){
    try {
        System.out.println("Qual informação você quer modificar?\n1. Login\n2. Senha\n3. Nome\n4. Nenhuma");

        int op = input.nextInt();

        if(op > 0 && op < 5) {
            System.out.println("\nDigite 0 a qualquer momento para cancelar a operação.");

            if (op == 1) {
                login = signInProcedure.loginInput();
                if (!login.equals("")) {
                    updateProfileController.updateLogin(current_login, login);
                }
            } else if (op == 2) {
            [...]
            }
```

###### UpdateProfile.java



```java
System.out.printf("Escolha uma opção:\n1. Ver minhas solicitações recebidas\n" +
        "2. Enviar nova solicitação de amizade.\n3. Ver minhas amizades\nDigite qualquer outro numero para cancelar a operação.\n");
op = input.nextInt();

//Ver minhas solicitações
if (op == 1) {
    friendsController.manageFriendsRequests(current_user);
}
//Solicitar nova amizade
else if (op == 2) {
    System.out.println("Insira o login do amigo que quer adicionar:");
    String friend = input.next();

    friendsController.newFriendRequest(current_user, friend);
[...]
```

###### FriendsManagement.java



```java
System.out.printf("Escolha uma opção:\n1. Ver feed\n2. Criar novo post\n3. Ver meus posts\nDigite qualquer outro número para cancelar a operação.\n");
op = input.nextInt();

if (op == 1) {
    feedController.printFeed(current_user);
} else if (op == 2) {
    input.nextLine();
    System.out.print("Escreva o conteúdo do post: ");
    String content = input.nextLine();

    while(true) {
        try {
            System.out.print("1. Post público para a rede\n2. Post privado para os meus amigos\nDigite qualquer número para cancelar a operação: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    new_post = new PostMessage(current_user, "public", content);
                    feedController.newPublicPost(new_post);
				[...]
```

###### feedService.java



```java
System.out.println("Escolha uma opção:\n1. Ver minhas comunidades\n2. Criar nova comunidade");
op = input.nextInt();

if (op == 1) {
    int n_coms = communityController.showCommunities(current_user);

    if (n_coms == 0) {
        return;
    }
    [...]
```

###### CommunityManagement.java



### Funcionalidade: todas

> Code smells pelo código como um todo.

- **Código repetido**

  1. Exceptions

     ```java
     catch (InputMismatchException e){
         input.next(); //limpa o buffer
         System.out.println("Você precisa inserir um número.\n");
     }
     ```

     ###### CommunityManagement.java, FeedService.java, FriendsManagement.java, MessagingService.java, UpdateProfile.java, MainMenu.java

     ```java
     } catch (InvalidInputException e){
         System.out.println(e.getMessage());
     }
     ```

     ###### SignIn.java

     

[^ Concluido em]: 
