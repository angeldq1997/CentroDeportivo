package app;

import controller.ActivityController;
import controller.MemberController;
import controller.;
import controller.SportCenterController;
import model.SportCenter;
import view.ConsoleView;

import javax.swing.text.View;

public class Main {
    public static void main(String[] args) {
        final int SIZE_ACTIVITY_ARRAY = 20;
        final int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY = 20;
        final int SIZE_MEMBER_ARRAY = 20;

        MemberController memController = new MemberController();
        ActivityController actController = new ActivityController();
        SportCenterController sprtController = new SportCenterController();
        PrincipalController prinController = new PrincipalController();

        SportCenter sportCenter = prinController.startApp(SIZE_ACTIVITY_ARRAY, SIZE_MEMBER_ARRAY);

    }
}