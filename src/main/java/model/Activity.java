package model;

import java.util.Arrays;

public class Activity {
    private int activityId;
    private String name;
    private int minuteDuration;
    private String level;
    private Member[] membersInscribed;
    private static int nextActivityId;

    public Activity (String name, int minuteDuration, String level, Member[] membersInscribed) {
        this.activityId = nextActivityId++;
        this.name = name;
        this.minuteDuration = minuteDuration;
        this.level = level;
        this.membersInscribed = membersInscribed;
    }

    public Activity (){
        this.activityId = nextActivityId++;
        this.name = "Actividad sin título";
        this.minuteDuration = 0;
        this.level = "Sin asignar";
        this.membersInscribed = new Member[0];
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

    public Member[] getMembersInscribed() {
        return membersInscribed;
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
                "\nMiembros inscritos = " + Arrays.toString(membersInscribed) + ".";
    }
}
