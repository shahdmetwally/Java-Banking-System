package MainController;


import Menu.MainMenu;

public class StartProgram {

    public static void main(String[] args) {
      String option;
        do{

          try {
              System.out.println(" Welcomen to --name -- Banking System");
              MainMenu bankMenu = new MainMenu();
              bankMenu.handleMainMenu();
          }catch (Exception exception){
              System.out.println(exception.getMessage());
          }
          option = "Do you want to continue?";
      }while (option.equalsIgnoreCase("yes"));
    }
}
