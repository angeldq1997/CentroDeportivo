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
        Member[] onlyNotNullMembers = new Member[Utils.countArrayFilled(this.membersInscribed)];
        int counter = 0;
        for (int i = 0; i < this.membersInscribed.length; i++) {
            if(this.membersInscribed[i] != null){
                onlyNotNullMembers[counter] = membersInscribed[i];
                counter++;
            }
        }
        return onlyNotNullMembers;
    }

    public void setMembersInscribed(Member[] membersInscribed) {
        this.membersInscribed = membersInscribed;
    }

    public int countMembersFilled (){
        int total = 0;
        if(this.membersInscribed != null){
            for (int i = 0; i < this.membersInscribed.length; i++) {
                if (this.membersInscribed[i] != null || this.membersInscribed[i].getMemberId() == -1) {
                    total++;
                }
            }
        }else{
            total = -1;
        }
        return total;
    }

    public boolean memberIsAlreadyInscribed(Member memberToSearch){
        boolean isAlreadyInscribed = false;
        for (int i = 0; i < this.membersInscribed.length; i++) {
            if(this.membersInscribed[i] != null && this.membersInscribed[i].equals(memberToSearch)){
                isAlreadyInscribed = true;
            }
        }
        return isAlreadyInscribed;
    }

    public boolean subscribeMemberToActivity (Member memberToSubscribe) throws Exception {
        boolean subscribedSuccessful = false;
        if( !this.memberIsAlreadyInscribed(memberToSubscribe) ){
            if(Utils.countArrayFilled(this.membersInscribed) == this.membersInscribed.length){
                throw new Exception("Error, no puede inscribirse el socio, la actividad está completa.");
            }else{
                for (int i = 0; i < this.membersInscribed.length && !subscribedSuccessful; i++) {
                    if (this.membersInscribed[i] == null){
                        this.membersInscribed[i] = memberToSubscribe;
                        subscribedSuccessful = true;
                    }
                }
            }
        }else{
            throw new Exception("Error, el socio ya está suscrito a la actividad.");
        }
        return subscribedSuccessful;
    }

    public boolean unsubscribeMemberToActivity(Member memberToSearch) throws Exception {
        boolean unsubscribeMemberSuccessful = false;
        if(!this.memberIsAlreadyInscribed(memberToSearch)){
            throw new Exception("Error, el socio no está inscrito, no se le puede dar de baja.");
        }else{
            for (int i = 0; i < this.membersInscribed.length && !unsubscribeMemberSuccessful; i++) {
                if(this.membersInscribed[i] == memberToSearch){
                    this.membersInscribed[i] = null;
                    unsubscribeMemberSuccessful = true;
                }
            }
        }
        return unsubscribeMemberSuccessful;
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