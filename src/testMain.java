import Classes.Customer;
import Classes.Employee;

public class testMain {
    public static void main(String [] args) throws Exception {
        System.out.println("hello");
        Customer customer = new Customer("Marta", 900110, "900104", 900104, 1234);
        System.out.println(customer);
        Employee employee = new Employee("ID1", "Jennifer", 2002, 50000, 100);
        customer.depositMoney(5000);
        System.out.println(customer.checkBalance());
        employee.deactivateAccount(customer);
        customer.depositMoney(5000);

        //that
        //hello

    }
}
