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
        return this.memberId;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Activity[] getActivitiesInscribed() {
        return this.activitiesInscribed;
    }

    public void setActivitiesInscribed (Activity[] activities){
        this.activitiesInscribed = activities;
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

    /**
     * Función que recoge la lista de las actividades en las que está inscrito un socio.
     * @return Devuelve la lista completa de las actividades excluyendo las nulas, si no hubiese devuelve una cadena corta comentandolo.
     */
    private String listActivitiesInscribed(){
        String listActivities = "Actividades inscritas:\n";
        for (int i = 0; i < this.getActivitiesInscribed().length; i++) {
            if(this.getActivitiesInscribed()[i] != null){
                listActivities += this.getActivitiesInscribed()[i].toString() + "\n";
            }
        }
        if(listActivities.trim().equals("Actividades inscritas:")){
            listActivities = "No hay actividades inscritas ahora mismo.";
        }
        return listActivities;
    }

    /**
     * Función que recoge la cuota de los meses del año y los agrupa.
     * @return La lista con cada cuota mensual, si no hubiese devuelve que no hay cuotas para este año.
     */
    private String listMonthlyFees(){
        String listMonthlyFees = "";
        for (int i = 0; i < this.getMonthlyFees().length; i++) {
            if(this.getMonthlyFees()[i] != 0.0){
                listMonthlyFees += (i+1) + " " + this.getMonthlyFees()[i] + "\n";
            }
        }
        if(listMonthlyFees.trim().isEmpty()){
            listMonthlyFees = "No hay cuotas para este año ahora mismo.";
        }
        return listMonthlyFees;
    }

    /**
     * Función que recoge en una lista las cuotas pendientes y pagadas por orden.
     * @return La lista de las cuotas del año si todas están pagadas lo comenta igual que si no hubiese ninguna pagada.
     */
    private String listPayedFees(){
        boolean allPayedFees = true;
        boolean nothingPayed = true;
        String listPayedFees = "CUOTAS DE ESTE AÑO: \n";
        for (int i = 0; i < this.getPayedFees().length; i++) {
            if(!this.getPayedFees()[i] && this.getMonthlyFees()[i] != 0.0){
                listPayedFees += "X Importe PENDIENTE de pagar para el mes (" + (i+1) + ")\n";
                allPayedFees = false;
            }else{
                listPayedFees += "O Importe PAGADO para el mes (" + (i+1) + ")\n";
                nothingPayed = false;
            }
        }
        if(allPayedFees){
            listPayedFees = "TODAS LOS PAGOS DEL MES ESTÁN PAGADOS ACTUALMENTE";
        } else if (nothingPayed){
            listPayedFees = "NINGUNA CUOTA ESTÁ PAGADA DE ESTE AÑO";
        }
        return listPayedFees;
    }

    @Override
    public String toString() {
        return  "\nID socio = " + this.memberId +
                "\nDNI = " + this.dni +
                "\nEdad = " + this.age +
                "\nNombre = " + this.name +
                "\nActividades inscritas = " + listActivitiesInscribed() +
                "\nCuotas mensuales = " + listMonthlyFees() +
                "\nCuotas pagadas = " + listPayedFees() + ".\n";
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