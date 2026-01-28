package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import utils.Utils;
import view.ConsoleView;

public class SportCenterController {
    private SportCenter actualSportCenter;

    public SportCenterController(SportCenter actualSportCenter) {
        this.actualSportCenter = actualSportCenter;
    }

    public void setActualSportCenter(SportCenter sportCenter){
        actualSportCenter = sportCenter;
    }

    public Member[] obtainMembersNotNull() {
        Member[] notNullMembers = new Member[Utils.countArrayFilled(actualSportCenter.getMembers())];
        int counter = 0;
        for (int i = 0; i < actualSportCenter.getMembers().length; i++) {
            if(actualSportCenter.getMembers()[i] != null) {
                notNullMembers[counter] = actualSportCenter.getMembers()[i];
                counter++;
            }
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

    public boolean subscribeMemberOnFoundActivity() throws Exception {
        boolean subscriptionSuccessful = false;
        boolean subscriptionSuccessful2 = false;
        int memberId = ConsoleView.askIdSearchMember();
        int activityId = ConsoleView.askIdSearchActivity();
        Member actualMember = findMemberById(memberId);
        Activity foundActivity = findActivityById(activityId);
        if(!existsMemberWithId(memberId) && !existsActivityWithId(activityId)){
            throw new Exception("Error, no existe un socio/actividad con este ID.");
        }
        if(foundActivity.getNotNullMembersInscribed().length != foundActivity.getMembersInscribed().length){
            throw new Exception("Error, la actividad ya está llena.");
        }
        if(memberAlreadySubscribedActivity(foundActivity, memberId)){
            throw new Exception("Error, el socio ya está inscrito en esta actividad.");
        }else {
            for (int i = 0; i < foundActivity.getMembersInscribed().length; i++) {
                if (foundActivity.getMembersInscribed()[i] == null) {
                    foundActivity.getMembersInscribed()[i] = actualMember;
                }
            }
            for (int i = 0; i < actualMember.getActivitiesInscribed().length && !subscriptionSuccessful; i++) {
                if (actualMember.getActivitiesInscribed()[i] == null) {
                    actualMember.getActivitiesInscribed()[i] = findActivityById(activityId);
                    subscriptionSuccessful = true;
                }
            }
        }
        return subscriptionSuccessful;
    }

    public boolean memberAlreadySubscribedActivity(Activity activityToCheck, int memberIdToCheck){
        boolean memberIsAlreadySubscribed = false;
        for (int i = 0; i < activityToCheck.getMembersInscribed().length && !memberIsAlreadySubscribed; i++) {
            if(activityToCheck.getMembersInscribed()[i] != null && activityToCheck.getMembersInscribed()[i].getMemberId() == memberIdToCheck ){
                memberIsAlreadySubscribed = true;
            }
        }
        return memberIsAlreadySubscribed;
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
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getDni().equalsIgnoreCase(dni)) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public Member findMemberById(int memberId) throws Exception {
        boolean isMemberFound = false;
        Member memberFound = null;
        for (int i = 0; i < actualSportCenter.getMembers().length && !isMemberFound; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == memberId) ){
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
        for (int i = 0; i < this.actualSportCenter.getMembers().length; i++) {
            if(this.actualSportCenter.getMembers()[i] != null && this.actualSportCenter.getMembers()[i].getMemberId() == memberId){
                memberToFind = this.actualSportCenter.getMembers()[i];
            }
        }
        if(memberToFind == null){
            throw new Exception("El socio a buscar con la ID introducida no existe.");
        }
        return memberToFind;
    }

    public String listMembersOfActivity() throws Exception {
        int activityId = ConsoleView.askIdSearchActivity();
        String listMembersOfActivity = "";
        Activity foundActivity = findActivityById(activityId);
        for (int i = 0; i < foundActivity.getMembersInscribed().length; i++) {
            if(foundActivity.getMembersInscribed()[i] != null) {
                listMembersOfActivity += foundActivity.getMembersInscribed()[i];
            }
        }
        return listMembersOfActivity;
    }

    public String listActivities() throws Exception {
        String listActivities = "";
        if(obtainActivitiesNotNull().length == 0){
            throw new Exception("Error, no hay actividades en el centro deportivo.");
        }
        for (int i = 0; i < obtainActivitiesNotNull().length; i++) {
            if(obtainActivitiesNotNull()[i] != null){
                listActivities += obtainActivitiesNotNull()[i].toString();
            }
        }
        return listActivities;
    }

    public String listMembers() throws Exception {
        String listMembers = "";
        if(obtainMembersNotNull() == null ) {
            throw new Exception("Error, no hay socios en el registro del centro deportivo.");
        }
        for (int i = 0; i < obtainMembersNotNull().length; i++) {
            if(obtainMembersNotNull()[i] != null){
                listMembers += obtainMembersNotNull()[i].toString();
            }
        }
        return listMembers;
    }

    public boolean removeActivity() throws Exception {
        boolean activityRemoved = false;
        int activityId = ConsoleView.askIdSearchActivity();
        if(existsActivityWithId(activityId)) {
            for (int i = 0; i < this.actualSportCenter.getActivities().length && !activityRemoved; i++) {
                if (this.actualSportCenter.getActivities()[i] != null && this.actualSportCenter.getActivities()[i].getActivityId() == activityId) {
                    this.actualSportCenter.getActivities()[i] = null;
                    activityRemoved = true;
                }
            }
        }else{
            throw new Exception("Error, no se puede eliminar la actividad con el ID introducido, es posible que no exista.");
        }
        return activityRemoved;
    }
}