package app;

import controller.PrincipalController;
import view.ViewConsole;

public class Main {
    public static void main(String[] args) {
        final int SIZE_ACTIVITY_ARRAY = 20;
        final int SIZE_MEMBER_ARRAY = 20;
        ViewConsole.showPrincipalMenu(PrincipalController.startApp(SIZE_ACTIVITY_ARRAY, SIZE_MEMBER_ARRAY), SIZE_MEMBER_ARRAY);
    }
}
