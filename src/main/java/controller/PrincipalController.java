package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import view.ConsoleView;

public class PrincipalController {
    private static ActivityController actController;
    private static MemberController memController;
    private static SportCenterController centerController;

    private SportCenter actualSportCenter;

    public SportCenter startApp (int SIZE_ACTIVITY_ARRAY, int SIZE_MEMBER_ARRAY){
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
        String nameSportCenter = ConsoleView.askNameSportCenter();
        return new SportCenter(nameSportCenter, members, activities);
    }

    private void showPrincipalMenu (int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) {
        boolean stayOnMenu = true;
        do{
            int option = ConsoleView.showPrincipalMenuAndReadOption(actualSportCenter, 0, 4);
            switch (option){
                case 0:
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del programa, gracias por su tiempo.");
                    break;
                case 1:
                    try {
                        memberMenu();
                    } catch (Exception e) {
                        ConsoleView.showMessage(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        activityMenu();
                    } catch (Exception e) {
                        ConsoleView.showMessage(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        inscriptionsMenu();
                    } catch (Exception e) {
                        ConsoleView.showMessage(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        feeMenu();
                    } catch (Exception e) {
                        ConsoleView.showMessage(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Debe introducir un número entre 0 y 4.");
            }
        }while(stayOnMenu);
    }

    public void memberMenu () throws Exception {
        boolean stayOnMenu  = true;
        do{
            int option = ConsoleView.showMemberMenuAndReadOption(0, 4);
            switch (option){
                case 0:
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    ConsoleView.showMessage(actualSportCenter.getMembers().toString());
                    break;
                case 2:
                    ConsoleView.showMessage(centerController.searchMemberById().toString());
                    break;
                case 3:

            }
        }while(stayOnMenu);
    }

    public void activityMenu() throws Exception {
        boolean stayOnMenu  = true;
        do{
            int option = ConsoleView.showActivityMenuAndReadOption(0, 4);
            switch (option){
                case 0:
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    ConsoleView.showMessage(centerController.listActivities());
                    break;
                case 2:
                    ConsoleView.showMessage(centerController.listMembersOfActivity());
                    break;
                case 3:
                    ConsoleView.showMessage(centerController.searchActivityById().toString());
                    break;
                case 4:
                    if(centerController.removeActivity()){
                        System.out.println("Actividad eliminada satisfactoriamente.");
                    }else{
                        System.out.println("No se ha podido eliminar la actividad.");
                    }
                    break;
            }
        }while(stayOnMenu);
    }

    public void inscriptionsMenu(SportCenter sportCenter, int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) throws Exception {
        boolean stayOnMenu  = true;
        do{
            int option = ConsoleView.showInscriptionsMenuAndReadOption(0, 4);
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    if ( centerController.registerNewMember() ){
                        System.out.println("Socio registrado correctamente.");
                    }
                    break;
                case 2:
                    if ( centerController.registerActivity(sportCenter, SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) ){
                        System.out.println("Actividad registrada correctamente.");
                    }
                    break;
                case 3:
                    if ( MemberController.(sportCenter) ){
                    System.out.println("Socio registrado en actividad correctamente.");
                }
                break;
                case 4:
                    if ( memController.unsubscribeActivity() ){
                        System.out.println("Socio registrado en actividad correctamente.");
                    }
                    break;
                default:
                    System.out.println("Error, debe introducir un valor entre 0 y 4.");
            }
        }while(stayOnMenu);
    }

    public static void feeMenu(SportCenter sportCenter) throws Exception {
        boolean stayOnMenu  = true;
        do{
            int option = ConsoleView.showFeeMenuAndReadOption(0, 4);
            switch (option){
                case 0:
                    stayOnMenu = false;
                    System.out.println("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    System.out.println("La cuota mensual es: " + memController.calculateMonthlyFeeMember(sportCenter) );
                    break;
                case 2:
                    memController.markFeePayed(sportCenter);
                    break;
                case 3:
                    System.out.println("El importe restante es: " + MemberController.totalLeftFeeYear(sportCenter, askMonth()) );
                    break;
                case 4:
                    System.out.println("La cuota del mes es: " + MemberController.feeExactMonth(sportCenter) );
                    break;
                default:
                    System.out.println("Error, debe introducir un valor entre 0 y 4.");
            }
        }while(stayOnMenu);
    }
}

    private boolean registerActivityOnStart (Activity activityToPutOnArray, Activity[] activities) {
        boolean registerSuccessful = false;
        for (int i = 0; i < activities.length && !registerSuccessful; i++) {
            if (activities[i] == null) {
                activities[i] = activityToPutOnArray;
                registerSuccessful = true;
            }
        }
        return registerSuccessful;
    }

    private boolean registerMemberOnStart (Member memberToPutOnArray, Member[] members) {
        boolean registerSuccessful = false;
        for (int i = 0; i < members.length && !registerSuccessful; i++) {
            if (members[i] == null) {
                members[i] = memberToPutOnArray;
                registerSuccessful = true;
            }
        }
        return registerSuccessful;
    }

    public boolean registerActivityWithMember (Activity activityToAdd, Member memberToRegister) throws Exception {
        boolean activityRegisteredSuccessfully = false;

        this.actController.subscribeMemberToActivity(memberToRegister);
        activityRegisteredSuccessfully = true;
        return activityRegisteredSuccessfully;
    }
}