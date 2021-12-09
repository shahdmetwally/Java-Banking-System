import Classes.Bank;
import Classes.Customer;
import Classes.Employee;
import Classes.Manager;
import Menu.MainMenu;
import Utilities.Utilities;

public class TestMain {
    public static void main(String[] args) {
        String option;
        do{

            try {
                Customer customer = new Customer("Maria K", "199001041848","Chu44asco!");
                Employee emp = new Employee("Angelica K", "199001041858","Chu44asco!",50000);
                Manager manager= new Manager("Anna L", "199001041868","Chu44asco!",50000,2000);
                Bank bank = new Bank();
                bank.addUser(customer);
                bank.addUser(emp);
                bank.addUser(manager);
                customer.depositMoney(100000);

                System.out.println("Welcome"+ Utilities.EOL + "-------------");

                MainMenu bankMenu = new MainMenu();
                bankMenu.handleMainMenu();

            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
            option = "Do you want to continue?";
        }while (option.equalsIgnoreCase("yes"));
    }
}
