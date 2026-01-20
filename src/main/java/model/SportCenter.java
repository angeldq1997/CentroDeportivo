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
        if(existsMemberWithDni(dni)){
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

    public boolean existsMemberWithId(int id){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < this.members.length && !isAlreadyRegistered; i++) {
            if(this.members[i] != null && (this.members[i].getMemberId() == id) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public boolean existsMemberWithDni (String dni){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < this.members.length && !isAlreadyRegistered; i++) {
            if(this.members[i] != null && (this.members[i].getDni() == dni) ){
                    isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public Member findMemberById(int id) throws Exception {
        boolean isMemberFound = false;
        Member memberFound = null;
        if (id < 0){
            throw new Exception("Error, la ID introducida no es válida.");
        }
        for (int i = 0; i < this.members.length && !isMemberFound; i++) {
            if(this.members[i] != null && (this.members[i].getMemberId() == id) ){
                memberFound = this.members[i];
                isMemberFound = true;
            }
        }
        if (memberFound == null){
            throw new Exception("Error, no se ha encontrado el socio con la ID introducida.");
        }
        return memberFound;
    }

    public int findMemberPositionById (int id){
        boolean isMemberFound = false;
        int positionMemberFound = -1;
        for (int i = 0; i < this.members.length && !isMemberFound; i++) {
            if(this.members[i] != null && (this.members[i].getMemberId() == id) ){
                positionMemberFound = i;
                isMemberFound = true;
            }
        }
        return positionMemberFound;
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