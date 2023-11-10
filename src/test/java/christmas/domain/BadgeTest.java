package christmas.domain;

import static christmas.domain.Badge.SANTA;
import static christmas.domain.Badge.STAR;
import static christmas.domain.Badge.TREE;
import static christmas.domain.Badge.findBadgeByPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    @DisplayName("4000원에 해당되는 뱃지는 없으므로 null을 반환한다")
    void null_price_4000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(4000);
        assertNull(badgeByPrice);
    }

    @Test
    @DisplayName("5000원에 해당되는 뱃지는 스타를 반환한다")
    void star_price_5000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(5000);
        assertEquals(badgeByPrice, STAR);
    }

    @Test
    @DisplayName("6000원에 해당되는 뱃지는 스타를 반환한다")
    void star_price_6000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(6000);
        assertEquals(badgeByPrice, STAR);
    }

    @Test
    @DisplayName("10000원에 해당되는 뱃지는 트리를 반환한다")
    void tree_price_10000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(10000);
        assertEquals(badgeByPrice, TREE);
    }

    @Test
    @DisplayName("11000원에 해당되는 뱃지는 트리를 반환한다")
    void tree_price_11000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(11000);
        assertEquals(badgeByPrice, TREE);
    }

    @Test
    @DisplayName("20000원에 해당되는 뱃지는 산타를 반환한다")
    void santa_price_20000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(20000);
        assertEquals(badgeByPrice, SANTA);
    }

    @Test
    @DisplayName("21000원에 해당되는 뱃지는 산타를 반환한다")
    void santa_price_21000() throws Exception {
        Badge badgeByPrice = findBadgeByPrice(21000);
        assertEquals(badgeByPrice, SANTA);
    }
}