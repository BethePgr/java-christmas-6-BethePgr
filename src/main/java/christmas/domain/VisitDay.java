package christmas.domain;

import christmas.valid.DayValid;

public class VisitDay {

    private int day;

    public VisitDay(String day) {
        this.day = DayValid.validDay(day);
    }

    public int getDay() {
        return day;
    }
}
