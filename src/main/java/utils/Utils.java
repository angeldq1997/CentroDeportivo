package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {



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

    public boolean verifyDni(String dni){
        boolean validDni = false;
        if ((dni != null || dni != "") && dni.matches("[0-9]{8}[A-Z a-z]")){
            validDni = true;
        }
        return validDni;
    }
}