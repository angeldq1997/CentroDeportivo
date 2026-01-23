package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import utils.Utils;
import view.ConsoleView;

public class SportCenterController {
    private ActivityController actController;
    private MemberController memController;
    private SportCenter actualSportCenter;

    public Member[] obtainMembersNotNull() {
        Member[] notNullMembers = new Member[Utils.countArrayFilled(actualSportCenter.getMembers())];
        int counter = 0;
        for (int i = 0; i < actualSportCenter.getMembers().length; i++) {
            notNullMembers[counter] = actualSportCenter.getMembers()[i];
            counter++;
        }
        return notNullMembers;
    }

    public Activity[] obtainActivitiesNotNull() {
        Activity[] onlyNotNullActivities = new Activity[Utils.countArrayFilled(actualSportCenter.getActivities())];
        int counter = 0;
        for (int i = 0; i < actualSportCenter.getActivities().length; i++) {
            if(actualSportCenter.getActivities()[i] != null){
                onlyNotNullActivities[counter] = actualSportCenter.getActivities()[i];
                counter++;
            }
        }
        return onlyNotNullActivities;
    }

    public boolean registerNewMember() throws Exception {
        boolean registerSuccessful = false;
        if(existsMemberWithDni()){
            throw new Exception("Error, el DNI seleccionado ya está registrado.");
        } else{
            for (int i = 0; i < actualSportCenter.getMembers().length && !registerSuccessful; i++) {
                if(actualSportCenter.getMembers()[i] == null){
                    String dni = ConsoleView.askDniMember();
                    String name = ConsoleView.askNameMember();
                    int age = ConsoleView.askAge();
                    int sizeMembersInscribed = Utils.readIntInRange(1, 40, "Introduce número máximo actividades para inscribirse: ", "Error, debe introducir un número entre 1 y 40.")
                    actualSportCenter.getMembers()[i] = memController.memberCreated(dni, name, age, sizeMembersInscribed);
                    registerSuccessful = true;
                }
            }
        }
        return registerSuccessful;
    }

    public boolean registerActivity() throws Exception {
        boolean activityRegisteredSuccessfully = false;
        if(Utils.countArrayFilled(actualSportCenter.getActivities()) == actualSportCenter.getActivities().length){
            throw new Exception("Error, no se ha podido registrar la actividad, está completo el registro.");
        }else {
            for (int i = 0; i < actualSportCenter.getActivities().length && !activityRegisteredSuccessfully; i++) {
                if (actualSportCenter.getActivities()[i] == null) {
                    String activityName = ConsoleView.askStringUser("Introduce nombre de la actividad: ");
                    int minuteDuration = Utils.readIntInRange(1, 300, "Introduce minutos de duración de la actividad: ", "Error, ha introducido un valor inválido debe estar entre 1 y 300.");
                    String level = ConsoleView.askLevelIntensityActivity();
                    double monthlyPrice = Utils.readIntInRange(1, 40, "Introduce precio mensual de la actividad: ", "Error, debe introducido un valor entre 1 y 40.");
                    int sizeMembers = Utils.readIntInRange(1, 40, "Introduce número de miembros de la actividad: ", "Error, debe introducir un número entre 1 y 40.")
                    actualSportCenter.getActivities()[i] = actController.activityCreated(activityName, minuteDuration, level, monthlyPrice, sizeMembers);
                    activityRegisteredSuccessfully = true;
                }
            }
        }
        return activityRegisteredSuccessfully;
    }

    private boolean subscribeMemberOnActivity(Activity activityToSubscribe, Member actualMember){
        boolean subscribedSuccessful = false;
        for (int i = 0; i < actualMember.getActivitiesInscribed().length; i++) {
            if(actualMember.getActivitiesInscribed()[i] == null){
                actualMember.getActivitiesInscribed()[i] = activityToSubscribe;
                subscribedSuccessful = true;
            }
        }
        return subscribedSuccessful;
    }

    public boolean subscribeMemberOnFoundActivity(int memberId, int activityId) throws Exception {
        boolean subscriptionSuccessful = false;
        if(!existsMemberWithId(memberId)){
            throw new Exception("Error, no existe un socio con esta ID.");
        } else if (!existsActivityWithId(activityId)) {
            throw new Exception("Error, no existe una actividad con esta ID.");
        } else{
            if( subscribeMemberOnActivity(this.findActivityById(activityId), findMemberById(memberId)) ){
                subscriptionSuccessful = true;
            }else{
                throw new Exception("Error, no se ha podido suscribir el miembro en la actividad.");
            }
        }
        return subscriptionSuccessful;
    }

    public boolean existsMemberWithId(int id){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < actualSportCenter.getMembers().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == id) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public boolean existsActivityWithId(int id){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getActivityId() == id) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public boolean existsActivityWithName (String name){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getName().equalsIgnoreCase(name)) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public boolean existsMemberWithDni (){
        boolean isAlreadyRegistered = false;
        String dni = ConsoleView.askDniMember();
        for (int i = 0; i < actualSportCenter.getMembers().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getDni() == dni) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public Member findMemberById() throws Exception {
        boolean isMemberFound = false;
        Member memberFound = null;
        int id = ConsoleView.askIdSearchMember();
        if (id < 0){
            throw new Exception("Error, la ID introducida no es válida.");
        }
        for (int i = 0; i < actualSportCenter.getMembers().length && !isMemberFound; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == id) ){
                memberFound = actualSportCenter.getMembers()[i];
                isMemberFound = true;
            }
        }
        if (memberFound == null){
            throw new Exception("Error, no se ha encontrado el socio con la ID introducida.");
        }
        return memberFound;
    }

    public Activity findActivityById(int id) throws Exception {
        boolean isActivityFound = false;
        Activity activityFound = null;
        if (id < 0){
            throw new Exception("Error, la ID introducida no es válida.");
        }
        for (int i = 0; i < actualSportCenter.getActivities().length && !isActivityFound; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getActivityId() == id) ){
                activityFound = actualSportCenter.getActivities()[i];
                isActivityFound = true;
            }
        }
        if (activityFound == null){
            throw new Exception("Error, no se ha encontrado el socio con la ID introducida.");
        }
        return activityFound;
    }

    public int findMemberPositionById (int id){
        boolean isMemberFound = false;
        int positionMemberFound = -1;
        for (int i = 0; i < actualSportCenter.getMembers().length && !isMemberFound; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == id) ){
                positionMemberFound = i;
                isMemberFound = true;
            }
        }
        return positionMemberFound;
    }

    public int findActivityPositionById (int id) throws Exception {
        boolean isActivityFound = false;
        int positionActivityFound = -1;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isActivityFound; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getActivityId() == id) ){
                positionActivityFound = i;
                isActivityFound = true;
            }
        }
        if(positionActivityFound == -1) {
            throw new Exception("Error, el ID de la actividad a buscar no está en el sistema, por lo que no se puede determinar su posición.");
        }
        return positionActivityFound;
    }

    public Activity searchActivityById() throws Exception {
        Activity activityToFind = null;
        boolean activityFound = false;
        int activityId = ConsoleView.askIdSearchActivity();
        if (!existsActivityWithId(activityId)){
            throw new Exception("Error, ID introducido no existe en el sistema.");
        }else{
            for (int i = 0; i < actualSportCenter.getActivities().length && !activityFound; i++) {
                if (actualSportCenter.getActivities()[i].getActivityId() == activityId){
                    activityToFind = actualSportCenter.getActivities()[i];
                    activityFound = true;
                }
            }
        }
        return activityToFind;
    }

    public Member searchMemberById() throws Exception {
        Member memberToFind = null;
        int memberId = ConsoleView.askIdSearchMember();
        if(findMemberById() != null){
            memberToFind = findMemberById();
        }
        return memberToFind;
    }

    public String listMembersOfActivity() throws Exception {
        int activityId = ConsoleView.askIdSearchActivity();
        String listMembersOfActivity = "";
        for (int i = 0; i < findActivityById(activityId).getMembersInscribed().length; i++) {
            listMembersOfActivity += findActivityById(activityId).getMembersInscribed()[i];
        }
        return listMembersOfActivity;
    }

    public boolean removeActivity() throws Exception {
        boolean activityRemoved = false;
        int activityId = ConsoleView.askIdSearchActivity();
        int position = findActivityPositionById(activityId);
        actualSportCenter.getActivities()[position] = null;
        if(actualSportCenter.getActivities()[position] == null){
            ConsoleView.showMessage("Actividad borrada correctamente.");
        }else{
            ConsoleView.showError("la actividad no ha podido ser borrada.");
        }
        activityRemoved = true;
        return activityRemoved;
    }
}