package model;

import utils.Utils;

import java.util.Arrays;

public class Activity {
    private int activityId;
    private String name;
    private int minuteDuration;
    private String level;
    private double monthlyPrice;
    private Member[] membersInscribed;
    private static int nextActivityId;

    public Activity (String name, int minuteDuration, String level, double monthlyPrice,Member[] membersInscribed) {
        this.activityId = nextActivityId++;
        this.name = name;
        this.minuteDuration = minuteDuration;
        this.level = level;
        this.monthlyPrice = monthlyPrice;
        this.membersInscribed = membersInscribed;
    }

    public Activity (String name, int minuteDuration, String level, double monthlyPrice,int SIZE_MEMBERS_INSCRIBED) {
        this.activityId = nextActivityId++;
        this.name = name;
        this.minuteDuration = minuteDuration;
        this.level = level;
        this.monthlyPrice = monthlyPrice;
        this.membersInscribed = new Member[SIZE_MEMBERS_INSCRIBED];
    }

    public Activity (){
        this.activityId = -1;
        this.name = "Actividad sin título";
        this.minuteDuration = 0;
        this.level = "Sin asignar";
        this.monthlyPrice = 0.0;
        this.membersInscribed = new Member[0];
        nextActivityId++;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinuteDuration() {
        return minuteDuration;
    }

    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public Member[] getMembersInscribed() {
        return membersInscribed;
    }

    public Member[] getNotNullMembersInscribed() {
        Member[] onlyNotNullMembers = new Member[Utils.countArrayFilled(this.membersInscribed)];
        int counter = 0;
        for (int i = 0; i < this.membersInscribed.length; i++) {
            if(this.membersInscribed[i] != null){
                onlyNotNullMembers[counter] = this.membersInscribed[i];
                counter++;
            }
        }
        return onlyNotNullMembers;
    }

    public void setMembersInscribed(Member[] membersInscribed) {
        this.membersInscribed = membersInscribed;
    }

    @Override
    public String toString() {
        return  name + ":" +
                "\nID Actividad = " + activityId +
                "\nDuración (minutos) = " + minuteDuration +
                "\nNivel de intensidad = " + level +
                "\nMiembros inscritos = " + getMembersInscribed() + ".";
    }
}