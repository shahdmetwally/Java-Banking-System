package Menu;

import java.util.HashMap;

public class MenuOptions {

    private String menuName;
    private HashMap<Integer, String> menus = new HashMap<>();

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void addOptions (Integer optionNumber, String optionName){
        menus.put(optionNumber,optionName);
    }

    public void printOptions(){
        System.out.println(menuName);
        menus.forEach((optionNumber, optionName) -> System.out.println(optionNumber + " : " + optionName));

        /*
         public void printOptions(){
        System.out.println(menuName);
        options.forEach((optionId, optionName) -> System.out.println(optionId + " : " + optionName));
    }
         */
    }

}
