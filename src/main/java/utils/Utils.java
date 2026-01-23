package utils;

import view.ConsoleView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    private static final Scanner keyboard = new Scanner(System.in);

    public static int countArrayFilled(Object[] objects){
        int total = 0;
        if(objects != null){
            for (Object object : objects) {
                if (object != null) {
                    total++;
                }
            }
        }else{
            total = -1;
        }
        return total;
    }

    public static String readTextNotEmpty(String message){
        boolean readTextValid = false;
        String text = "";
        do{
            System.out.println(message);
            text = keyboard.nextLine();
            if (text.isEmpty()){
                ConsoleView.showError("debes introducir texto, vuelve a intentarlo.");
            }else{
                text = text.trim();
                readTextValid = true;
            }
        }while(readTextValid);
        return text;
    }

    public static boolean verifyDni(String dni){
        return ((dni != null || dni.isEmpty()) && dni.matches("[0-9]{8}[A-Z a-z]"));
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