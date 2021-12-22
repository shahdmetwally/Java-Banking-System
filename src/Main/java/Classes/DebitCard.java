package Classes;

public class DebitCard {
    int cardNr;
    boolean status;
    int cvc;
    String expirationDate;
    int code;

    public DebitCard(){

    }
    public DebitCard(int cardNr, int cvc, String expirationDate, int code) throws Exception{
        if(cardNr != 16){
            throw new Exception("The card number should have 16 digits");
        }
        this.cardNr = cardNr;
        this.status = true;
        if(cvc != 3){
            throw new Exception("The cvc should have only 3 digits.");
        }
        this.cvc = cvc;
        // find a way to make sure that the expiration date is in the future!
        this.expirationDate = expirationDate;

        if( code != 4){
            throw new Exception("The code should have 4 digits.");
        }
        this.code = code;
    }

    // 6 bank identification
    // 6 bank account
    // 4 unique

    public void setActive() {
        this.status = true;
    }

    public void setDeactivate() {
        this.status = false;
    }

    public void setCode(int newCode) throws Exception{
        if(newCode != 4){
            throw new Exception("The code must have 4 digits.");
        }
        this.code = newCode;
    }










}
