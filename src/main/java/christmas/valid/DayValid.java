package christmas.valid;

import static christmas.constant.ErrorMessageConst.UNAVAILABLE_VISIT_DAY;
import static christmas.constant.ValidConst.DECEMBER_END_DAY;
import static christmas.constant.ValidConst.DECEMBER_START_DAY;

public class DayValid {

    public static int validDay(String input) {
        int day = validOnlyNumber(input);
        validRangeOfDay(day);
        return day;
    }

    private static int validOnlyNumber(String input) {
        String reg = "^[0-9]*$";
        if (!input.matches(reg)) {
            throw new IllegalArgumentException(UNAVAILABLE_VISIT_DAY);
        }
        return Integer.parseInt(input);
    }

    private static void validRangeOfDay(int day) {
        if (day < DECEMBER_START_DAY || day > DECEMBER_END_DAY) {
            throw new IllegalArgumentException(UNAVAILABLE_VISIT_DAY);
        }
    }

}
