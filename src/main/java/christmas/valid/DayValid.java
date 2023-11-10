package christmas.valid;

public class DayValid {

    public static int validDay(String input) {
        int day = validOnlyNumber(input);
        validRangeOfDay(day);
        return day;
    }

    private static int validOnlyNumber(String input) {
        String reg = "^[0-9]*$";
        if (!input.matches(reg)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return Integer.parseInt(input);
    }

    private static void validRangeOfDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요");
        }
    }

}
