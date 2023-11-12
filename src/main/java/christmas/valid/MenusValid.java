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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MenusValid {

    public static Map<Menu, Integer> validMenus(String input) {
        List<String> menuNames = new ArrayList<>();
        List<String> menus = Arrays.asList(input.split(","));
        int allQuantity = 0;
        for (String menu : menus) {
            List<String> menuOptions = Arrays.asList(menu.split("-"));
            validMenuFormAndName(menuOptions);
            menuNames.add(menuOptions.get(0));
            allQuantity += validQuantity(menuOptions);
        }
        validAllQuantity(allQuantity);
        validNotDuplicateMenu(menuNames);
        validNotOnlyDrink(menuNames);
        return Convert.convertToMenuMap(input);
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

    private static void validAllQuantity(int allQuantity) {
        if (allQuantity > MENU_MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
    }

    private static void validNotDuplicateMenu(List<String> menuNames) {
        if (menuNames.stream().distinct().count() != menuNames.size()) {
            throw new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE);
        }
    }

    private static void validNotOnlyDrink(List<String> menuNames) {
        if (menuNames.stream().filter(menu -> findTypeByMenuName(menu) == DRINK).count() == menuNames.size()) {
            throw new IllegalArgumentException(CANNOT_ORDER_ONLY_DRINK);
        }
    }
}
