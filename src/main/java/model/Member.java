package model;

import utils.Utils;

import java.util.Arrays;

public class Member {
    private int memberId;
    private String dni;
    private int  age;
    private Activity[] activitiesInscribed;
    private double[] monthlyFees;
    private boolean[] payedFees;
    private static int nextMember;

    //Constructor registrar socios nuevos
    public Member(String dni, int age, Activity[] activitiesInscribed, double[] monthlyFees, boolean[] payedFees) {
        this.memberId = nextMember++;
        this.dni = dni;
        this.age = age;
        this.activitiesInscribed = activitiesInscribed;
        this.monthlyFees = monthlyFees;
        this.payedFees = payedFees;
    }

    public Member(String dni, int age) {
        this.memberId = nextMember++;
        this.dni = dni;
        this.age = age;
        this.activitiesInscribed = new Activity[10];
        this.monthlyFees = new double[12];
        this.payedFees = new boolean[12];
    }

    public Member() {
        this.memberId = -1;
        this.dni = "12345678A";
        this.age = 0;
        this.activitiesInscribed = new Activity[10];
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

    public Activity[] getActivitiesInscribed() {
        Activity[] onlyNotNullActivities = new Activity[Utils.countArrayFilled(this.activitiesInscribed)];
        int counter = 0;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if(this.activitiesInscribed[i] != null){
                onlyNotNullActivities[counter] = activitiesInscribed[i];
                counter++;
            }
        }
        return onlyNotNullActivities;
    }

    public boolean[] getPayedFees() {
        return payedFees;
    }

    public void setPayedFees(boolean[] payedFees) {
        this.payedFees = payedFees;
    }

    public double[] getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double[] monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public boolean verifyDni(String dni){
        boolean validDni = false;
        if ((dni != null || dni != "") && dni.matches("[0-9]{8}[A-Z a-z]")){
            validDni = true;
        }
        return validDni;
    }

    public boolean inscribeMemberOnActivity (int memberId, int activityId){
        boolean inscribedCorrectly = false;
        if(memberId > -1  || activityId > -1){
            if (findExactActivityPosition() != -1){

            }
        }
        return inscribedCorrectly;
    }

    public int findExactActivityPosition(){
        int foundActivity = -1;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if(this.activitiesInscribed[i] == null || this.activitiesInscribed[i].getActivityId() == -1 ){
                foundActivity = i;
            }
        }
        return foundActivity;
    }

    public int findExactActivityPosition(int activityId){
        int foundActivity = -1;
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if((this.activitiesInscribed[i] == null || this.activitiesInscribed[i].getActivityId() == -1) && this.activitiesInscribed[i].getActivityId() == activityId){
                foundActivity = i;
            }
        }
        return foundActivity;
    }

    public Activity findActivity(int activityIdToFind){
        Activity foundActivity = null;
        if( activityIdToFind > -1){
            for (int i = 0; i < activitiesInscribed.length; i++) {
                if(activitiesInscribed[i].getActivityId() != -1 && activitiesInscribed[i].getActivityId() == activityIdToFind){
                    foundActivity = activitiesInscribed[i];
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

    public boolean recalculateMonthlyFeesCurrentMonth(int actualMonth){
        boolean recalculateSuccessful = false;
        for (int i = actualMonth; i < this.monthlyFees.length; i++) {
            double totalMonth = 0.0;
            for (int j = 0; j < this.activitiesInscribed.length; j++) {
                totalMonth += this.activitiesInscribed[i].getMonthlyPrice();
            }
            this.monthlyFees[i] = totalMonth;
        }
        return recalculateSuccessful;
    }

    public boolean recalculateMonthlyFees(int actualMonth){
        boolean recalculateSuccessful = false;
        for (int i = actualMonth; i < this.monthlyFees.length; i++) {
            double totalMonth = 0.0;
            for (int j = 0; j < this.activitiesInscribed.length; j++) {
                totalMonth += this.activitiesInscribed[i].getMonthlyPrice();
            }
            this.monthlyFees[i] = totalMonth;
        }
        return recalculateSuccessful;
    }

    public double yearlyFee (){
        double total = 0.0;
        for (int i = 0; i < this.monthlyFees.length; i++) {
            total += this.monthlyFees[i];
        }
        return total;
    }

    public double feeOfExactMonth (int monthToSearch) throws Exception {
        double exactFee = 0.0;
        //En el array queremos buscar un mes que han introducido, para facilitar al usuario que se introduzca 1 = Enero,
        // reducimos ese número una vez para que corresponda con el array.
        monthToSearch--;
        if (monthToSearch < 1 || monthToSearch > 12){
            throw new Exception("Error, mes introducido inválido.");
        } else{
            exactFee = this.monthlyFees[monthToSearch];
        }
        return exactFee;
    }

    public double yearLeftFee (int monthToSearch){
        monthToSearch--;
        double yearLeftTotal = 0.0;
        for (int i = monthToSearch; i < this.monthlyFees.length; i++) {
            yearLeftTotal += this.monthlyFees[i];
        }
        return yearLeftTotal;
    }

    public boolean markPayedMonth(int monthToCheck, boolean statusPayment){
        boolean modifiedMonthFeeSuccessful = false;
        monthToCheck--;
        this.payedFees[monthToCheck] = statusPayment;
        return modifiedMonthFeeSuccessful;
    }

    public String showOnlyInscribedActivities(){
        String onlyInscribedActivities = "";
        for (int i = 0; i < this.activitiesInscribed.length; i++) {
            if (this.activitiesInscribed[i] != null){
                onlyInscribedActivities += this.activitiesInscribed[i].getName() + " " + this.activitiesInscribed[i].getMonthlyPrice() + "\n";
            }
        }
        return onlyInscribedActivities;
    }

    @Override
    public String toString() {
        return "\nID socio = " + memberId +
                "\nDNI = " + dni +
                "\nEdad = " + age +
                "\nActividades inscritas = " + Arrays.toString(activitiesInscribed) +
                "\nCuotas mensuales = " + Arrays.toString(monthlyFees) +
                "\nCuotas pagadas = " + Arrays.toString(payedFees) + ".";
    }

    @Override
    public boolean equals(Object obj) {
        boolean membersAreEquals = false;
        if (!(obj instanceof Member)){
            Member memberToCheck = Member.class.cast(obj);
            if(this.memberId == memberToCheck.getMemberId() && this.dni == memberToCheck.getDni()){
                membersAreEquals = true;
            }
        }
        return membersAreEquals;
    }
}