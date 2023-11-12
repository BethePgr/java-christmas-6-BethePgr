package christmas.constant;

import java.util.List;

public class EventConst {

    public static final int EVENT_LEAST_APPLY_PRICE = 10000;
    public static final int GIFT_EVENT_LEAST_PRICE = 120000;

    public static final int CHRISTMAS_D_DAY = 25;
    public static final int CHRISTMAS_D_DAY_EVENT_BASIC_DISCOUNT = 1000;
    public static final int CHRISTMAS_D_DAY_EVENT_PER_DAY_DISCOUNT = 100;

    public static final int DESSERT_DISCOUNT = 2023;
    public static final int MAIN_DISCOUNT = 2023;
    public static final int SPECIAL_DISCOUNT = 1000;

    public static final List<Integer> DECEMBER_WEEKDAY = List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21,
            24, 25,
            26, 27, 28, 31);
    public static final List<Integer> DECEMBER_WEEKEND = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    public static final List<Integer> DECEMBER_SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);
}
