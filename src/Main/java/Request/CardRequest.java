package Request;

import Classes.User;
import Utilities.Utilities;


public class CardRequest extends Request  {

   private String id;

   public CardRequest(){}

    public CardRequest(User user){
        super(user);
        this.id = "C" + getPersonalNr();

    }
    public String getId() {
        return id;
    }
   @Override
    public String toString(){
        return "ID: " + id + " Date: "+ getDate();
    }

    public String printRequest(){
        return "Card request:" + Utilities.EOL +
                "-----------------------"+ Utilities.EOL +
                "Date: " + getDate() + Utilities.EOL +
                "Customer name: " + getName() + Utilities.EOL+
                "Personal number: " + getPersonalNr() +Utilities.EOL;

    }

}
