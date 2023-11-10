package christmas.valid;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenusValidTest {

    @Test
    @DisplayName("메뉴이름-개수 순으로 입력하면 에러가 발생하지 않는다")
    void no_error() throws Exception {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        assertDoesNotThrow(() -> MenusValid.validMenus(input));
    }

    @Test
    @DisplayName("수량이 0보다 작은게 있으므로 에러가 발생")
    void error_quantity_lower_than_one() throws Exception {
        String input = "티본스테이크-0,바비큐립-1,초코케이크-2,제로콜라-1";
        assertThrows(IllegalArgumentException.class, () -> MenusValid.validMenus(input));
    }

    @Test
    @DisplayName("메뉴가 겹치는게 있으므로 에러가 발생")
    void error_duplicate_menu() throws Exception {
        String input = "티본스테이크-1,티본스테이크-1,초코케이크-2,제로콜라-1";
        assertThrows(IllegalArgumentException.class, () -> MenusValid.validMenus(input));
    }

    @Test
    @DisplayName("메뉴가 총 20개 이상이므로 에러가 발생")
    void error_total_quantity_over_20() throws Exception {
        String input = "티본스테이크-1,바비큐립-10,초코케이크-12,제로콜라-1";
        assertThrows(IllegalArgumentException.class, () -> MenusValid.validMenus(input));
    }

    @Test
    @DisplayName("음료만 존재하므로 에러가 발생")
    void error_menu_only_drink() throws Exception {
        String input = "제로콜라-1,레드와인-2";
        assertThrows(IllegalArgumentException.class, () -> MenusValid.validMenus(input));
    }

    @Test
    @DisplayName("-가 없으므로 에러 발생")
    void error_menu_wrong_form1() throws Exception {
        String input = "제로콜라1,레드와인-2";
        assertThrows(IllegalArgumentException.class, () -> MenusValid.validMenus(input));
    }

    @Test
    @DisplayName(",가 없으므로 에러 발생")
    void error_menu_wrong_form2() throws Exception {
        String input = "제로콜라-1레드와인-2";
        assertThrows(IllegalArgumentException.class, () -> MenusValid.validMenus(input));
    }
}