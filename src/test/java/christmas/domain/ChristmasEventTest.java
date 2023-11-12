package christmas.domain;

import static christmas.domain.Event.D_DAY_EVENT;
import static christmas.domain.Event.GIFT_EVENT;
import static christmas.domain.Event.SPECIAL_EVENT;
import static christmas.domain.Event.WEEKDAY_EVENT;
import static christmas.domain.Event.WEEKEND_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasEventTest {
    /**
     * 티본스테이크-55000 초코케이크-15000 제로콜라-3000 양송이수프-6000
     */
    @Test
    @DisplayName("3일은 평일이자 특별한 날이므로 특별할인과 디저트 할인이 들어가야한다")
    void test_3day_over_120000() throws Exception {
        String specialWeekday = "3";
        VisitDay visitDay = new VisitDay(specialWeekday);
        String menus = "티본스테이크-1,초코케이크-3,제로콜라-7";
        ChristmasEvent christmasEvent = new ChristmasEvent(new ChristmasMenu(menus));
        int originalPrice = 55000 * 1 + 15000 * 3 + 3000 * 7; //121,000
        christmasEvent.buildEventMap(originalPrice, visitDay);

        assertThat(christmasEvent.getEventMap()).containsOnlyKeys(GIFT_EVENT, SPECIAL_EVENT, WEEKDAY_EVENT,
                D_DAY_EVENT);
        assertThat(christmasEvent.getEventMap().get(GIFT_EVENT)).isEqualTo(25000);
        assertThat(christmasEvent.getEventMap().get(SPECIAL_EVENT)).isEqualTo(1000);
        assertThat(christmasEvent.getEventMap().get(WEEKDAY_EVENT)).isEqualTo(2023 * 3);
        assertThat(christmasEvent.getEventMap().get(D_DAY_EVENT)).isEqualTo(1000 + 100 * (visitDay.getDay() - 1));
    }

    @Test
    @DisplayName("1일은 주말이므로 메인메뉴 할인이 들어가야한다")
    void test_1day() throws Exception {
        String specialWeekday = "1";
        VisitDay visitDay = new VisitDay(specialWeekday);
        String menus = "티본스테이크-1,초코케이크-3,제로콜라-7";
        ChristmasEvent christmasEvent = new ChristmasEvent(new ChristmasMenu(menus));
        int originalPrice = 55000 * 1 + 15000 * 3 + 3000 * 7; //121,000
        christmasEvent.buildEventMap(originalPrice, visitDay);

        assertThat(christmasEvent.getEventMap()).containsOnlyKeys(GIFT_EVENT, WEEKEND_EVENT,
                D_DAY_EVENT);
        assertThat(christmasEvent.getEventMap().get(GIFT_EVENT)).isEqualTo(25000);
        assertThat(christmasEvent.getEventMap().get(WEEKEND_EVENT)).isEqualTo(2023 * 1);
        assertThat(christmasEvent.getEventMap().get(D_DAY_EVENT)).isEqualTo(1000 + 100 * (visitDay.getDay() - 1));
    }

    @Test
    @DisplayName("1일은 주말이므로 메인메뉴 할인이 들어가야한다 하지만 티본스테이크 메뉴의 할인가격이 잘못 책정됐으므로 에러가 발생")
    void error_test_1day() throws Exception {
        String weekend = "1";
        VisitDay visitDay = new VisitDay(weekend);
        String menus = "티본스테이크-2,초코케이크-3,제로콜라-7";
        ChristmasEvent christmasEvent = new ChristmasEvent(new ChristmasMenu(menus));
        int originalPrice = 55000 * 1 + 15000 * 3 + 3000 * 7; //121,000
        christmasEvent.buildEventMap(originalPrice, visitDay);

        assertThat(christmasEvent.getEventMap()).containsOnlyKeys(GIFT_EVENT, WEEKEND_EVENT,
                D_DAY_EVENT);
        assertThat(christmasEvent.getEventMap().get(GIFT_EVENT)).isEqualTo(25000);
        assertThat(christmasEvent.getEventMap().get(WEEKEND_EVENT)).isNotEqualTo(2023 * 1);
        assertThat(christmasEvent.getEventMap().get(D_DAY_EVENT)).isEqualTo(1000 + 100 * (visitDay.getDay() - 1));
    }

    @Test
    @DisplayName("3일은 평일이자 특별한 날이므로 특별할인과 디저트 할인이 들어가야한다")
    void test_3day_down_120000() throws Exception {
        String specialWeekday = "3";
        VisitDay visitDay = new VisitDay(specialWeekday);
        String menus = "티본스테이크-1,초코케이크-3,제로콜라-6";
        ChristmasEvent christmasEvent = new ChristmasEvent(new ChristmasMenu(menus));
        int originalPrice = 55000 * 1 + 15000 * 3 + 3000 * 6; //118,000
        christmasEvent.buildEventMap(originalPrice, visitDay);

        assertThat(christmasEvent.getEventMap()).containsOnlyKeys(SPECIAL_EVENT, WEEKDAY_EVENT,
                D_DAY_EVENT);
        assertThat(christmasEvent.getEventMap().get(SPECIAL_EVENT)).isEqualTo(1000);
        assertThat(christmasEvent.getEventMap().get(WEEKDAY_EVENT)).isEqualTo(2023 * 3);
        assertThat(christmasEvent.getEventMap().get(D_DAY_EVENT)).isEqualTo(1000 + 100 * (visitDay.getDay() - 1));
    }

    @Test
    @DisplayName("10000원 미만이면 아무런 이벤트가 적용되지 않는다")
    void no_event_under_10000() throws Exception {
        String specialWeekday = "3";
        VisitDay visitDay = new VisitDay(specialWeekday);
        String menus = "양송이수프-1,제로콜라-1";
        ChristmasEvent christmasEvent = new ChristmasEvent(new ChristmasMenu(menus));
        int originalPrice = 6000 * 1 + 3000 * 1; //9,000
        christmasEvent.buildEventMap(originalPrice, visitDay);

        assertThat(christmasEvent.getEventMap().size()).isEqualTo(0);

    }
}