package christmas.valid;

import static christmas.domain.Menu.findMenuByName;
import static christmas.domain.Type.DRINK;
import static christmas.domain.Type.findTypeByMenuName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenusValid {

    public static void validMenus(String input) {
        List<String> menuNames = new ArrayList<>();
        List<String> menus = Arrays.asList(input.split(","));
        int allQuantity = 0;
        for (String menu : menus) {
            List<String> menuOptions = Arrays.asList(menu.split("-"));
            validMenuFormAndName(menuOptions);
            menuNames.add(menuOptions.get(0));
            int quantity = validQuantity(menuOptions);
            allQuantity += quantity;
        }
        validAllQuantity(allQuantity);
        validNotDuplicateMenu(menuNames);
        validNotOnlyDrink(menuNames);
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
        if (menuOptions.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static int validOnlyNumber(String input) {
        String reg = "^[0-9]*$";
        if (!input.matches(reg)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return Integer.parseInt(input);
    }

    private static void validQuantityRange(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validAllQuantity(int allQuantity) {
        if (allQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validNotDuplicateMenu(List<String> menuNames) {
        if (menuNames.stream().distinct().count() != menuNames.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validNotOnlyDrink(List<String> menuNames) {
        if (menuNames.stream().filter(menu -> findTypeByMenuName(menu) == DRINK).count() == menuNames.size()) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }
}
