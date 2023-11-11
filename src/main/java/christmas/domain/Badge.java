package christmas.domain;

import java.util.Arrays;

public enum Badge {

    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Badge findBadgeByPrice(int price) {
        return Arrays.stream(Badge.values()).filter(badge -> badge.getPrice() <= price).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
