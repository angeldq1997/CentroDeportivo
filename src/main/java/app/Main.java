package app;

import controller.PrincipalController;

public class Main {
    public static void main(String[] args) {
        final int SIZE_ACTIVITY_ARRAY = 20;
        final int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY = 20;
        final int SIZE_MEMBER_ARRAY = 20;

        //Inicio controlador principal
        PrincipalController prinController = new PrincipalController(SIZE_ACTIVITY_ARRAY, SIZE_MEMBER_ARRAY);
        //Entramos en el menú del programa
        prinController.showPrincipalMenu(SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY);
    }
}