package Classes;

public class Person {

    private String name;
    private int personNumber;
    private String password;

    public Person(String name, int personNumber, String password){
        this.name = name;
        this.personNumber = personNumber;
        this.password = password;
    }
    public String getName(){
        return this.name;
    }
    public int getPersonNumber(){
        return this.personNumber;
    }

    public void setPassword( String newPassword){
        this.password = newPassword;
    }
    public void setName(String newName){
        this.name = newName;
    }


}
