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
        Activity[] onlyNotNullActivities = new Activity[Utils.countArrayFilled(this.activities)];
        int counter = 0;
        for (int i = 0; i < this.activities.length; i++) {
            if(this.activities[i] != null){
                onlyNotNullActivities[counter] = activities[i];
                counter++;
            }
        }
        return onlyNotNullActivities;
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

    public boolean registerNewActivity(String name, int minuteDuration, String level, double monthlyPrice,int SIZE_MEMBERS_INSCRIBED) throws Exception {
        boolean registerSuccessful = false;
        if(existsActivityWithName(name)){
            throw new Exception("Error, ya existe una actividad con este nombre.");
        }else {
            for (int i = 0; i < this.activities.length && !registerSuccessful; i++) {
                if (this.activities[i] == null) {
                    this.activities[i] = new Activity(name, minuteDuration, level, monthlyPrice, SIZE_MEMBERS_INSCRIBED);
                    registerSuccessful = true;
                }
            }
        }
        return registerSuccessful;
    }

    public boolean registerNewMember(String dni, String name, int age, Activity[] activitiesInscribed) throws Exception {
        boolean registerSuccessful = false;
        if(existsMemberWithDni(dni)){
            throw new Exception("Error, el DNI seleccionado ya está registrado.");
        } else{
            for (int i = 0; i < this.members.length && !registerSuccessful; i++) {
                if(this.members[i] == null){
                    this.members[i] = new Member(dni, name, age, activitiesInscribed);
                    registerSuccessful = true;
                }
            }
        }
        return registerSuccessful;
    }

    public boolean subscribeMemberOnFoundActivity(int memberId, int activityId) throws Exception {
        boolean subscriptionSuccessful = false;
        if(!existsMemberWithId(memberId)){
            throw new Exception("Error, no existe un socio con esta ID.");
        } else if (!existsActivityWithId(activityId)) {
            throw new Exception("Error, no existe una actividad con esta ID.");
        } else{
            this.findMemberById(memberId).subscribeMemberOnActivity(this.findActivityById(activityId));
            subscriptionSuccessful = true;
        }
        return subscriptionSuccessful;
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

    public boolean existsActivityWithId(int id){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < this.activities.length && !isAlreadyRegistered; i++) {
            if(this.activities[i] != null && (this.activities[i].getActivityId() == id) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    public boolean existsActivityWithName (String name){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < this.activities.length && !isAlreadyRegistered; i++) {
            if(this.activities[i] != null && (this.activities[i].getName().equalsIgnoreCase(name)) ){
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

    public Activity findActivityById(int id) throws Exception {
        boolean isActivityFound = false;
        Activity activityFound = null;
        if (id < 0){
            throw new Exception("Error, la ID introducida no es válida.");
        }
        for (int i = 0; i < this.activities.length && !isActivityFound; i++) {
            if(this.activities[i] != null && (this.activities[i].getActivityId() == id) ){
                activityFound = this.activities[i];
                isActivityFound = true;
            }
        }
        if (activityFound == null){
            throw new Exception("Error, no se ha encontrado el socio con la ID introducida.");
        }
        return activityFound;
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

    public int findActivityPositionById (int id) throws Exception {
        boolean isActivityFound = false;
        int positionActivityFound = -1;
        for (int i = 0; i < this.activities.length && !isActivityFound; i++) {
            if(this.activities[i] != null && (this.activities[i].getActivityId() == id) ){
                positionActivityFound = i;
                isActivityFound = true;
            }
        }
        if(positionActivityFound == -1) {
            throw new Exception("Error, el ID de la actividad a buscar no está en el sistema, por lo que no se puede determinar su posición.");
        }
        return positionActivityFound;
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