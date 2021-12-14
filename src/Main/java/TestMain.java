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
                Customer customer = new Customer("Maria K", "199001041818","Asd1234!");
                Employee emp = new Employee("Angelica K", "199001041819","Asd1234!",50000);
                Manager manager= new Manager("Anna L", "199001041820","Asd1234!",50000,2000);
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
