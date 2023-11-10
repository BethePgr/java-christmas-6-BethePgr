package christmas.valid;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayValidTest {

    @Test
    @DisplayName("숫자를 입력하지 않았으므로 에러가 발생해야 한다")
    void error_not_number() throws Exception {
        String input = "a";
        assertThrows(IllegalArgumentException.class, () -> DayValid.validDay(input));
    }

    @Test
    @DisplayName("1보다 작은 숫자를 입력했으므로 에러가 발생해야 한다")
    void error_smaller_than_1() throws Exception {
        String input = "0";
        assertThrows(IllegalArgumentException.class, () -> DayValid.validDay(input));
    }

    @Test
    @DisplayName("31보다 큰 숫자를 입력했으므로 에러가 발생해야 한다")
    void error_bigger_than_31() throws Exception {
        String input = "32";
        assertThrows(IllegalArgumentException.class, () -> DayValid.validDay(input));
    }

    @Test
    @DisplayName("1과 31 사이의 숫자를 입력했으므로 에러가 발생하지 않는다")
    void no_error_range_number() throws Exception {
        for (int i = 1; i <= 31; i++) {
            String input = String.valueOf(i);
            assertDoesNotThrow(() -> DayValid.validDay(input));
        }
    }
}