package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import view.ViewConsole;

public class ActivityController {

    public boolean registerActivity (SportCenter sportCenter, int SIZE_MEMBERS_INSCRIBED) throws Exception {
        boolean activityRegisteredSuccessfully = false;
        String activityName = ViewConsole.askStringUser("Introduce nombre de la actividad: ");
        int minuteDuration = ViewConsole.readIntInRange(1, 300, "Introduce minutos de duración de la actividad: ", "Error, ha introducido un valor inválido debe estar entre 1 y 300.");
        String level = ViewConsole.askLevelIntensityActivity();
        double monthlyPrice = ViewConsole.readIntInRange(1, 40, "Introduce precio mensual de la actividad: ", "Error, debe introducido un valor entre 1 y 40.");
        sportCenter.registerNewActivity(activityName, minuteDuration, level, monthlyPrice, SIZE_MEMBERS_INSCRIBED);
        activityRegisteredSuccessfully = true;
        return activityRegisteredSuccessfully;
    }

    public Activity searchActivityById (SportCenter sportCenter) throws Exception {
        Activity activityToFind = null;
        boolean activityFound = false;
        int activityId = ViewConsole.askIdSearchActivity();
        if (!sportCenter.existsActivityWithId(activityId)){
            throw new Exception("Error, ID introducido no existe en el sistema.");
        }else{
            for (int i = 0; i < sportCenter.getActivities().length && !activityFound; i++) {
                if (sportCenter.getActivities()[i].getActivityId() == activityId){
                    activityToFind = sportCenter.getActivities()[i];
                    activityFound = true;
                }
            }
        }
        return activityToFind;
    }

    public Member[] listMembersOfActivity (SportCenter sportCenter) throws Exception {
        int activityId = ViewConsole.askIdSearchActivity();
        return sportCenter.findActivityById(activityId).getMembersInscribed();
    }

    public boolean removeActivity (SportCenter sportCenter) throws Exception {
        boolean activityRemoved = false;
        int activityId = ViewConsole.askIdSearchActivity();
        int position = sportCenter.findActivityPositionById(activityId);
        sportCenter.getActivities()[position] = null;
        return activityRemoved;
    }
}