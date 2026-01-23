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

    public Member(String dni, String name, int age, int sizeArrayInscribed) {
        this.memberId = nextMember++;
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.activitiesInscribed = new Activity[sizeArrayInscribed];
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
        return this.activitiesInscribed;
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