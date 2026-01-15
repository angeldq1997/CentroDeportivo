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
        this.memberId = nextMember++;
        this.dni = "00000000A";
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