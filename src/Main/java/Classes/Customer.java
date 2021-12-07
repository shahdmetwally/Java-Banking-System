package Classes;

public class Customer extends User {
    private BankAccount bankAccount;

    public Customer(String fullName, String personalNo, String password)throws Exception{
        super(fullName, personalNo,password);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public double depositMoney(double amount) throws Exception {
      return bankAccount.depositMoney(amount);
    }

    public double withdrawMoney(double amount) throws Exception {
        return bankAccount.withdrawMoney(amount);
    }

   public double transferMoney(double amount, String  anotherBankAccountNo) throws Exception{
        return bankAccount.transferMoney(amount, anotherBankAccountNo);
    }



    public String checkBudget(){
        return bankAccount.checkBudget();
    }

   /* public boolean equals(Object anotherObject){
        if(this == anotherObject){
            return true;
        }else if(anotherObject == null){
            return false;
        }else if( anotherObject instanceof Customer){
            Customer anotherCustomer = (Customer) anotherObject;
            boolean isSameCustomer = this.getPersonalNo().equals(anotherCustomer.getPersonalNo()) && this.getPassword().equals(anotherCustomer.getPassword());
            return false;
        }else{
            return false;
        }
    }

    */
}
