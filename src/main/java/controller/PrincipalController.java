package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import utils.Utils;
import view.ConsoleView;

import java.util.Calendar;

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

    /**
     * Función que se ejecuta al comenzar el programa, ya que no tenemos todavía persistencia de datos inicia actividades, socios y crea un Centro Deportivo.
     * @param SIZE_ACTIVITY_ARRAY Entero que fija el tamaño del array de actividades en el que los socios puedes estar inscritos y el de actividades del Centro Deportivo.
     * @param SIZE_MEMBER_ARRAY Entero en el que se fija el tamaño del array de socios del Centro Deportivo.
     * @return Devuelve el Centro Deportivo ya con nombre, array de socios y de actividades.
     */
    private SportCenter startApp(int SIZE_ACTIVITY_ARRAY, int SIZE_MEMBER_ARRAY) {
        Activity[] activities = new Activity[SIZE_ACTIVITY_ARRAY];
        Member[] members = new Member[SIZE_MEMBER_ARRAY];

        double january2 = 60;
        double january = 15;
        double february = 50;
        double march = 119.99;
        double april = 119.99;
        double may = 119.99;
        double june = 60;
        double july = 169.99;
        double august = 60;
        double september = 120;
        double october = 169.99;
        double november = 169.99;
        double december = 100;
        double[] yearMember1 = {january, february, march, april, may, june, july, august, september, october, november, december};
        double[] yearMember2 = {january2, february, march, april, may, june, july, august, september, october, november, december};
        double[] yearMember3 = {january2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        boolean[] payedFeesPayed = {true, true, true, true, true, true, true, true, true, true, true, true};
        boolean[] payedFeesPending = {false, false, false, false, false, false, false, false, false, false, false, false};
        boolean[] payedFeesAlternate = {true, false, false, false, true, false, true, false, true, false, true, false};
        boolean[] payedFeesActual = {true, false, false, false, false, false, false, false, false, false, false, false};

        Activity[] temp = null;

        Member member1 = new Member("31025482T", "Ángel", 28, temp, yearMember1, payedFeesPayed);
        Member member2 = new Member("53694581P", "Pepe", 20, temp, yearMember2, payedFeesPending);
        Member member3 = new Member("12345678Z", "María", 45, temp, yearMember3, payedFeesActual);
        Member member4 = new Member("67592924J", "Josefina", 66, temp, yearMember1, payedFeesPayed);
        Member member5 = new Member("018492522Z", "Paco", 52, temp, yearMember2, payedFeesAlternate);
        Member[] membersInscribedOnActivity1 = {null, member2, member3, member4, member5, null, null, null, null, null};
        Member[] membersInscribedOnActivity2 = {member1, null, member3, member4, member5, null, null, null, null, null};
        Member[] membersInscribedOnActivity3 = {member1, member2, null, member4, member5, null, null, null, null, null};
        Member[] membersInscribedOnActivity4 = {member1, member2, member3, null, member5, null, null, null, null, null};
        Member[] membersInscribedOnActivity5 = {member1, member2, member3, member4, null, null, null, null, null, null};
        Activity activity1 = new Activity("Pilates", 30, "Iniciación", 25.5, membersInscribedOnActivity1);
        Activity activity2 = new Activity("Natación", 60, "Intermedio", 50, membersInscribedOnActivity2);
        Activity activity3 = new Activity("Karate", 30, "Avanzado", 69.99, membersInscribedOnActivity3);
        Activity activity4 = new Activity("Yoga", 30, "Iniciación", 15.0, membersInscribedOnActivity4);
        Activity activity5 = new Activity("Boxeo", 30, "Avanzado", 45.0, membersInscribedOnActivity5);
        Activity[] groupActivities1 = {activity1, activity2, activity3, activity4, activity5, null, null, null, null, null};
        Activity[] groupActivities2 = {activity1, null, activity3, activity4, activity5, null, null, null, null, null};
        Activity[] groupActivities3 = {activity1, activity2, null, activity4, activity5, null, null, null, null, null};
        Activity[] groupActivities4 = {activity1, activity2, activity3, null, activity5, null, null, null, null, null};
        Activity[] groupActivities5 = {activity1, activity2, activity3, activity4, null, null, null, null, null, null};

        member1.setActivitiesInscribed(groupActivities1);
        member2.setActivitiesInscribed(groupActivities2);
        member3.setActivitiesInscribed(groupActivities3);
        member4.setActivitiesInscribed(groupActivities4);
        member5.setActivitiesInscribed(groupActivities5);

        registerActivityOnStart(activity1, activities);
        registerActivityOnStart(activity2, activities);
        registerActivityOnStart(activity3, activities);
        registerActivityOnStart(activity4, activities);
        registerActivityOnStart(activity5, activities);
        registerMemberOnStart(member1, members);
        registerMemberOnStart(member2, members);
        registerMemberOnStart(member3, members);
        registerMemberOnStart(member4, members);
        registerMemberOnStart(member5, members);
        String nameSportCenter = ConsoleView.askNameSportCenter();
        return new SportCenter(nameSportCenter, members, activities);
    }

    /**
     * Función que muestra el menú principal y contiene las opciones en las que se divide.
     */
    public void showPrincipalMenu() {
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
                        //SUBMENÚ DE SOCIOS
                        memberMenu();
                        break;
                    case 2:
                        //SUBMENÚ DE ACTIVIDADES
                        activityMenu();
                        break;
                    case 3:
                        //SUBMENÚ DE INSCRIPCIONES
                        inscriptionsMenu();
                        break;
                    case 4:
                        //SUBMENÚ DE CUOTAS
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

    /**
     * Función que muestra el submenú de socios, contiene las opciones posibles en las que se divide y llama a los métodos correspondientes para la acción elegida.
     * @throws Exception Lanza excepción cuando no hay socios en el centro deportivo o cuando el socio a buscar con un ID concreto no se encuentra.
     */
    private void memberMenu() throws Exception {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showMemberMenuAndReadOption(0, 3);
            switch (option) {
                case 0:
                    //SALIR DEL SUBMENÚ SOCIOS
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    //LISTA DE SOCIOS DEL CENTRO DEPORTIVO
                    ConsoleView.showMessage(centerController.listMembers());
                    break;
                case 2:
                    //MUESTRA EN PANTALLA UN SOCIO SELECCIONADO
                    ConsoleView.showMessage(centerController.searchMemberById().toString());
                    break;
                case 3:
                    //ACTUALIZAR DATOS DE SOCIO
                    memController.updateMemberData(centerController.findMemberById(ConsoleView.askIdSearchMember()));
                    break;
                default:
                    ConsoleView.showError("ha introducido un número de opción incorrecto.");
            }
        } while (stayOnMenu);
    }

    /**
     * Función que muestra el submenú de actividades, contiene las opciones posibles en las que se divide y llama a los métodos correspondientes para la acción elegida.
     * @throws Exception Lanza excepción cuando no se halla el ID de una actividad concreta / o de un socio/a, también cuando el ID introducido para borrar una actividad no aparece en el array de estas.
     */
    private void activityMenu() throws Exception {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showActivityMenuAndReadOption(0, 4);
            switch (option) {
                case 0:
                    //SALIR DEL SUBMENÚ ACTIVIDADES
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de socios al menú principal.");
                    break;
                case 1:
                    //Listado de actividades del centro deportivo
                    ConsoleView.showMessage(centerController.listActivities());
                    break;
                case 2:
                    //Listado de socios de una actividad concreta
                    ConsoleView.showMessage(centerController.listMembersOfActivity());
                    break;
                case 3:
                    //Muestra en pantalla detalles de una actividad concreta
                    ConsoleView.showMessage(centerController.searchActivityById().toString());
                    break;
                case 4:
                    //Elimina una actividad
                    if (centerController.removeActivity()) {
                        ConsoleView.showMessage("Actividad eliminada satisfactoriamente.");
                    }
                    break;
                default:
                    ConsoleView.showError("ha introducido un número de opción incorrecto.");
            }
        } while (stayOnMenu);
    }

    /**
     * Función que muestra el submenú de inscripciones, contiene las opciones posibles en las que se divide y llama a los métodos correspondientes para la acción elegida.
     * @throws Exception Lanza excepción si no se ha podido anular la suscripción de una actividad o socio de esta, también cuando falla al suscribir a un socio/a.
     */
    private void inscriptionsMenu() throws Exception {
        boolean stayOnMenu = true;
        do {
            int option = ConsoleView.showInscriptionsMenuAndReadOption(0, 4);
            switch (option) {
                case 0:
                    //SALIR DEL SUBMENÚ DE INSCRIPCIONES
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    //REGISTRAR UN SOCIO NUEVO
                    if (registerNewMember()) {
                        ConsoleView.showMessage("Socio registrado correctamente.");
                    }
                    break;
                case 2:
                    //REGISTRAR UNA ACTIVIDAD NUEVA
                    int registeredActivity = registerActivity();
                    Activity activityCreated = this.actualSportCenter.getActivities()[registeredActivity];
                    if (registeredActivity != -1) {
                        ConsoleView.showMessage("Actividad " + activityCreated.getName() + " con ID: " + activityCreated.getActivityId() + " registrada correctamente.");
                    }else{
                        ConsoleView.showError("No existe la actividad con este ID.");
                    }
                    break;
                case 3:
                    //REGISTRAR UN SOCIO EN ACTIVIDAD CONCRETA
                    if (centerController.subscribeMemberOnFoundActivity()) {
                        ConsoleView.showMessage("Socio suscrito a actividad correctamente.");
                    }
                    break;
                case 4:
                    //ANULAR SUSCRIPCIÓN DEL SOCIO A LA ACTIVIDAD Y VICEVERSA
                    if( unsubscribeMemberFromActivityAndReverse() ){
                        ConsoleView.showMessage("Se ha realizado correctamente la petición de anulación de suscripción.");
                    }
                    break;
                default:
                    ConsoleView.showError ( "debe introducir un valor entre 0 y 4.");
            }
        } while (stayOnMenu);
    }

    /**
     * Función que muestra el submenú de cuotas, contiene las opciones posibles en las que se divide y llama a los métodos correspondientes para la acción elegida.
     * @throws Exception Lanza excepción cuando no es posible actualizar el pago una cuota debido a que ya se pagó.
     */
    private void feeMenu() throws Exception {
        boolean stayOnMenu = true;
        int memberId = ConsoleView.askIdSearchMember();
        String status = "";
        memController.updateActualMember(centerController.findMemberById(memberId));
        do {
            int option = ConsoleView.showFeeMenuAndReadOption(0, 4);
            switch (option) {
                case 0:
                    //SALIR DEL SUBMENÚ DE CUOTAS
                    stayOnMenu = false;
                    ConsoleView.showMessage("Ha seleccionado salir del menú de inscripciones al menú principal.");
                    break;
                case 1:
                    //MUESTRA EN PANTALLA LA CUOTA ACTUAL (Con Calendar . MONTH toma el mes del sistema)
                    if(memController.getActualMember().getPayedFees()[Calendar.MONTH]){
                        status = "pagada. ⭕";
                    }else{
                        status = "no pagada. ❌";
                    }
                    ConsoleView.showMessage("La cuota mensual es: " + memController.actualFee(Calendar.MONTH) + " se encuentra " + status);
                    break;
                case 2:
                    //MARCA COMO PAGADA UNA CUOTA CONCRETA
                    if (memController.markPayedMonth()) {
                        ConsoleView.showMessage("El mes ha sido marcado como pagado de forma satisfactoria.");
                    } else {
                        ConsoleView.showError("el mes está marcado como no pagado.");
                    }
                    break;
                case 3:
                    //MUESTRA POR PANTALLA EL IMPORTE PENDIENTE DEL AÑO
                    ConsoleView.showMessage("El importe restante es: " + memController.yearLeftFee());
                    break;
                case 4:
                    //MUESTRA POR PANTALLA LA CUOTA DE UN MES CONCRETO
                     ConsoleView.showMessage ( memController.feeOfExactMonth() );
                    break;
                default:
                    ConsoleView.showError("debe introducir un valor entre 0 y 4.");
            }
        } while (stayOnMenu);
    }

    /**
     * Función que registra una actividad ya creada al comienzo de la ejecución de la app por la falta de persistencia de datos.
     * @param activityToPutOnArray Actividad a colocar en el array de actividades.
     * @param activities Array de actividades por el cual se trabaja.
     */
    private void registerActivityOnStart(Activity activityToPutOnArray, Activity[] activities) {
        boolean registerSuccessful = false;
        for (int i = 0; i < activities.length && !registerSuccessful; i++) {
            if (activities[i] == null) {
                activities[i] = activityToPutOnArray;
                registerSuccessful = true;
            }
        }
    }

    /**
     * Función que registra un socio/a ya creado/a al comienzo de la ejecución de la app por la falta de persistencia de datos.
     * @param memberToPutOnArray Socio a colocar en el array de socios.
     * @param members Array de socios que contiene el centro deportivo, en este se registra el socio concreto.
     */
    private void registerMemberOnStart(Member memberToPutOnArray, Member[] members) {
        boolean registerSuccessful = false;
        for (int i = 0; i < members.length && !registerSuccessful; i++) {
            if (members[i] == null) {
                members[i] = memberToPutOnArray;
                registerSuccessful = true;
            }
        }
    }

    /**
     * Función que anula el registro de un socio en una actividad y viceversa.
     * @return Devuelve True si ambos funcionan y False si cualquiera de ellos fallase.
     * @throws Exception Lanza excepción si no se encuentra el socio/ la actividad o se introducen ID no válidas.
     */
    public boolean unsubscribeMemberFromActivityAndReverse() throws Exception {
        boolean perfectUnsubscription = true;
        int memberId = ConsoleView.askIdSearchMember();
        memController.updateActualMember(centerController.findMemberById(memberId));
        if (memController.unsubscribeActivityOnMember()) {
            ConsoleView.showMessage("Se ha eliminado el registro de la actividad en el socio.");
        } else {
            ConsoleView.showError("no se ha podido anular la suscripción de la actividad al socio/a.");
            perfectUnsubscription = false;
        }
        if (actController.unsubscribeMemberToActivity   ( memController.getActualMember(), centerController.findActivityById(ConsoleView.askIdSearchActivity())) ) {
            ConsoleView.showMessage("Se ha eliminado el registro del socio en la actividad.");
        } else {
            ConsoleView.showError("no se ha podido anular el socio de la actividad.");
            perfectUnsubscription = false;
        }
        return perfectUnsubscription;
    }

    /**
     * ---NO SE HA USADO PORQUE YA SE AÑADIÓ LA OPCIÓN DENTRO DE LA FUNCIÓN PARA REGISTRAR UN SOCIO A UNA ACTIVIDAD---
     * Función que registra una actividad con un socio/a en concreto.
     * @param activityToAdd Actividad para inscribir el socio en ella.
     * @param memberToRegister Socio que se registrará en la actividad.
     * @throws Exception Lanza excepción cuando no se puede inscribir porque ya está completo o ya se halla en él.
     */
    private void registerActivityWithMember(Activity activityToAdd, Member memberToRegister) throws Exception {
        this.actController.subscribeMemberToActivity(memberToRegister);
    }

    /**
     * Función para registrar a un socio/a nuevo/a en el centro deportivo.
     * @return Devuelve True cuando se ha registrado exitosamente y False cuando no ha sido posible.
     * @throws Exception Lanza excepción cuando se escribe un DNI que ya está en el sistema (no se verifica el ID porque se autoincrementa).
     */
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

    /**
     * Función que permite registrar una actividad nueva en el centro deportivo.
     * @return Devuelve True cuando se ha podido realizar sin problema y False cuando no es el caso.
     * @throws Exception Lanza excepción cuando ya está el registro completo o cuando se introduce de forma incorrecta un nivel de intensidad.
     */
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