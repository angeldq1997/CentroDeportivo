package controller;

import model.Member;
import model.SportCenter;
import view.ViewConsole;

public class MemberController {

    public boolean registerMember(SportCenter sportCenter) throws Exception {
        boolean registerSuccessful = false;
        String dni = ViewConsole.askStringUser("Introduce DNI del socio/a a registrar: ");
        int age = ViewConsole.askAge();
        if (sportCenter.registerNewMember(dni, age)){
            registerSuccessful = true;
        }
        return registerSuccessful;
    }

    public Member searchMemberById(SportCenter sportCenter) throws Exception {
        Member memberToFind = null;
        int memberId = ViewConsole.askIdSearchMember();
        if(sportCenter.findMemberById(memberId) != null){
            memberToFind = sportCenter.findMemberById(memberId);
        }
        return memberToFind;
    }

    public boolean unsubscribeMemberOnActivity (SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        int activityId = ViewConsole.askIdSearchActivity();
        return sportCenter.findMemberById(memberId).unsubscribeActivity(activityId);
    }

    public double calculateMonthlyFeeMember (SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        return sportCenter.findMemberById(memberId).actualFee();
    }

    public boolean markFeePayed (SportCenter sportCenter) throws Exception {
        boolean isPayed = false;
        int memberId = ViewConsole.askIdSearchMember();
        int month = ViewConsole.askMonth();
        sportCenter.findMemberById(memberId).modifyPayedMonth(month, true);
        isPayed = true;
        return isPayed;
    }

    public void getActivitiesMember (SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        sportCenter.findMemberById(memberId).getActivitiesInscribed();
    }
}