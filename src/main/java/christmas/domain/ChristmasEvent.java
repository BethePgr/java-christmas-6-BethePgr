package christmas.domain;

import christmas.util.Convert;
import java.util.Map;

public class ChristmasEvent {

    private final Map<Menu, Integer> menuMap;

    public ChristmasEvent(String input) {
        menuMap = Convert.convertToMenuMap(input);
    }
}
