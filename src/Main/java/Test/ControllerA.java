package Test;

import Classes.Bank;
import Classes.User;

public class ControllerA {

Bank bank;

    public ControllerA(String personNr, String password){
    this.bank = new Bank();
    logIn(personNr, password);
    }

    public User logIn(String inputPersonNo, String inputPassword) {
        for (User currentPerson : bank.getUsers()) { // clone the list for safety // encapsulation
            if (currentPerson.isSamePersonNo(inputPersonNo) && currentPerson.isSamePassword(inputPassword)) {
                return currentPerson;
            }
        }
        return null;
    }


}
