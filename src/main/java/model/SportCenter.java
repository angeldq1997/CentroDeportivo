package model;

import utils.Utils;

import java.util.Arrays;

public class SportCenter {
    private String name;
    private Member[] members;
    private Activity[] activities;
    private int numberMembers;
    private int numberActivities;

    public SportCenter(String name, Member[] members, Activity[] activities) {
        this.name = name;
        this.members = members;
        this.activities = activities;
        this.numberMembers = getNumberMembers();
        this.numberActivities = getNumberActivities();
    }

    public Member[] getMembers() {
        return this.members;
    }

    public Activity[] getActivities() {
        return this.activities;
    }

    private int getNumberMembers(){
        int numberMembers = 0;
        numberMembers = Utils.countArrayFilled(this.members);
        return numberMembers;
    }

    private int getNumberActivities(){
        int numberActivities = 0;
        numberActivities = Utils.countArrayFilled(this.activities);
        return numberActivities;
    }

    @Override
    public String toString() {
        return "Centro Deportivo " + this.name +
                "\nSocios: " + Arrays.toString(this.members) +
                "\nActividades: " + Arrays.toString(this.activities) +
                "\nNúmero de socios: " + getNumberMembers() +
                "\nNúmero de actividades" + getNumberActivities();
    }
}