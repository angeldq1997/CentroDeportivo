package controller;

import model.Activity;
import model.Member;
import utils.Utils;

public class ActivityController {
    private Activity actualActivity;

    public Activity activityCreated(String activityName, int minuteDuration, String level, double monthlyPrice,int SIZE_MEMBERS_INSCRIBED){
        return new Activity(activityName, minuteDuration, level, monthlyPrice, SIZE_MEMBERS_INSCRIBED);
    }

    public int countMembersFilled (){
        int total = 0;
        if(this.actualActivity.getMembersInscribed() != null){
            for (int i = 0; i < this.actualActivity.getMembersInscribed().length; i++) {
                if (this.actualActivity.getMembersInscribed()[i] != null || this.actualActivity.getMembersInscribed()[i].getMemberId() == -1) {
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
        for (int i = 0; i < this.actualActivity.getMembersInscribed().length; i++) {
            if(this.actualActivity.getMembersInscribed()[i] != null && this.actualActivity.getMembersInscribed()[i].equals(memberToSearch)){
                isAlreadyInscribed = true;
            }
        }
        return isAlreadyInscribed;
    }

    public boolean subscribeMemberToActivity (Member memberToSubscribe) throws Exception {
        boolean subscribedSuccessful = false;
        if( !this.memberIsAlreadyInscribed(memberToSubscribe) ){
            if(Utils.countArrayFilled(this.actualActivity.getMembersInscribed()) == this.actualActivity.getMembersInscribed().length){
                throw new Exception("Error, no puede inscribirse el socio, la actividad está completa.");
            }else{
                for (int i = 0; i < this.actualActivity.getMembersInscribed().length && !subscribedSuccessful; i++) {
                    if (this.actualActivity.getMembersInscribed()[i] == null){
                        this.actualActivity.getMembersInscribed()[i] = memberToSubscribe;
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
        if(this.memberIsAlreadyInscribed(memberToSearch)){
            for (int i = 0; i < this.actualActivity.getMembersInscribed().length && !unsubscribeMemberSuccessful; i++) {
                if(this.actualActivity.getMembersInscribed()[i] == memberToSearch){
                    this.actualActivity.getMembersInscribed()[i] = null;
                    unsubscribeMemberSuccessful = true;
                }
            }
        }else{
            throw new Exception("Error, el socio no está inscrito, no se le puede dar de baja.");
        }
        return unsubscribeMemberSuccessful;
    }
}