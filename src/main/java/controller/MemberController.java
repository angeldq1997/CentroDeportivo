package controller;

import model.Activity;
import model.Member;

public class MemberController {

    public boolean registerMember(){
        boolean registerSuccessful = false;

        return registerSuccessful;
    }

    public Member searchMemberById(int memberId){
        Member memberToFind = new Member();

        return memberToFind;
    }

    public boolean inscribeMemberOnActivity (int memberId, int activityId){
        boolean inscribedCorrectly = false;

        return inscribedCorrectly;
    }

    public boolean unsubscribeMemberOnActivity (int memberId, int activityId){
        boolean unsubcribedSuccessfully = false;

        return unsubcribedSuccessfully;
    }

    public double calculateMonthlyFeeMember (int memberId, int month){
        double total = 0.0;

        return total;
    }

    public boolean markFeePayed (int memberId, int month){
        boolean isPayed = false;

        return isPayed;
    }
}