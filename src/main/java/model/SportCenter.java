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
        return this.members;
    }

    public Activity[] getActivities() {
        return this.activities;
    }

    public int getNumberMembers(){
        return Utils.countArrayFilled(this.members);
    }

    public int getNumberActivities(){
        return Utils.countArrayFilled(this.activities);
    }

    public boolean registerNewMember(String dni, int age) throws Exception {
        boolean registerSuccessful = false;
        if(memberIsAlreadyRegistered(dni)){
            throw new Exception("Error, el DNI seleccionado ya está registrado.");
        } else{
            for (int i = 0; i < this.members.length && !registerSuccessful; i++) {
                if(this.members[i] == null){
                    this.members[i] = new Member(dni, age);
                    registerSuccessful = true;
                }
            }
        }
        return registerSuccessful;
    }

    public boolean memberIsAlreadyRegistered(String dni){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < this.members.length; i++) {
            if(this.members[i] != null && (this.members[i].getDni() == dni) ){
                    isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
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