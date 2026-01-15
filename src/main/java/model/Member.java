package model;

import java.util.Arrays;

public class Member {
    private int memberId;
    private String dni;
    private int  age;
    private Activity[] activitiesInscribed;
    private double[] monthlyFees;
    private boolean[] payedFees;
    private static int nextMember;

    //Constructor registrar miembros nuevos
    public Member(String dni, int age, Activity[] activitiesInscribed, double[] monthlyFees, boolean[] payedFees) {
        this.memberId = nextMember++;
        this.dni = dni;
        this.age = age;
        this.activitiesInscribed = activitiesInscribed;
        this.monthlyFees = monthlyFees;
        this.payedFees = payedFees;
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
        return activitiesInscribed;
    }

    public void setActivitiesInscribed(Activity[] activitiesInscribed) {
        this.activitiesInscribed = activitiesInscribed;
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

    public boolean unsubscribeActivity(int activityId){
        boolean unsubscribedSuccessful = false;
        if( activityId > -1){
            if(this.findActivity(activityId) != null){
                this.activitiesInscribed[this.findExactActivityPosition(activityId)] = null;
                unsubscribedSuccessful = true;
            }
        }
        return unsubscribedSuccessful;
    }


    @Override
    public String toString() {
        return "DATOS DEL SOCIO " +
                "\nID socio = " + memberId +
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