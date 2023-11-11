package christmas.domain;

import static christmas.constant.ErrorMessageConst.UNAVAILABLE_ORDER_MESSAGE;
import static christmas.domain.Type.APPETIZER;
import static christmas.domain.Type.DESSERT;
import static christmas.domain.Type.DRINK;
import static christmas.domain.Type.MAIN;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BBQ_RIBS("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),

    CHOCO_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),

    ZERO_COLA("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    private final String name;
    private final int price;
    private final Type type;

    Menu(String name, int price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu findMenuByName(String name) {
        return Arrays.stream(Menu.values()).filter(menu -> menu.getName().equals(name)).findAny()
                .orElseThrow(() -> new IllegalArgumentException(UNAVAILABLE_ORDER_MESSAGE));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }
}
