package christmas.view;

import christmas.domain.Menu;
import java.util.Map;

public class OutputView {

    public static void printStartEventPlanner() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printShowEvent(int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printAllMenus(Map<Menu, Integer> menuMap) {
        System.out.println("<주문 메뉴>");
        for (Menu menu : menuMap.keySet()) {
            System.out.println(menu.getName() + menuMap.get(menu) + "개");
        }
    }
}
