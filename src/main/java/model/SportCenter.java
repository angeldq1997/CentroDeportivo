package model;

import utils.Utils;

public class SportCenter {
    private String name;
    private Member[] members;
    private Activity[] activities;

    public SportCenter(String name, Member[] members, Activity[] activities) {
        this.name = name;
        this.members = members;
        this.activities = activities;
    }

    public Member[] getMembers() {
        return members;
    }

    public Activity[] getActivities() {
        return activities;
    }

    public String getName (){
        return this.name;
    }

    public int getNumberMembers(){
        return Utils.countArrayFilled(this.members);
    }

    public int getNumberActivities(){
        return Utils.countArrayFilled(this.activities);
    }

    @Override
    public String toString() {
        return "Centro Deportivo " + this.name +
                "\nSocios: " + this.members +
                "\nActividades: " + this.activities +
                "\nNúmero de socios: " + getNumberMembers() +
                "\nNúmero de actividades" + getNumberActivities();
    }
}