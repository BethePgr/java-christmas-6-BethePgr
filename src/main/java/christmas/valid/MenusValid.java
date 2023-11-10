package christmas.valid;

import static christmas.domain.Menu.findMenuByName;
import static christmas.domain.Type.DRINK;
import static christmas.domain.Type.findTypeByMenuName;

import java.util.ArrayList;
import java.util.List;

public class MenusValid {

    public static void validMenus(String input) {
        List<String> menuNames = new ArrayList<>();
        String[] menus = input.split(",");
        int allQuantity = 0;
        for (String menu : menus) {
            String[] menuOptions = menu.split("-");
            validFormOfMenu(menuOptions);
            findMenuByName(menuOptions[0]);
            menuNames.add(menuOptions[0]);
            int quantity = validOnlyNumber(menuOptions[1]);
            validQuantityRange(quantity);
            allQuantity += quantity;
        }
        validAllQuantity(allQuantity);
        validNotDuplicateMenu(menuNames);
        validNotOnlyDrink(menuNames);
    }

    private static void validFormOfMenu(String[] menuOptions) {
        if (menuOptions.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static int validOnlyNumber(String input) {
        String reg = "^[0-9]*$";
        if (!input.matches(reg)) {
            throw new IllegalArgumentException("[ERROR] 날짜는 숫자만 입력 가능합니다.");
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
