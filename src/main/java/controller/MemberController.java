package controller;

import model.Activity;
import model.Member;
import view.ConsoleView;

public class MemberController {
    private Member actualMember;

    public void updateActualMember(Member member){
        if(member != null) {
            member = this.actualMember;
        }
    }

    public Member memberCreated(String dni, String name, int age, int activitiesInscribed){
        return new Member(dni, name, age, activitiesInscribed);
    }

    public boolean subscribeMemberOnActivity(Activity activityToSubscribe){
        boolean subscribedSuccessful = false;
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if(this.actualMember.getActivitiesInscribed()[i] == null){
                this.actualMember.getActivitiesInscribed()[i] = activityToSubscribe;
                subscribedSuccessful = true;
            }
        }
        return subscribedSuccessful;
    }

    public boolean unsubscribeActivity() throws Exception {
        boolean unsubscribedSuccessful = false;
        int activityId = ConsoleView.askIdSearchActivity();
        if( activityId > -1){
            if(this.findActivityOnInscribed(activityId) != null){
                this.actualMember.getActivitiesInscribed()[this.findExactActivityPosition(activityId)] = null;
                unsubscribedSuccessful = true;
            }
        }else{
            throw new Exception("Error, la id de Actividad introducida es inválida, debe ser mayor de -1.");
        }
        return unsubscribedSuccessful;
    }

    public int findExactActivityPosition(int activityId){
        int foundActivity = -1;
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if(!(this.actualMember.getActivitiesInscribed()[i] == null || this.actualMember.getActivitiesInscribed()[i].getActivityId() == -1) && this.actualMember.getActivitiesInscribed()[i].getActivityId() == activityId){
                foundActivity = i;
            }
        }
        return foundActivity;
    }

    public Activity findActivityOnInscribed(int activityIdToFind){
        Activity foundActivity = null;
        if( activityIdToFind > -1){
            for (Activity activity : actualMember.getActivitiesInscribed()) {
                if (activity.getActivityId() != -1 && activity.getActivityId() == activityIdToFind) {
                    foundActivity = activity;
                }
            }
        }
        return foundActivity;
    }

    public void recalculateMonthlyFees(int actualMonth){
        double totalMonth = 0.0;
        for (int i = actualMonth; i < this.actualMember.getMonthlyFees().length; i++) {
            for (Activity activity : this.actualMember.getActivitiesInscribed()) {
                if(activity != null) {
                    totalMonth += activity.getMonthlyPrice();
                }
            }
            this.actualMember.getMonthlyFees()[i] = totalMonth;
        }
    }

    public double actualFee () {
        double actualFee = 0.0;
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if(this.actualMember.getActivitiesInscribed()[i] !=null){
                actualFee += this.actualMember.getActivitiesInscribed()[i].getMonthlyPrice();
            }
        }
        return actualFee;
    }

    public double yearlyFee (){
        double total = 0.0;
        for (double monthlyFee : this.actualMember.getMonthlyFees()) {
            total += monthlyFee;
        }
        return total;
    }

    public double feeOfExactMonth () throws Exception {
        double exactFee = 0.0;
        //En el array queremos buscar un mes que han introducido, para facilitar al usuario que se introduzca 1 = enero,
        // reducimos ese número una vez para que corresponda con el array.
        int monthToSearch = ConsoleView.askMonth();
        monthToSearch--;
        if (monthToSearch < 1 || monthToSearch > 12){
            throw new Exception("Error, mes introducido inválido, debe introducir un número entre 1 y 12.");
        } else{
            exactFee = this.actualMember.getMonthlyFees()[monthToSearch];
        }
        return exactFee;
    }

    public double yearLeftFee (){
        int monthToSearch = ConsoleView.askMonth();
        monthToSearch--;
        double yearLeftTotal = 0.0;
        recalculateMonthlyFees(monthToSearch);
        for (int i = monthToSearch; i < this.actualMember.getMonthlyFees().length; i++) {
            yearLeftTotal += this.actualMember.getMonthlyFees()[i];
        }
        return yearLeftTotal;
    }

    public boolean markPayedMonth() throws Exception {
        int monthToCheck = ConsoleView.askMonth();
        monthToCheck--;
        if(this.actualMember.getPayedFees()[monthToCheck]){
            throw new Exception("Error, el mes seleccionado ya había sido pagado.");
        }else {
            this.actualMember.getPayedFees()[monthToCheck] = true;
        }
        return this.actualMember.getPayedFees()[monthToCheck];
    }

    public String showOnlyInscribedActivities(){
        String onlyInscribedActivities = "";
        for (Activity activity : this.actualMember.getActivitiesInscribed()) {
            if (activity != null) {
                onlyInscribedActivities += activity.getName() + " " + activity.getMonthlyPrice() + "\n";
            }
        }
        return onlyInscribedActivities;
    }

    public String getPayedFeesOnString() {
        String listOfFees = "";
        for (int i = 0; i < this.actualMember.getPayedFees().length; i++) {
            if(!this.actualMember.getPayedFees()[i]){
                listOfFees = (i+1) + " NO PAGADO" + " | ";
            }else{
                listOfFees = (i+1) + " PAGADO" + " | ";
            }
        }
        return listOfFees;
    }

    public String getNotNullActivitiesInscribed() {
        String onlyNotNullActivities = "";
        int counter = 0;
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if (this.actualMember.getActivitiesInscribed()[i] != null) {
                onlyNotNullActivities = String.valueOf(this.actualMember.getActivitiesInscribed()[i]);
                counter++;
            }
        }
        return onlyNotNullActivities;
    }

    public String getMonthlyFeesOnString() {
        String feesCollected = "";
        for (int i = 0; i < this.actualMember.getMonthlyFees().length; i++) {
            if(this.actualMember.getMonthlyFees()[i] != 0.0){
                feesCollected = (i+1) + this.actualMember.getMonthlyFees()[i] + " | ";
            }
        }
        return feesCollected;
    }
}