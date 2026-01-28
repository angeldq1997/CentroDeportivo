package view;

import model.SportCenter;
import utils.Utils;

import java.util.Scanner;

public final class ConsoleView {
    private ConsoleView() {

    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    public static int showPrincipalMenuAndReadOption(SportCenter sportCenter, int min, int max) {
        System.out.println("\n----✨✨ MENU CENTRO DEPORTIVO " + sportCenter.getName().toUpperCase() + " ✨✨----\n\t0. Salir.\n\t1. Gestionar socios.\n\t2. Gestionar actividades.\n\t3. Inscripciones.\n\t4. Cuotas.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    public static int showMemberMenuAndReadOption(int min, int max) {
        System.out.println("\n----👨🏻👩🏻 MENÚ SOCIOS 👧🏻👦🏻----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de socios.\n\t2. Información de socio.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    public static int showActivityMenuAndReadOption(int min, int max) {
        System.out.println("\n----🏌️🏋️🏃‍➡️ MENÚ ACTIVIDADES 🏃🧗‍♂️⛹️----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de actividades.\n\t2. Mostrar socios de una actividad.\n\t3. Mostrar detalles de actividad concreta.\n\t4. Dar de baja una actividad.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    public static int showInscriptionsMenuAndReadOption(int min, int max) {
        System.out.println("\n----📃📋 MENÚ INSCRIPCIONES 📋📃----\n\t0. Salir al menú principal.\n\t1. Inscribir nuevo socio.\n\t2. Registrar nueva actividad.\n\t3. Inscribir socio existente en actividad.\n\t4. Darse de baja en actividad.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    public static int showFeeMenuAndReadOption(int min, int max) {
        System.out.println("----🪙💶🪙 MENÚ CUOTAS 🪙💶🪙----\n\t0. Salir al menú principal.\n\t1. Calcular cuota mensual socio.\n\t2. Marcar cuota como pagada.\n\t3. Ver total pendiente (resto del año).\n\t4. Ver cuota de un mes concreto.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }


    public static String askStringUser(String messageAskString) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(messageAskString);
        return keyboard.nextLine();
    }

    public static String askDniMember() {
        boolean validDni = false;
        String dni = "";
        do {
            dni = askStringUser("Introduce DNI: ");
            if (Utils.verifyDni(dni)) {
                validDni = true;
            } else {
                showError("debes escribir un DNI válido (8 dígitos y una letra).");
            }
        } while (!validDni);
        return dni;
    }

    public static String askNameMember() {
        return askStringUser("Introduce nombre: ");
    }

    public static int askIdSearchMember() {
        return Utils.readIntInRange(0, 100000, "Introduce ID del socio/a: ", "Ha introducido una ID no válida.");
    }

    public static int askIdSearchActivity() {
        return Utils.readIntInRange(0, 100000, "Introduce ID actividad: ", "Ha introducido una ID no válida.");
    }

    public static int askMonth() {
        return Utils.readIntInRange(1, 12, "Introduce mes en formato numérico (1 = ENERO, 2 = Febrero...): ", "Error, debes introducir un número entre 1 y 12.");
    }

    public static int askAge() {
        return Utils.readIntInRange(1, 150, "Introduce edad del socio/a: ", "No ha introducido una edad válida, debe estar entre 1 y 150.");
    }

    public static String askNameSportCenter() {
        return askStringUser("Introduce nombre del Centro Deportivo: ");
    }

    public static String askLevelIntensityActivity() throws Exception {
        Scanner keyboard = new Scanner(System.in);
        String levelIntensity = askStringUser("Introduzca nivel de intensidad de la actividad (iniciación, intermedio, avanzado): ");
        levelIntensity = levelIntensity.trim();
        if ((levelIntensity.equalsIgnoreCase("Iniciación") || levelIntensity.equalsIgnoreCase("Intermedio") || levelIntensity.equalsIgnoreCase("Avanzado"))) {
            ConsoleView.showMessage("Nivel seleccionado.");
        }else if (levelIntensity.equals("1") || levelIntensity.equals("2") || levelIntensity.equals("3")) {
            switch (levelIntensity) {
                case "1" -> levelIntensity = "Iniciación";
                case "2" -> levelIntensity = "Intermedio";
                case "3" -> levelIntensity = "Avanzado";
                default -> throw new Exception("Error, no has introducido un nivel de intensidad válido (1/2/3).");
            }
        }else{
            throw new Exception("Error, no has introducido un nivel de intensidad válido.");
        }
        return levelIntensity;
    }
}