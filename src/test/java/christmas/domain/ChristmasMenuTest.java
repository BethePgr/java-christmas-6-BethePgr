package christmas.domain;

import static christmas.domain.Menu.findMenuByName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasMenuTest {

    @Test
    @DisplayName("메뉴 이름과 개수가 잘 적용됐나")
    void no_error_1() throws Exception {
        String menus = "해산물파스타-2,레드와인-1,초코케이크-1";
        ChristmasMenu christmasMenu = new ChristmasMenu(menus);
        assertThat(christmasMenu.getMenuMap()).containsOnlyKeys(findMenuByName("해산물파스타"), findMenuByName("레드와인"),
                findMenuByName("초코케이크"));
        assertThat(christmasMenu.getMenuMap().get(findMenuByName("해산물파스타"))).isEqualTo(2);
        assertThat(christmasMenu.getMenuMap().get(findMenuByName("레드와인"))).isEqualTo(1);
        assertThat(christmasMenu.getMenuMap().get(findMenuByName("초코케이크"))).isEqualTo(1);
    }

    @Test
    @DisplayName("메뉴 이름과 개수가 잘 적용됐나")
    void no_error_2() throws Exception {
        String menus = "타파스-1,제로콜라-1";
        ChristmasMenu christmasMenu = new ChristmasMenu(menus);
        assertThat(christmasMenu.getMenuMap()).containsOnlyKeys(findMenuByName("타파스"), findMenuByName("제로콜라"));
        assertThat(christmasMenu.getMenuMap().get(findMenuByName("타파스"))).isEqualTo(1);
        assertThat(christmasMenu.getMenuMap().get(findMenuByName("제로콜라"))).isEqualTo(1);
    }

    @Test
    @DisplayName("메뉴 이름과 개수가 잘 적용됐지 않았나")
    void error_1() throws Exception {
        String menus = "타파스-1,제로콜라-1";
        ChristmasMenu christmasMenu = new ChristmasMenu(menus);
        assertThat(christmasMenu.getMenuMap()).doesNotContainKeys(findMenuByName("해산물파스타"), findMenuByName("레드와인"));
    }

    @Test
    @DisplayName("총 메뉴 20개 이상이므로 에러를 발생")
    void error_quantity_over_20() throws Exception {
        String menus = "타파스-1,제로콜라-21";
        assertThrows(IllegalArgumentException.class, () -> new ChristmasMenu(menus));
    }

    @Test
    @DisplayName("디저트만 있으므로 에러 발생")
    void error_only_dessert() throws Exception {
        String menus = "레드와인-1,제로콜라-1,샴폐인-4";
        assertThrows(IllegalArgumentException.class, () -> new ChristmasMenu(menus));
    }

    @Test
    @DisplayName("메뉴 이름이 다르므로 에러 발생")
    void error_wrong_menu_name() throws Exception {
        String menus = "레드외인-1,제로콜라-1,타파스-1";
        assertThrows(IllegalArgumentException.class, () -> new ChristmasMenu(menus));
    }
}