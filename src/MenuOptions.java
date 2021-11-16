import java.util.HashMap;

public class MenuOptions {
    private String menuName;
    private HashMap<Integer, String> menus = new HashMap<Integer, String>();
    private Integer currentChoice;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getCurrentChoice() {
        return currentChoice;
    }

    public void setCurrentChoice(Integer currentChoice) {
        this.currentChoice = currentChoice;
    }

    public void addOptions (Integer optionNumber, String optionName){
        menus.put(optionNumber, optionName);
    }

    public void printOptions(){
        System.out.println(menuName);
        menus.forEach((optionNumber, optionName) -> System.out.println());
    }
}
