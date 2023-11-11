package christmas.domain;

import static christmas.constant.ErrorMessageConst.UNAVAILABLE_ORDER_MESSAGE;

import java.util.Arrays;

public enum Type {

    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public static Type findTypeByMenuName(String name) {
        return Arrays.stream(Menu.values()).filter(menu -> menu.getName().equals(name)).findAny()
                .orElseThrow(() -> new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE)).getType();
    }

}
