package christmas.util;

import static christmas.domain.Menu.findMenuByName;

import christmas.domain.Menu;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convert {

    public static Map<Menu, Integer> convertToMenuMap(String input) {
        Map<Menu, Integer> menuMap = new HashMap<>();
        List<String> menus = Arrays.asList(input.split(","));
        for (String menu : menus) {
            List<String> menuOptions = Arrays.asList(menu.split("-"));
            String menuName = menuOptions.get(0);
            int menuQuantity = Integer.parseInt(menuOptions.get(1));
            menuMap.put(findMenuByName(menuName), menuQuantity);
        }
        return menuMap;
    }

    public static String convertAmount(int price) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(price);
    }

}
