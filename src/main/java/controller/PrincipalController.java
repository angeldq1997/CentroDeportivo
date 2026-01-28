package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import utils.Utils;
import view.ConsoleView;

public class PrincipalController {
    private final ActivityController actController;
    private final MemberController memController;
    private final SportCenterController centerController;
    private final SportCenter actualSportCenter;

    public PrincipalController(int SIZE_ACTIVITY_ARRAY, int SIZE_MEMBER_ARRAY) {
        this.actController = new ActivityController();
        this.memController = new MemberController();
        this.actualSportCenter = startApp(SIZE_ACTIVITY_ARRAY, SIZE_MEMBER_ARRAY);
        this.centerController = new SportCenterController(actualSportCenter);
    }

    private SportCenter startApp(int SIZE_ACTIVITY_ARRAY, int SIZE_MEMBER_ARRAY) {
        Activity[] activities = new Activity[SIZE_ACTIVITY_ARRAY];
        Member[] members = new Member[SIZE_MEMBER_ARRAY];
        Member member1 = new Member("31025482T", "Ángel", 28, SIZE_ACTIVITY_ARRAY);
        Member member2 = new Member("53694581P", "Pepe", 20, SIZE_ACTIVITY_ARRAY);
        Member member3 = new Member("12345678Z", "María", 45, SIZE_ACTIVITY_ARRAY);
        Member[] membersInscribedOnActivity1 = {member1, member2, member3, null, null, null, null, null, null, null};
        Member[] membersInscribedOnActivity2 = {member1, member2, member3, null, null, null, null, null, null, null};
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
        String nameSportCenter = ConsoleView.askNameSportCenter();
        return new SportCenter(nameSportCenter, members, activities);
    }

    public void showPrincipalMenu(int SIZE_MEMBERS_INSCRIBED_ON_ACTIVITY) {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showPrincipalMenuAndReadOption(actualSportCenter, 0, 4);
            try {
                switch (option) {
                    case 0:
                        stayOnMenu = false;
                        ConsoleView.showMessage("Ha seleccionado salir del programa, gracias por su tiempo.");
                        break;
                    case 1:
                        memberMenu();
                        break;
                    case 2:
                        activityMenu();
                        break;
                    case 3:
                        inscriptionsMenu();
                        break;
                    case 4:
                        feeMenu();
                        break;
                    default:
                        ConsoleView.showError("debe introducir un número entre 0 y 4.");

                }
            } catch (Exception e) {
                ConsoleView.showMessage(e.getMessage());
            }
        } while (stayOnMenu);
    }

    private void memberMenu() throws Exception {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showMemberMenuAndReadOption(0, 4);
            switch (option) {
                case 0:
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    ConsoleView.showMessage(centerController.listMembers());
                    break;
                case 2:
                    ConsoleView.showMessage(centerController.searchMemberById().toString());
                    break;
                case 3:

            }
        } while (stayOnMenu);
    }

    private void activityMenu() throws Exception {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showActivityMenuAndReadOption(0, 4);
            switch (option) {
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
                    if (centerController.removeActivity()) {
                        ConsoleView.showMessage("Actividad eliminada satisfactoriamente.");
                    }
                    break;
            }
        } while (stayOnMenu);
    }

    private void inscriptionsMenu() throws Exception {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showInscriptionsMenuAndReadOption(0, 4);
            switch (option) {
                case 0:
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    if (registerNewMember()) {
                        ConsoleView.showMessage("Socio registrado correctamente.");
                    }
                    break;
                case 2:
                    int registeredActivity = registerActivity();
                    Activity activityCreated = this.actualSportCenter.getActivities()[registeredActivity];
                    if (registeredActivity != -1) {
                        ConsoleView.showMessage("Actividad " + activityCreated.getName() + " con ID: " + activityCreated.getActivityId() + " registrada correctamente.");
                    }else{
                        ConsoleView.showError("No existe la actividad con este ID.");
                    }
                    break;
                case 3:
                    if (centerController.subscribeMemberOnFoundActivity()) {
                        ConsoleView.showMessage("Socio suscrito a actividad correctamente.");
                    }
                    break;
                case 4:
                    int memberId = ConsoleView.askIdSearchMember();
                    memController.updateActualMember(centerController.findMemberById(memberId));
                    if (memController.unsubscribeActivity()) {
                        ConsoleView.showMessage("Actividad seleccionada eliminada de la lista del socio correctamente.");
                    }
                    break;
                default:
                    ConsoleView.showError("debe introducir un valor entre 0 y 4.");
            }
        } while (stayOnMenu);
    }

    private void feeMenu() throws Exception {
        boolean stayOnMenu = true;
        int memberId = ConsoleView.askIdSearchMember();
        memController.updateActualMember(centerController.findMemberById(memberId));
        do {
            int option = ConsoleView.showFeeMenuAndReadOption(0, 4);
            switch (option) {
                case 0:
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    ConsoleView.showMessage("La cuota mensual es: " + memController.actualFee());
                    break;
                case 2:
                    if (memController.markPayedMonth()) {
                        ConsoleView.showMessage("El mes ha sido marcado como pagado de forma satisfactoria.");
                    } else {
                        ConsoleView.showError("el mes está marcado como no pagado.");
                    }
                    break;
                case 3:
                    ConsoleView.showMessage("El importe restante es: " + memController.yearLeftFee());
                    break;
                case 4:
                    ConsoleView.showMessage("La cuota del mes es: " + memController.feeOfExactMonth());
                    break;
                default:
                    ConsoleView.showError("debe introducir un valor entre 0 y 4.");
            }
        } while (stayOnMenu);
    }

    private void registerActivityOnStart(Activity activityToPutOnArray, Activity[] activities) {
        boolean registerSuccessful = false;
        for (int i = 0; i < activities.length && !registerSuccessful; i++) {
            if (activities[i] == null) {
                activities[i] = activityToPutOnArray;
                registerSuccessful = true;
            }
        }
    }

    private void registerMemberOnStart(Member memberToPutOnArray, Member[] members) {
        boolean registerSuccessful = false;
        for (int i = 0; i < members.length && !registerSuccessful; i++) {
            if (members[i] == null) {
                members[i] = memberToPutOnArray;
                registerSuccessful = true;
            }
        }
    }

    private void registerActivityWithMember(Activity activityToAdd, Member memberToRegister) throws Exception {
        this.actController.subscribeMemberToActivity(memberToRegister);
    }

    private boolean registerNewMember() throws Exception {
        boolean registerSuccessful = false;
        if (centerController.existsMemberWithDni()) {
            throw new Exception("Error, el DNI seleccionado ya está registrado.");
        } else {
            for (int i = 0; i < actualSportCenter.getMembers().length && !registerSuccessful; i++) {
                if (actualSportCenter.getMembers()[i] == null) {
                    String dni = ConsoleView.askDniMember();
                    String name = ConsoleView.askNameMember();
                    int age = ConsoleView.askAge();
                    int sizeMembersInscribed = Utils.readIntInRange(1, 40, "Introduce número máximo actividades para inscribirse: ", "Error, debe introducir un número entre 1 y 40.");
                    actualSportCenter.getMembers()[i] = memController.memberCreated(dni, name, age, sizeMembersInscribed);
                    registerSuccessful = true;
                }
            }
        }
        return registerSuccessful;
    }

    private int registerActivity() throws Exception {
        boolean activityRegisteredSuccessfully = false;
        int positionRegisteredActivity = -1;
        if (Utils.countArrayFilled(actualSportCenter.getActivities()) == actualSportCenter.getActivities().length) {
            throw new Exception("Error, no se ha podido registrar la actividad, está completo el registro.");
        } else {
            for (int i = 0; i < actualSportCenter.getActivities().length && !activityRegisteredSuccessfully; i++) {
                if (actualSportCenter.getActivities()[i] == null) {
                    String activityName = ConsoleView.askStringUser("Introduce nombre de la actividad: ");
                    int minuteDuration = Utils.readIntInRange(1, 300, "Introduce minutos de duración de la actividad: ", "Error, ha introducido un valor inválido debe estar entre 1 y 300.");
                    String level = ConsoleView.askLevelIntensityActivity();
                    double monthlyPrice = Utils.readIntInRange(1, 40, "Introduce precio mensual de la actividad: ", "Error, debe introducido un valor entre 1 y 40.");
                    int sizeMembers = Utils.readIntInRange(1, 40, "Introduce número de miembros de la actividad: ", "Error, debe introducir un número entre 1 y 40.");
                    actualSportCenter.getActivities()[i] = actController.activityCreated(activityName, minuteDuration, level, monthlyPrice, sizeMembers);
                    positionRegisteredActivity = i;
                    activityRegisteredSuccessfully = true;
                }
            }
        }
        return positionRegisteredActivity;
    }
}