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

      i. Mistura entre menu, entrada de usuário e chamada de controller, além de quantidade grande de condicionais.

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

      i. Exceptions

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
     
<hr>
	
	 Concluido em 07/06/2022

# Padrões de projeto
_Refatoração dos Code Smells identificados utilizando Padrões de Projeto_

### Funcionalidade: Menu principal e Menus das funcionalidades UpdateProfile, FriendsManagement, FeedService e CommunityManagement

- **Métodos que fazem demais**
	
	Foi refatorado todo o processo de menu antes concentrado nas classes ``Main`` e ``MainMenu``, e nas classes especificas UpdateProfile, FriendsManagement, FeedService e CommunityManagement utilizando o padrão _Replace Conditional with Polymorphism_ em conjunto com _Extract Method_. Antes, tinha-se o seguinte código:
	
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
  [...]
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
 	    int op;
        Scanner input = new Scanner(System.in);
        boolean flag = false;

        while(!flag) {
            try {
                if (current_user.getName() == null) {

                    System.out.println("Escolha uma opcao:\n1. Fazer login\n2. Cadastrar e logar como novo usuário \n3. Sair do programa");

                    op = input.nextInt();

                    switch (op) {
                        case 1:
                            return "login";
                        case 2:
                            return "signin";
                        case 3:
                            return "quit";
                    }
                } else {
                    System.out.printf("Você está navegando como %s.\n", current_user.getLogin());
                    System.out.println("Escolha uma opção:\n1. Alterar informações do usuário atual\n" +
                            "2. Gerenciar amizades\n3. Chats\n4. Comunidades\n5. Feed\n6. Excluir minha conta\n7. Trocar de conta" +
                            "\n8. Sair do programa");

                    op = input.nextInt();

                    switch (op) {
                        case 1:
                            return "update";
                        case 2:
                            return "friendsmanage";
                        case 3:
                            return "chats"
			 [...]
	```
	###### MainMenu.java

Após a aplicação do padrão, temos a seguinte hierarquia de classes

```java
App application = new App();

System.out.println("Bem vindo ao iFace");
application.startApp();
```
###### Main.java

```java
public void startApp(){
    while(true) {
        this.app_menu.Menu();
        this.app_menu = this.app_menu.getNextMenu();

         if(app_menu == null){
            System.out.println("quiting...");
            return;
         }
    }
}
```
###### App.java

```java
public Menu(Dependencies app_dependencies){[...]}

public void Menu(){}

public int getUserChoice(){[...]}

private void setNextMenu(){[...]}

private void optionOnMap(int option, Module current_module){[...]}

public Menu getNextMenu(){}
```
###### Menu.java: classe-pai menu, da qual todas as classes Menu herdam

```java
private Module home_module;
private Menu next_menu;

public HomeMenu(Dependencies app_dependencies) {
	super(app_dependencies);
	this.home_module = new Home(app_dependencies);
}

public void Menu(){
	System.out.printf("Você está navegando como %s.\n", app_dependencies.getCurrentUser().getName());
	System.out.println(home_module.getMenu());
	int chosen_int_option = super.getUserChoice();

	this.optionOnMap(chosen_int_option);
}

private void optionOnMap(int option){
	Map menu_map = this.home_module.getMap();
	this.next_menu = (Menu) menu_map.get(option);

	if(this.next_menu == null){this.next_menu = new HomeMenu(app_dependencies);}
}


public Menu getNextMenu(){
	return this.next_menu;
}
```
###### HomeMenu.java: uma das classes que herdam Menu (_menus/_)

```java
public Home(Dependencies app_dependencies){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, new UpdateProfileMenu(app_dependencies));
        this.menu_map.put(2, new FriendsManagementMenu(app_dependencies));
        this.menu_map.put(3, new MessagingServiceMenu(app_dependencies));
        this.menu_map.put(4, new CommunityManageMenu(app_dependencies));
        this.menu_map.put(5, new FeedServiceMenu(app_dependencies));
        this.menu_map.put(6, new RemoveAccountMenu(app_dependencies));
        this.menu_map.put(7, new SwitchAccountMenu(app_dependencies));
        this.menu_map.put(8, new Menu(app_dependencies));
        //System.out.println(this.menu_map.get(3));
}

public String getMenu() {
        return "Escolha uma opção:\n1. Alterar informações do usuário atual\n" +
                "2. Gerenciar amizades\n3. Chats\n4. Comunidades\n5. Feed\n6. Excluir minha conta\n7. Trocar de conta" +
                "\n8. Sair do programa";
}

public Map getMap() {
        return this.menu_map;
}
```
###### Home.java: Menus que possuem sub-menus utilizam de "Modulos" (_modules/_) com o mesmo nome do menu a que estão atrelados. Cada módulo retorna um menu especifico e um mapeamento entre uma opção e um novo menu.

Foi também utilizado o padrão de projeto _Replace Method with Method Object_ na criação da classe ``Dependencies``. 

 	i. Code Smell

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
   ###### Main.java
   
  
	ii. Aplicação do padrão
   ```java
    private List<User> users;
    private User current_user;
    private UserFeed mainPublicFeed;


    public Dependencies(){
		this.users = new ArrayList();
		this.current_user = new UserData();
		this.mainPublicFeed = new UserFeed();
    }

    public List<User> getUsers() {
		return this.users;
    }

    public User getCurrentUser() {
		return current_user;
    }

    public void setCurrentUser(User current_user){
		this.current_user = current_user;
    }

    public UserFeed getMainPublicFeed() {
		return mainPublicFeed;
    }
   ```
   ###### modules/Dependencies.java
   
   
   
### Funcionalidade: Todas
Alguns dos Code Smells repetidos por todo o projeto que foram resolvidos com algum Padrão de Projeto.

- **Código repetido**
	
	Utilizada uma combinação dos padrões _Extract Method_ e _Pull Up Field_

      i. Code Smell

     ```java
     int op
     Scanner input = new Scanner(System.in);
     while (true) {
            try {
                System.out.printf([...]);
                op = input.nextInt();
			   [...]
			}
		     catch (InputMismatchException e){
			     input.next(); //limpa o buffer
			     System.out.println("Você precisa inserir um número.\n");
		     }
	}
     ```

     ###### CommunityManagement.java, FeedService.java, FriendsManagement.java, MessagingService.java, UpdateProfile.java, MainMenu.java
     
     	ii. Aplicação do padrão

     ```java
     public Menu(Dependencies app_dependencies){
		[...]
		this.input = new Scanner(System.in);
     }
     public int getUserChoice(){
        try{
            int op = this.input.nextInt();
            return op;
        } catch (InputMismatchException e) {
            input.next(); //limpa o buffer
            System.out.println("Você precisa inserir um número.\n");

            return -1;
        }
    }
     ```

     ###### Menu.java: possui o método ``getUserChoice()``, utilizado sempre que o usuário precisa escolher um item do menu. Assim, as classes que herdam Menu utilizam de tal método da classe-pai.


      i. Code Smell

     ```java
     public String loginInput(){
        String login;
        Scanner input = new Scanner(System.in);
        InputValidations validator = new InputValidations();

        while(true) {
            try {
                System.out.println("Digite um login:");
                login = input.nextLine();

                if (isQuit(login)){return "";}

                validator.hasSpecialChar(login);
                validator.hasTheMinimumSize(login, 4);

                if (signInController.existingLogin(login)) {
                    System.out.println("Esse login já está sendo usado. Escolha outro.\n");
                } else {
                    System.out.printf("Login escolhido: %s\n", login);
                    break;
                }
            } catch (InvalidInputException e){
                System.out.println(e.getMessage());
            }
        }
        return login;
    }
    
     public String passwordInput(){
        String password;
        Scanner input = new Scanner(System.in);
        InputValidations validator = new InputValidations();

        while(true){
            try {
                System.out.println("Digite uma senha:");
                password = input.nextLine();

                if (isQuit(password)){return "";}

                validator.hasSomeSpecialChar(password);
                validator.hasTheMinimumSize(password, 5);

                System.out.printf("Senha escolhida: %s\n", password);
                break;
            } catch (InvalidInputException e){
                System.out.println(e.getMessage());
            }
        }

        return password;
    }
     ```

     ###### SignIn.java: repetição da mesma estrutura para pegar parâmetros diferentes
     
     	ii. Aplicação do padrão

     ```java
     public String getUserInput(String display_input_request){
        String user_input;

        System.out.println(display_input_request);
        user_input = this.input.nextLine();

        return user_input;
    }
    
     public boolean isUserInputValid(String user_input, String validator_type){
        InputValidations validator = new InputValidations();

        try {
            validator.hasSpecialChar(user_input);

            if(validator_type.equals("login")) {
                validator.hasTheMinimumSize(user_input, 4);
            } else if(validator_type.equals("pass")){
                validator.hasTheMinimumSize(user_input, 5);
            }

            return true;

        } catch(InvalidInputException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getUserLogin(SignInController signInController){
        while(true) {
            String login = getUserInput("Digite um login: ");

            if (isQuit(login)){ return ""; }

            if (isUserInputValid(login, "login")) {
                if (!signInController.existingLogin(login)) {
                    System.out.printf("Login escolhido: %s\n", login);

                    return login;
                } else {
                    System.out.println("Esse login já está sendo usado. Escolha outro.\n");
                }
            }
        }
    }

    public String getUserPass(){
        while(true) {
            String password = getUserInput("Digite uma senha: ");
            if (isQuit(password)){ return ""; }

            if (isUserInputValid(password, "pass")) {
                System.out.printf("Senha escolhida: %s\n", password);

                return password;
            }
        }
    }
     ```

     ###### modules/Input.java: possui o método ``isUserInputValid()`` para remover a repetição da estrutura ``try-catch``. E do método ``getUserInput()`` para remover a repetição de entrada do usuário.
     
   O método ``getUserInput()`` também é utilizado para resolver code smell similar da antiga classe ``Login``
   
      i. Code Smell

     ```java
    System.out.println("Digite seu login:");
    login = input.next();

    if (isQuit(login)){ return some_user;}
    //System.out.printf("Login digitado: %s\n", login);

    System.out.println("Digite a senha:");
    password = input.next();

     ```
     ###### Login.``menu()``
     
     
     	ii. Aplicação do padrão

     ```java
    String login = input.getUserInput("Digite seu login:");
    if (input.isQuit(login)){ return false; }

    String pass = input.getUserInput("Digite a sua senha:");
    if (input.isQuit(pass)){ return false; }
     ```

     ###### LoginController.``executeLogin()`` 


<hr>
	
	 Concluido em 03/07/2022
