public class TheBank {
    private MenuOptions mainMenu = new MenuOptions();
    private MenuOptions customer = new MenuOptions();
    private MenuOptions employee = new MenuOptions();

    public TheBank() {
        mainMenu.setMenuName("Main Menu: Please choose among the options below.");
        mainMenu.addOptions(0, "Close System");
        mainMenu.addOptions(1, "Customer");
        mainMenu.addOptions(2, "Employee");

        customer.setMenuName("Dear Customer, please choose one of the options below to proceed.");
        customer.addOptions(0, "View account balance");
        customer.addOptions(1, "Deposit money");
        customer.addOptions(2, "Withdraw cash");
        customer.addOptions(3, "Transfer money");
        customer.addOptions(4, "Request loan and apply for mortgages");
        customer.addOptions(5, "Apply for card");
        customer.addOptions(6, "Block payment card");
        customer.addOptions(7, "Deactivate account");
        customer.addOptions(7, "Edit personal information");


        employee.setMenuName("Dear Employee, please choose one of the options below.");
        employee.addOptions(0, "Manager");
        employee.addOptions(1, "View salary");
        employee.addOptions(2,"Apply for vacation");
        employee.addOptions(3,"Customer accounts");
        employee.addOptions(4,"Approve loans and mortgages");
    }

    public void handleCustomerMenu(){
        this.customer.printOptions();
        int userChoice = src.ReadInput.readInteger("Type in the option: ");

        switch(userChoice){
            case 0:
        }

    }

    public void handleEmployeeMenu(){
        this.employee.printOptions();
        int userChoice = src.ReadInput.readInteger("Type in the option");

        switch(userChoice){
            case 0:
                break;
        }
    }

    public void handleMainMenu(){
        this.mainMenu.printOptions();
        int userChoice = src.ReadInput.readInteger("Type in the option: ");

        switch(userChoice){
            case 0:
                break;
            case 1:
                this.handleCustomerMenu();
                break;
            case 2:

        }
    }

    public static void main(String[] args) {
        TheBank bank = new TheBank();
        bank.handleMainMenu();
    }

}
