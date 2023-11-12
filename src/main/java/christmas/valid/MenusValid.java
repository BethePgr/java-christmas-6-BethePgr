package christmas.valid;

import static christmas.constant.ErrorMessageConst.CANNOT_ORDER_ONLY_DRINK;
import static christmas.constant.ErrorMessageConst.UNAVAILABLE_ORDER_MESSAGE;
import static christmas.constant.ValidConst.MENU_INDIVIDUAL_LEAST_ORDER_QUANTITY;
import static christmas.constant.ValidConst.MENU_MAX_ORDER_QUANTITY;
import static christmas.constant.ValidConst.MENU_OPTIONS_ORDER_SIZE;
import static christmas.domain.Menu.findMenuByName;
import static christmas.domain.Type.DRINK;
import static christmas.domain.Type.findTypeByMenuName;

import christmas.domain.Menu;
import christmas.util.Convert;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MenusValid {

    public static Map<Menu, Integer> validMenus(String input) {
        List<String> menus = Arrays.asList(input.split(","));
        for (String menu : menus) {
            List<String> menuOptions = Arrays.asList(menu.split("-"));
            validMenuFormAndName(menuOptions);
            validQuantity(menuOptions);
        }
        Map<Menu, Integer> menuMap = Convert.convertToMenuMap(input);
        validAllQuantity(menuMap);
        validNotDuplicateMenu(menuMap, menus);
        validNotOnlyDrink(menuMap);
        return menuMap;
    }

    private static void validMenuFormAndName(List<String> menuOptions) {
        validFormOfMenu(menuOptions);
        findMenuByName(menuOptions.get(0));
    }

    private static int validQuantity(List<String> menuOptions) {
        int quantity = validOnlyNumber(menuOptions.get(1));
        validQuantityRange(quantity);
        return quantity;
    }

    private static void validFormOfMenu(List<String> menuOptions) {
        if (menuOptions.size() != MENU_OPTIONS_ORDER_SIZE) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
    }

    private static int validOnlyNumber(String input) {
        String reg = "^[0-9]*$";
        if (!input.matches(reg)) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
        return Integer.parseInt(input);
    }

    private static void validQuantityRange(int quantity) {
        if (quantity < MENU_INDIVIDUAL_LEAST_ORDER_QUANTITY) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
    }

    private static void validAllQuantity(Map<Menu, Integer> menuMap) {
        if (menuMap.values().stream().mapToInt(Integer::intValue).sum() > MENU_MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
    }

    private static void validNotDuplicateMenu(Map<Menu, Integer> menuMap, List<String> menus) {
        if (menuMap.size() != menus.size()) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
    }

    private static void validNotOnlyDrink(Map<Menu, Integer> menuMap) {
        if (menuMap.keySet().stream().allMatch(menu -> findTypeByMenuName(menu.getName()) == DRINK)) {
            throw new IllegalArgumentException(CANNOT_ORDER_ONLY_DRINK);
        }
    }

}
