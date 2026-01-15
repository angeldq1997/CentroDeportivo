package view;

import utils.Utils;

public class ViewConsole {

    public void showPrincipalMenu (){
        boolean stayOnMenu = true;
        do{
            System.out.println("----MENU----\n\t0. Salir.\n\t1. Gestionar socios.\n\t2. Gestionar actividades.\n\t3. Inscripciones.\n\t4.Cuotas.");
            int option = Utils.readIntInRange(0, 4, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 4.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del programa, gracias por su tiempo.");
                    break;
                case 1:

                    break;

            }
        }while(stayOnMenu);
    }

    public void memberMenu (){
        boolean stayOnMenu  = true;
        do{
            System.out.println("----MENÚ SOCIOS----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de socios.\n\t2. Información de socio.\n\t3. PENDIENTE.\n\t4.PENDIENTE.");
            int option = Utils.readIntInRange(0, 4, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 4.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:

                    break;
            }
        }while(stayOnMenu);
    }

    public void activityMenu (){
        boolean stayOnMenu  = true;
        do{
            System.out.println("----MENÚ ACTIVIDADES----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de actividades.\n\t2. Mostrar socios de una actividad.\n\t3. PENDIENTE.\n\t4.PENDIENTE.");
            int option = Utils.readIntInRange(0, 4, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 4.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:

                    break;
            }
        }while(stayOnMenu);
    }
}