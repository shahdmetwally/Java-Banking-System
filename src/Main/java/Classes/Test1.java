package Classes;

import Bank.Bank;

import MainController.Controller;
import Utilities.Utilities;
import Utilities.UserInput;

import Bank.TypesOfLoan;

public class Test1 {
    public static void main(String[] args) {
        //"customers":[{"fullName":"Anasha Sarker","personalNo":"200102151234","password":"Lmnop1234","bankAccount":{"accountNo":"5051-195291080","balance":5000.0,"transactions":[],"active":true,"loan":0.0,"budget":0.0},"transactions":[]},
        try {
            String personNr = "199001041848";
            String password = "Chu44asco";
            Customer customer = new Customer("nicole", personNr,20000,password,"5051-195291080","1234567891234",123,"20251209",8787);
           Bank bank = new Bank();
           bank.addUser(customer);
            Controller controller = new Controller(personNr,password,bank);

            System.out.println(" Loan options: " + Utilities.EOL +
                    "1. Personal loan " + Utilities.EOL +
                    "2. House loan "+ Utilities.EOL +
                    "3. Car loan"+ Utilities.EOL +
                    "4. Unsecured loan");
            TypesOfLoan typesOfLoan = null;
            int newOption = UserInput.readInt("Enter loan option: ");
            do {
                switch (newOption) {
                    case 1:
                        typesOfLoan = TypesOfLoan.PERSONAL_LOAN;
                        break;
                    case 2:
                        typesOfLoan = TypesOfLoan.HOUSE_LOAN;
                        break;
                    case 3:
                        typesOfLoan = TypesOfLoan.CAR_LOAN;
                        break;
                    case 4:
                        typesOfLoan = TypesOfLoan.UNSECURED_LOAN;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select between option 1 to 4. ");
                }
            }while (newOption>4 ||newOption < 1);

            int loanAmount = UserInput.readInt("Enter the loan amount: ");
            double time = UserInput.readDouble("Enter loan time: ");
            String otherEquity = UserInput.readLine("Enter other equity");
            double otherEquitiesValue = UserInput.readDouble("Enter other equities value: ");
            double cashContribution = UserInput.readDouble("Enter cash contribution: " + Utilities.EOL + "Show be at least 15% of the total loan amount");
            String coSigner_name = UserInput.readLine("Enter Co-signer name: ");
            String coSigner_personalNr = UserInput.readLine("Enter Co-signer personal number: ");
            double coSigner_salary = UserInput.readDouble("Enter Co-signer salary: ");

            controller.loanRequestWithCoSigner(loanAmount,typesOfLoan,time,otherEquity,otherEquitiesValue,cashContribution,coSigner_name,coSigner_personalNr,coSigner_salary);
            bank.getLoanApplications().toString();

         //   controller.loanRequest()
        }catch (Exception exception){
            System.out.println(exception);
        }

    }
}
