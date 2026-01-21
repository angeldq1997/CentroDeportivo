package view;

import controller.ActivityController;
import controller.MemberController;
import model.SportCenter;
import utils.Utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewConsole {
    public static void showPrincipalMenu (SportCenter sportCenter, int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) {
        boolean stayOnMenu = true;
        do{
            System.out.println("----MENU CENTRO DEPORTIVO "+ sportCenter.getName() + "----\n\t0. Salir.\n\t1. Gestionar socios.\n\t2. Gestionar actividades.\n\t3. Inscripciones.\n\t4. Cuotas.");
            int option = readIntInRange(0, 4, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 4.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del programa, gracias por su tiempo.");
                    break;
                case 1:
                    try {
                        memberMenu(sportCenter);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        activityMenu(sportCenter);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        inscriptionsMenu(sportCenter, SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        feeMenu(sportCenter);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Debe introducir un número entre 0 y 4.");
            }
        }while(stayOnMenu);
    }

    public static void memberMenu (SportCenter sportCenter) throws Exception {
        boolean stayOnMenu  = true;
        do{
            System.out.println("----MENÚ SOCIOS----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de socios.\n\t2. Información de socio.");
            int option = readIntInRange(0, 2, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 2.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    System.out.println(MemberController.listMembers(sportCenter));
                    break;
                case 2:
                    System.out.println(MemberController.searchMemberById(sportCenter));
                    break;
                case 3:

            }
        }while(stayOnMenu);
    }

    public static void activityMenu(SportCenter sportCenter) throws Exception {
        boolean stayOnMenu  = true;
        do{
            System.out.println("----MENÚ ACTIVIDADES----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de actividades.\n\t2. Mostrar socios de una actividad.\n\t3. Mostrar detalles de actividad concreta.\n\t4. Dar de baja una actividad.");
            int option = readIntInRange(0, 4, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 4.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    System.out.println(ActivityController.listActivities(sportCenter));
                    break;
                case 2:
                    System.out.println(ActivityController.listMembersOfActivity(sportCenter));
                    break;
                case 3:
                    System.out.println(ActivityController.searchActivityById(sportCenter));
                    break;
                case 4:
                    if(ActivityController.removeActivity(sportCenter)){
                        System.out.println("Actividad eliminada satisfactoriamente.");
                    }else{
                        System.out.println("No se ha podido eliminar la actividad.");
                    }
                    break;
            }
        }while(stayOnMenu);
    }

    public static void inscriptionsMenu(SportCenter sportCenter, int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) throws Exception {
        boolean stayOnMenu  = true;
        do{
            System.out.println("----MENÚ INSCRIPCIONES----\n\t0. Salir al menú principal.\n\t1. Inscribir nuevo socio.\n\t2. Registrar nueva actividad.\n\t3. Inscribir socio existente en actividad.\n\t4. Darse de baja en actividad.");
            int option = readIntInRange(0, 4, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 4.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    if ( MemberController.registerMember(sportCenter) ){
                        System.out.println("Socio registrado correctamente.");
                    }
                    break;
                case 2:
                    if ( ActivityController.registerActivity(sportCenter, SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) ){
                        System.out.println("Actividad registrada correctamente.");
                    }
                    break;
                case 3:
                    if ( MemberController.subscribeMemberOnActivity(sportCenter) ){
                        System.out.println("Socio registrado en actividad correctamente.");
                    }
                    break;
                case 4:
                    if ( MemberController.unsubscribeMemberOnActivity(sportCenter) ){
                        System.out.println("Socio registrado en actividad correctamente.");
                    }
                    break;
                default:
                    System.out.println("Error, debe introducir un valor entre 0 y 4.");
            }
        }while(stayOnMenu);
    }

    public static void feeMenu(SportCenter sportCenter) throws Exception {
        boolean stayOnMenu  = true;
        do{
            System.out.println("----MENÚ CUOTAS----\n\t0. Salir al menú principal.\n\t1. Calcular cuota mensual socio.\n\t2. Marcar cuota como pagada.\n\t3. Ver total pendiente (resto del año).\n\t4. Ver cuota de un mes concreto.");
            int option = readIntInRange(0, 3, "Introduce opción: ", "Error, no ha introducido un número entre 0 y 3.");
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    System.out.println("La cuota mensual es: " + MemberController.calculateMonthlyFeeMember(sportCenter) );
                    break;
                case 2:
                    MemberController.markFeePayed(sportCenter);
                    break;
                case 3:
                    System.out.println("El importe restante es: " + MemberController.totalLeftFeeYear(sportCenter, askMonth()) );
                    break;
                case 4:
                    System.out.println("La cuota del mes es: " + MemberController.feeExactMonth(sportCenter) );
                    break;
                default:
                    System.out.println("Error, debe introducir un valor entre 0 y 4.");
            }
        }while(stayOnMenu);
    }

    public static String askStringUser (String messageAskString){
        Scanner keyboard = new Scanner(System.in);
        System.out.println(messageAskString);
        return keyboard.next();
    }

    public static String askDniMember (){
        boolean validDni = false;
        String dni = "";
        do {
            dni = askStringUser("Introduce DNI: ");
            if(Utils.verifyDni(dni)){
                validDni = true;
            }
        }while(!validDni);
        return dni;
    }

    public static String askNameMember (){
        return askStringUser("Introduce nombre: ");
    }

    public static int askIdSearchMember (){
        return readIntInRange(0,100000,"Introduce ID del socio/a: ", "Ha introducido una ID no válida.");
    }

    public static int askIdSearchActivity () {
        return readIntInRange(0, 100000, "Introduce ID actividad: ", "Ha introducido una ID no válida.");
    }

    public static int askMonth (){
        return readIntInRange(1, 12, "Introduce mes en formato numérico (1 = ENERO, 2 = Febrero...): ", "Error, debes introducir un número entre 1 y 12.");
    }

    public static int askAge (){
        return readIntInRange(1,150, "Introduce edad del socio/a: ", "No ha introducido una edad válida, debe estar entre 1 y 150.");
    }

    public static String askNameSportCenter (){
        return askStringUser("Introduce nombre del Centro Deportivo: ");
    }

    public static String askLevelIntensityActivity() throws Exception {
        Scanner keyboard = new Scanner(System.in);
        String levelIntensity = askStringUser("Introduzca nivel de intensidad de la actividad (iniciación, intermedio, avanzado): ");
        keyboard.nextLine();
        if (! (levelIntensity.equalsIgnoreCase("Iniciación") || levelIntensity.equalsIgnoreCase("Intermedio") || levelIntensity.equalsIgnoreCase("Avanzado")) ){
            throw new Exception("Error, ha introducido un nivel de intensidad inválido.");
        }
        return levelIntensity;
    }


    /**
     * Solicita al usuario un número entero por consola y verifica que la entrada sea correcta.
     * Si el usuario introduce un valor que no es un entero (por ejemplo, texto o decimales),
     * la función muestra un mensaje de error y vuelve a pedir el número hasta que sea válido.
     *
     * @param msn      mensaje que se muestra al usuario para solicitar el número
     * @param msnError mensaje que se muestra cuando la entrada no es válida
     * @return el número entero introducido correctamente por el usuario
     */
    public static int readInt(String msn, String msnError) {
        int result = 0;
        boolean isValid = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(msn);
                result = sc.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println(msnError);
                isValid = false;
                sc.nextLine();

            }
        } while (!isValid);
        return result;
    }

    /**
     * Lee un número entero introducido por el usuario y verifica que esté dentro de un rango
     * específico definido por min y max.
     * Si el número está fuera del rango, se muestra un mensaje de aviso y se vuelve a solicitar la entrada.
     *
     * @param min      valor mínimo permitido (incluido)
     * @param max      valor máximo permitido (incluido)
     * @param msn      mensaje que se muestra para pedir el número
     * @param msnError mensaje que se muestra cuando la entrada no es válida
     * @return un número entero dentro del rango [min, max]
     */
    public static int readIntInRange(int min, int max, String msn, String msnError) {
        int result = 0;
        boolean validNumber = false;
        do {
            result = readInt(msn, msnError);
            if (result >= min && result <= max) {
                validNumber = true;
            } else {
                System.out.println(msnError);
                validNumber = false;
            }
        } while (!validNumber);
        return result;
    }
}