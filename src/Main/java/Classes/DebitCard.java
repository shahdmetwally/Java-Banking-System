package Classes;

import Utilities.Utilities;

public class DebitCard {
    String cardNr;
    boolean status;
    int cvc;
    String expirationDate;
    int code;

    public DebitCard(){

    }
    public DebitCard(String cardNr, int cvc, String expirationDate, int code) throws Exception{
        if(cardNr.length() != 16){
            throw new Exception("The card number should have 16 digits");
        }
        this.cardNr = cardNr;
        this.status = true;

        String cvcStr = cvc + "";
        if(cvcStr.length() != 3){
            throw new Exception("The cvc should have only 3 digits.");
        }
        this.cvc = cvc;
        // find a way to make sure that the expiration date is in the future!
        this.expirationDate = expirationDate;

        String codeStr = code + "";
        if( codeStr.length() != 4){
            throw new Exception("The code should have 4 digits.");
        }
        this.code = code;
    }

    // 6 bank identification
    // 6 bank account
    // 4 unique
    public boolean getStatus(){
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setActive() {
        this.status = true;
    }

    public void setDeactivate() {
        this.status = false;
    }

    public void setCode(int newCode) throws Exception{
        String newCodeStr = newCode + "";
        if(newCodeStr.length() != 4){
            throw new Exception("The code must have 4 digits.");
        }
        this.code = newCode;
    }

    public int getCode() {
        return code;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCardNr(String cardNr) {
        this.cardNr = cardNr;
    }

    public String getCardNr() {
        return cardNr;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String toString(){
        return "Card number: " + getCardNr() + Utilities.EOL +
                "Code: " + getCode() + Utilities.EOL +
                "Cvc: " + getCvc() + Utilities.EOL +
                "Expiration date: " + getExpirationDate();
    }
}
