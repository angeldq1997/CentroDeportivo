package view;

import model.SportCenter;
import utils.Utils;

import java.util.Scanner;

public final class ConsoleView {

    /**
     * Función que muestra en pantalla un mensaje concreto.
     * @param message La cadena de texto del mensaje a mostrar en pantalla.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Función que muestra en pantalla un mensaje de error.
     * @param message La cadena de texto a mostrar en pantalla sobre el error (sin la palabra error que ya la añade la función).
     */
    public static void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Función que muestra el menú principal, pide al usuario un input de un entero y lo devuelve.
     * @param sportCenter Centro deportivo a tratar (para obtener su nombre y mostrarlo).
     * @param min Número entero mínimo para mostrar las opciones.
     * @param max Número entero máximo para mostrar las opciones.
     * @return Un entero con la opción seleccionada.
     */
    public static int showPrincipalMenuAndReadOption(SportCenter sportCenter, int min, int max) {
        System.out.println("\n----✨✨ MENU CENTRO DEPORTIVO " + sportCenter.getName().toUpperCase() + " ✨✨----\n\t0. Salir.\n\t1. Gestionar socios.\n\t2. Gestionar actividades.\n\t3. Inscripciones.\n\t4. Cuotas.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    /**
     * Función que muestra el SUBMENÚ DE SOCIOS, pide al usuario un input de un entero y lo devuelve.
     * @param min Número entero mínimo para mostrar las opciones.
     * @param max Número entero máximo para mostrar las opciones.
     * @return Un entero con la opción seleccionada.
     */
    public static int showMemberMenuAndReadOption(int min, int max) {
        System.out.println("\n----👨🏻👩🏻 MENÚ SOCIOS 👧🏻👦🏻----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de socios.\n\t2. Información de socio.\n\t3. Actualizar datos del socio.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    /**
     * Función que muestra el SUBMENÚ DE ACTIVIDADES, pide al usuario un input de un entero y lo devuelve.
     * @param min Número entero mínimo para mostrar las opciones.
     * @param max Número entero máximo para mostrar las opciones.
     * @return Un entero con la opción seleccionada.
     */
    public static int showActivityMenuAndReadOption(int min, int max) {
        System.out.println("\n----🏌️🏋️🏃‍➡️ MENÚ ACTIVIDADES 🏃🧗‍♂️⛹️----\n\t0. Salir al menú principal.\n\t1. Mostrar listado de actividades.\n\t2. Mostrar socios de una actividad.\n\t3. Mostrar detalles de actividad concreta.\n\t4. Dar de baja una actividad.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    /**
     * Función que muestra el SUBMENÚ DE INSCRIPCIONES, pide al usuario un input de un entero y lo devuelve.
     * @param min Número entero mínimo para mostrar las opciones.
     * @param max Número entero máximo para mostrar las opciones.
     * @return Un entero con la opción seleccionada.
     */
    public static int showInscriptionsMenuAndReadOption(int min, int max) {
        System.out.println("\n----📃📋 MENÚ INSCRIPCIONES 📋📃----\n\t0. Salir al menú principal.\n\t1. Inscribir nuevo socio.\n\t2. Registrar nueva actividad.\n\t3. Inscribir socio existente en actividad.\n\t4. Darse de baja en actividad.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    /**
     * Función que muestra el SUBMENÚ DE CUOTAS, pide al usuario un input de un entero y lo devuelve.
     * @param min Número entero mínimo para mostrar las opciones.
     * @param max Número entero máximo para mostrar las opciones.
     * @return Un entero con la opción seleccionada.
     */
    public static int showFeeMenuAndReadOption(int min, int max) {
        System.out.println("----🪙💶🪙 MENÚ CUOTAS 🪙💶🪙----\n\t0. Salir al menú principal.\n\t1. Calcular cuota mensual socio.\n\t2. Marcar cuota como pagada.\n\t3. Ver total pendiente (resto del año).\n\t4. Ver cuota de un mes concreto.");
        return Utils.readIntInRange(min, max, "Introduce opción: ", "Error, no ha introducido un número entre " + min + " y  " + max + ".");
    }

    /**
     * Pide al usuario una cadena de texto que se devuelve para que trabajen con ella.
     * @param messageAskString Mensaje a enviar por pantalla al usuario para pedir la cadena de forma más específica.
     * @return La cadena de texto con lo introducido por el usuario.
     */
    public static String askStringUser(String messageAskString) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(messageAskString);
        return keyboard.nextLine();
    }

    /**
     * Pide al usuario un DNI que se devuelve para que trabajen con él, verifica que es válido también.
     * @return El DNI con lo introducido por el usuario (deben ser 8 dígitos y una letra).
     */
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

    /**
     * Pide al usuario un nombre por pantalla.
     * @return La cadena de texto con el nombre introducido.
     */
    public static String askNameMember() {
        return askStringUser("Introduce nombre: ");
    }

    /**
     * Pide por pantalla un ID del socio/a a buscar.
     * @return Un entero que será el ID del socio/a a buscar.
     */
    public static int askIdSearchMember() {
        return Utils.readIntInRange(0, 100000, "Introduce ID del socio/a: ", "Ha introducido una ID no válida.");
    }

    /**
     * Pide por pantalla el ID de una actividad.
     * @return Un entero que será el ID de la actividad a buscar.
     */
    public static int askIdSearchActivity() {
        return Utils.readIntInRange(0, 100000, "Introduce ID actividad: ", "Ha introducido una ID no válida.");
    }

    /**
     * Pide por pantalla un mes concreto como entero (1 a 12).
     * @return El entero del més introducido (1 a 12).
     */
    public static int askMonth() {
        return Utils.readIntInRange(1, 12, "Introduce mes en formato numérico (1 = ENERO, 2 = Febrero...): ", "Error, debes introducir un número entre 1 y 12.");
    }

    /**
     * Pide por pantalla la edad del socio para inscribirlo o actualizarlo.
     * @return El entero con la edad introducido entre 1 y 150.
     */
    public static int askAge() {
        return Utils.readIntInRange(1, 150, "Introduce edad del socio/a: ", "No ha introducido una edad válida, debe estar entre 1 y 150.");
    }

    /**
     * Pide el nombre del Centro Deportivo con el cual se va a trabajar.
     * @return Devuelve una cadena de texto con el nombre del centro deportivo.
     */
    public static String askNameSportCenter() {
        return askStringUser("Introduce nombre del Centro Deportivo: ");
    }

    /**
     * Función que pide por pantalla el nivel de intensidad de una actividad.
     * @return Una cadena con el texto del nivel de actividad (Iniciación, Intermedio o Avanzado).
     * @throws Exception Lanza excepción si no se introduce de forma precisa el texto o el número del nivel de actividad.
     */
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