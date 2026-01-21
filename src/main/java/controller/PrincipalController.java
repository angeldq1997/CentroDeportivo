package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import view.ViewConsole;

public class PrincipalController {

    public static SportCenter startApp (int SIZE_ACTIVITY_ARRAY, int SIZE_MEMBER_ARRAY){
        Activity[] activities = new Activity[SIZE_ACTIVITY_ARRAY];
        Member[] members = new Member[SIZE_MEMBER_ARRAY];
        Member member1 = new Member("31025482T", "Ángel", 28, activities);
        Member member2 = new Member("53694581P", "Pepe", 20, activities);
        Member member3 = new Member("12345678Z", "María", 45, activities);
        Member[] membersInscribedOnActivity1 = new Member[SIZE_MEMBER_ARRAY];
        Member[] membersInscribedOnActivity2 = new Member[SIZE_MEMBER_ARRAY];
        Activity activity1 = new Activity("Pilates", 30, "Iniciación", 20.5, membersInscribedOnActivity1);
        Activity activity2 = new Activity("Natación", 60, "Intermedio", 42.3, membersInscribedOnActivity1);
        Activity activity3 = new Activity("Karate", 30, "Avanzado", 66.6, membersInscribedOnActivity2);
        Activity activity4 = new Activity("Pilates", 30, "Iniciación", 15.0, membersInscribedOnActivity2);
        registerActivityOnStart(activity1, activities);
        registerActivityOnStart(activity2, activities);
        registerActivityOnStart(activity3, activities);
        registerActivityOnStart(activity4, activities);
        registerMemberOnStart(member1, members);
        registerMemberOnStart(member2, members);
        registerMemberOnStart(member3, members);
        try {
            registerActivityWithMember(activity1, member1);
            registerActivityWithMember(activity1, member2);
            registerActivityWithMember(activity1, member3);
            registerActivityWithMember(activity2, member1);
            registerActivityWithMember(activity2, member2);
            registerActivityWithMember(activity3, member3);
            registerActivityWithMember(activity4, member3);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String nameSportCenter = ViewConsole.askNameSportCenter();
        return new SportCenter(nameSportCenter, members, activities);
    }

    private static boolean registerActivityOnStart (Activity activityToPutOnArray, Activity[] activities) {
        boolean registerSuccessful = false;
        for (int i = 0; i < activities.length && !registerSuccessful; i++) {
            if (activities[i] == null) {
                activities[i] = activityToPutOnArray;
                registerSuccessful = true;
            }
        }
        return registerSuccessful;
    }

    private static boolean registerMemberOnStart (Member memberToPutOnArray, Member[] members) {
        boolean registerSuccessful = false;
        for (int i = 0; i < members.length && !registerSuccessful; i++) {
            if (members[i] == null) {
                members[i] = memberToPutOnArray;
                registerSuccessful = true;
            }
        }
        return registerSuccessful;
    }

    public static boolean registerActivityWithMember (Activity activityToAdd, Member memberToRegister) throws Exception {
        boolean activityRegisteredSuccessfully = false;
        activityToAdd.subscribeMemberToActivity(memberToRegister);
        activityRegisteredSuccessfully = true;
        return activityRegisteredSuccessfully;
    }
}