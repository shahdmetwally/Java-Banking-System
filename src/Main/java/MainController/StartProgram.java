package MainController;


import Menu.Menu;

public class StartProgram {

    public static void main(String[] args) {
      String option;
        do{

          try {
              System.out.println(" Welcomen to --name -- Banking System");
              Menu bankMenu = new Menu();
              bankMenu.handleMainMenu();
          }catch (Exception exception){
              System.out.println(exception.getMessage());
          }
          option = "Do you want to continue?";
      }while (option.equalsIgnoreCase("yes"));
    }
}
