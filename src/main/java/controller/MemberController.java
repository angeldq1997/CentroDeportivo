package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import view.ViewConsole;

import java.util.Arrays;

public class MemberController {

    public static boolean registerMember(SportCenter sportCenter) throws Exception {
        boolean registerSuccessful = false;
        String dni = ViewConsole.askStringUser("Introduce DNI del socio/a a registrar: ");
        int age = ViewConsole.askAge();
        String name = ViewConsole.askNameMember();
        Activity[] activitiesInscribed = new Activity[10];
        if (sportCenter.registerNewMember(dni, name, age, activitiesInscribed) ){
            registerSuccessful = true;
        }
        return registerSuccessful;
    }

    public static String listMembers(SportCenter sportCenter){
        String listMembers = "";
        for (int i = 0; i < sportCenter.getMembers().length; i++) {
            listMembers += sportCenter.getMembers()[i];
        }
        return listMembers;
    }

    public double actualFee(SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        return sportCenter.findMemberById(memberId).actualFee();
    }

    public static double totalLeftFeeYear(SportCenter sportCenter, int actualMonth) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        return sportCenter.findMemberById(memberId).yearLeftFee(actualMonth);
    }

    public static Member searchMemberById(SportCenter sportCenter) throws Exception {
        Member memberToFind = null;
        int memberId = ViewConsole.askIdSearchMember();
        if(sportCenter.findMemberById(memberId) != null){
            memberToFind = sportCenter.findMemberById(memberId);
        }
        return memberToFind;
    }

    public static boolean subscribeMemberOnActivity(SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        int activityId = ViewConsole.askIdSearchActivity();
        return sportCenter.subscribeMemberOnFoundActivity(memberId, activityId);
    }

    public static boolean unsubscribeMemberOnActivity(SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        int activityId = ViewConsole.askIdSearchActivity();
        return sportCenter.findMemberById(memberId).unsubscribeActivity(activityId);
    }

    public static double calculateMonthlyFeeMember(SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        return sportCenter.findMemberById(memberId).actualFee();
    }

    public static double feeExactMonth(SportCenter sportCenter) throws Exception {
        int memberId = ViewConsole.askIdSearchMember();
        int monthToSearch = ViewConsole.askMonth();
        return sportCenter.findMemberById(memberId).feeOfExactMonth(monthToSearch);
    }

    public static boolean markFeePayed(SportCenter sportCenter) throws Exception {
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