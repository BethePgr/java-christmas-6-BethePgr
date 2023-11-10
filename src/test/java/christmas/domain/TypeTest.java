package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    @DisplayName("양송이수프는 에피타이저의 type을 가진다")
    void no_error_mushroom_soup_find_by_name() throws Exception {
        Type soup = Type.findTypeByMenuName("양송이수프");
        assertEquals(soup, Type.APPETIZER);
    }

    @Test
    @DisplayName("양송이수프는 디저트의 type을 가지지 않는다")
    void error_mushroom_soup_find_by_name() throws Exception {
        Type soup = Type.findTypeByMenuName("양송이수프");
        assertNotEquals(soup, Type.DESSERT);
    }
}