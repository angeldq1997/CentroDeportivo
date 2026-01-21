package model;

import utils.Utils;

import java.util.Arrays;

public class Member {
    private int memberId;
    private String dni;
    private String name;
    private int  age;
    private Activity[] activitiesInscribed;
    private double[] monthlyFees;
    private boolean[] payedFees;
    private static int nextMember;

    //Constructor registrar socios nuevos
    public Member(String dni, String name, int age, Activity[] activitiesInscribed, double[] monthlyFees, boolean[] payedFees) {
        this.memberId = nextMember++;
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.activitiesInscribed = activitiesInscribed;
        this.monthlyFees = monthlyFees;
        this.payedFees = payedFees;
    }

    public Member(String dni, String name, int age, Activity[] activitiesInscribed) {
        this.memberId = nextMember++;
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.activitiesInscribed = activitiesInscribed;
        this.monthlyFees = new double[12];
        this.payedFees = new boolean[12];
    }

    public Member() {
        this.memberId = -1;
        this.dni = "12345678A";
        this.age = 0;
        this.name = "Desconocido";
        this.activitiesInscribed = new Activity[20];
        this.monthlyFees = new double[12];
        this.payedFees = new boolean[12];
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getActivitiesInscribed() {
        String onlyNotNullActivities = "";
        int counter = 0;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if (this.activitiesInscribed[i] != null) {
                onlyNotNullActivities = String.valueOf(this.activitiesInscribed[i]);
                counter++;
            }
        }
        return onlyNotNullActivities;
    }

    public String getPayedFees() {
        String listOfFees = "";
        for (int i = 0; i < this.payedFees.length; i++) {
            if(!this.payedFees[i]){
                listOfFees = (i+1) + " NO PAGADO" + " | ";
            }else{
                listOfFees = (i+1) + " PAGADO" + " | ";
            }
        }
        return listOfFees;
    }

    public void setPayedFees(boolean[] payedFees) {
        this.payedFees = payedFees;
    }

    public String getMonthlyFees() {
        String feesCollected = "";
        for (int i = 0; i < this.monthlyFees.length; i++) {
            if(this.monthlyFees[i] != 0.0){
                feesCollected = (i+1) + this.monthlyFees[i] + " | ";
            }
        }
        return feesCollected;
    }

    public void setMonthlyFees(double[] monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public boolean subscribeMemberOnActivity(Activity activityToSubscribe){
        boolean subscribedSuccessful = false;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if(this.activitiesInscribed[i] == null){
                this.activitiesInscribed[i] = activityToSubscribe;
                subscribedSuccessful = true;
            }
        }
        return subscribedSuccessful;
    }

    public int findExactActivityPosition(int activityId){
        int foundActivity = -1;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if(!(this.activitiesInscribed[i] == null || this.activitiesInscribed[i].getActivityId() == -1) && this.activitiesInscribed[i].getActivityId() == activityId){
                foundActivity = i;
            }
        }
        return foundActivity;
    }

    public Activity findActivity(int activityIdToFind){
        Activity foundActivity = null;
        if( activityIdToFind > -1){
            for (Activity activity : activitiesInscribed) {
                if (activity.getActivityId() != -1 && activity.getActivityId() == activityIdToFind) {
                    foundActivity = activity;
                }
            }
        }
        return foundActivity;
    }

    public boolean unsubscribeActivity(int activityId) throws Exception {
        boolean unsubscribedSuccessful = false;
        if( activityId > -1){
            if(this.findActivity(activityId) != null){
                this.activitiesInscribed[this.findExactActivityPosition(activityId)] = null;
                unsubscribedSuccessful = true;
            }
        }else{
            throw new Exception("Error, la id de Actividad introducida es inválida, debe ser mayor de -1.");
        }
        return unsubscribedSuccessful;
    }

    public void recalculateMonthlyFees(int actualMonth){
        double totalMonth = 0.0;
        for (int i = actualMonth; i < this.monthlyFees.length; i++) {
            for (Activity activity : this.activitiesInscribed) {
                totalMonth += activity.getMonthlyPrice();
            }
            this.monthlyFees[i] = totalMonth;
        }
    }

    public double actualFee () {
        double actualFee = 0.0;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if(this.activitiesInscribed[i] !=null){
                actualFee += this.activitiesInscribed[i].getMonthlyPrice();
            }
        }
        return actualFee;
    }

    public double yearlyFee (){
        double total = 0.0;
        for (double monthlyFee : this.monthlyFees) {
            total += monthlyFee;
        }
        return total;
    }

    public double feeOfExactMonth (int monthToSearch) throws Exception {
        double exactFee = 0.0;
        //En el array queremos buscar un mes que han introducido, para facilitar al usuario que se introduzca 1 = enero,
        // reducimos ese número una vez para que corresponda con el array.
        monthToSearch--;
        if (monthToSearch < 1 || monthToSearch > 12){
            throw new Exception("Error, mes introducido inválido, debe introducir un número entre 1 y 12.");
        } else{
            exactFee = this.monthlyFees[monthToSearch];
        }
        return exactFee;
    }

    public double yearLeftFee (int monthToSearch){
        monthToSearch--;
        double yearLeftTotal = 0.0;
        recalculateMonthlyFees(monthToSearch);
        for (int i = monthToSearch; i < this.monthlyFees.length; i++) {
            yearLeftTotal += this.monthlyFees[i];
        }
        return yearLeftTotal;
    }

    public void modifyPayedMonth(int monthToCheck, boolean statusPayment){
        monthToCheck--;
        this.payedFees[monthToCheck] = statusPayment;
    }

    public String showOnlyInscribedActivities(){
        String onlyInscribedActivities = "";
        for (Activity activity : this.activitiesInscribed) {
            if (activity != null) {
                onlyInscribedActivities += activity.getName() + " " + activity.getMonthlyPrice() + "\n";
            }
        }
        return onlyInscribedActivities;
    }

    @Override
    public String toString() {
        return  "\nID socio = " + this.memberId +
                "\nDNI = " + this.dni +
                "\nEdad = " + this.age +
                "\nNombre = " + this.name +
                "\nActividades inscritas = " + getActivitiesInscribed() +
                "\nCuotas mensuales = " + getMonthlyFees() +
                "\nCuotas pagadas = " + getPayedFees() + ".\n\n";
    }

    @Override
    public boolean equals(Object obj) {
        boolean membersAreEquals = false;
        if (obj instanceof Member memberToCheck){
            if (this.memberId == memberToCheck.getMemberId() && this.dni.equals(memberToCheck.getDni())) {
                    membersAreEquals = true;
            }
        }
        return membersAreEquals;
    }
}