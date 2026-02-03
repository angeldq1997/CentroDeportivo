package controller;

import model.Activity;
import model.Member;
import model.SportCenter;
import utils.Utils;
import view.ConsoleView;

public class SportCenterController {
    private SportCenter actualSportCenter;

    public SportCenterController(SportCenter actualSportCenter) {
        this.actualSportCenter = actualSportCenter;
    }

    public void setActualSportCenter(SportCenter sportCenter){
        actualSportCenter = sportCenter;
    }

    /**
     * Función que recoge los socios que no sean nulos en un array.
     * @return Devuelve el array con tan solo los socios no nulos.
     */
    public Member[] obtainMembersNotNull() {
        Member[] notNullMembers = new Member[Utils.countArrayFilled(actualSportCenter.getMembers())];
        int counter = 0;
        for (int i = 0; i < actualSportCenter.getMembers().length; i++) {
            if(actualSportCenter.getMembers()[i] != null) {
                notNullMembers[counter] = actualSportCenter.getMembers()[i];
                counter++;
            }
        }
        return notNullMembers;
    }

    /**
     * Función que recoge las actividades que no sean nulas y las reune un array.
     * @return Array de actividades que contiene tan solo las que no son nulas.
     */
    public Activity[] obtainActivitiesNotNull() {
        Activity[] onlyNotNullActivities = new Activity[Utils.countArrayFilled(actualSportCenter.getActivities())];
        int counter = 0;
        for (int i = 0; i < actualSportCenter.getActivities().length; i++) {
            if(actualSportCenter.getActivities()[i] != null){
                onlyNotNullActivities[counter] = actualSportCenter.getActivities()[i];
                counter++;
            }
        }
        return onlyNotNullActivities;
    }

    /**
     * ---NO SE USA, YA QUE HAY OTRA FUNCIÓN EN EL PRINCIPAL---
     * Función que registra a un socio en una actividad concreta.
     * @param activityToSubscribe Actividad en la que registrar al socio.
     * @param actualMember Socio a registrar
     * @return True si se realiza con éxito y False si hay algún fallo.
     */
    private boolean subscribeMemberOnActivity(Activity activityToSubscribe, Member actualMember){
        boolean subscribedSuccessful = false;
        for (int i = 0; i < actualMember.getActivitiesInscribed().length; i++) {
            if(actualMember.getActivitiesInscribed()[i] == null){
                actualMember.getActivitiesInscribed()[i] = activityToSubscribe;
                subscribedSuccessful = true;
            }
        }
        return subscribedSuccessful;
    }

    /**
     * Función que registra a un socio en una actividad concreta.
     * @return Devuelve True si ambos procedimientos se realizan sin problema, si cualquiera fallase devuelve False.
     * @throws Exception Lanza excepción si no existe un socio/actividad con este ID, si la actividad ya está llena o si el socio ya estaba inscrito.
     */
    public boolean subscribeMemberOnFoundActivity() throws Exception {
        boolean subscriptionSuccessful = false;
        boolean subscriptionSuccessful2 = false;
        int memberId = ConsoleView.askIdSearchMember();
        int activityId = ConsoleView.askIdSearchActivity();
        Member actualMember = findMemberById(memberId);
        Activity foundActivity = findActivityById(activityId);
        if(!existsMemberWithId(memberId) && !existsActivityWithId(activityId)){
            throw new Exception("Error, no existe un socio/actividad con este ID.");
        }
        if(foundActivity.getNotNullMembersInscribed().length == foundActivity.getMembersInscribed().length){
            throw new Exception("Error, la actividad ya está llena.");
        }
        if(memberIsAlreadySubscribedActivity(foundActivity, memberId)){
            throw new Exception("Error, el socio ya está inscrito en esta actividad.");
        }else {
            for (int i = 0; i < foundActivity.getMembersInscribed().length && !subscriptionSuccessful2; i++) {
                if (foundActivity.getMembersInscribed()[i] == null) {
                    foundActivity.getMembersInscribed()[i] = actualMember;
                    subscriptionSuccessful2 = true;
                }
            }
            for (int i = 0; i < actualMember.getActivitiesInscribed().length && !subscriptionSuccessful; i++) {
                if (actualMember.getActivitiesInscribed()[i] == null) {
                    actualMember.getActivitiesInscribed()[i] = findActivityById(activityId);
                    subscriptionSuccessful = true;
                }
            }
        }
        return subscriptionSuccessful && subscriptionSuccessful2;
    }

    /**
     * Función que comprueba si el socio ya está suscrito en la actividad.
     * @param activityToCheck Actividad a comprobar para ver si está el socio en ella.
     * @param memberIdToCheck Entero que contiene el ID del socio para comprobar si se halla en la actividad.
     * @return Devuelve True si se encuentra y False si no.
     */
    public boolean memberIsAlreadySubscribedActivity(Activity activityToCheck, int memberIdToCheck){
        boolean memberIsAlreadySubscribed = false;
        for (int i = 0; i < activityToCheck.getMembersInscribed().length && !memberIsAlreadySubscribed; i++) {
            if(activityToCheck.getMembersInscribed()[i] != null && activityToCheck.getMembersInscribed()[i].getMemberId() == memberIdToCheck ){
                memberIsAlreadySubscribed = true;
            }
        }
        return memberIsAlreadySubscribed;
    }

    /**
     * Función que comprueba si existe un socio con un ID concreto.
     * @param id Entero con el ID del socio a comprobar.
     * @return True si existe el socio y False si no se encuentra.
     */
    public boolean existsMemberWithId(int id){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < actualSportCenter.getMembers().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == id) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    /**
     * Función que comprueba si existe una actividad con un ID concreto.
     * @param id Entero con el ID de la actividad a buscar.
     * @return True si existe la actividad y False si no se halla.
     */
    public boolean existsActivityWithId(int id){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getActivityId() == id) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    /**
     * ---NO SE ACABA UTILIZANDO POR SI SE HICIERAN SUBDIVISIONES DE ACTIVIDAD (PILATES 1-A, PILATES 2-A,-...)---
     * Función que comprueba si existe una actividad con el nombre recibido.
     * @param name Nombre para comprobar de la actividad.
     * @return True si ha encontrado una con el nombre y False si no se da el caso.
     */
    public boolean existsActivityWithName (String name){
        boolean isAlreadyRegistered = false;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getName().equalsIgnoreCase(name)) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    /**
     * Función que comprueba si existe un socio con el DNI recibido.
     * @return True si existe el socio con un DNI recibido o False si no se encuentra.
     */
    public boolean existsMemberWithDni (){
        boolean isAlreadyRegistered = false;
        String dni = ConsoleView.askDniMember();
        for (int i = 0; i < actualSportCenter.getMembers().length && !isAlreadyRegistered; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getDni().equalsIgnoreCase(dni)) ){
                isAlreadyRegistered = true;
            }
        }
        return isAlreadyRegistered;
    }

    /**
     * Función que busca un socio a partir de su ID y lo devuelve.
     * @param memberId Número entero con el ID del socio a buscar.
     * @return True si ha encontrado el socio y False si no.
     * @throws Exception Lanza excepción cuando no se encuentra el socio con el ID introducido.
     */
    public Member findMemberById(int memberId) throws Exception {
        boolean isMemberFound = false;
        Member memberFound = null;
        for (int i = 0; i < actualSportCenter.getMembers().length && !isMemberFound; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == memberId) ){
                memberFound = actualSportCenter.getMembers()[i];
                isMemberFound = true;
                ConsoleView.showMessage("El socio seleccionado es: " + memberFound.getName() + " con DNI " + memberFound.getDni());
            }
        }
        if (memberFound == null){
            throw new Exception("Error, no se ha encontrado el socio con la ID introducida.");
        }
        return memberFound;
    }

    /**
     * Función que busca una actividad a partir de su ID.
     * @param id Número entero que contiene el ID de la actividad a buscar.
     * @return La actividad encontrada para operar con ella.
     * @throws Exception Lanza excepción si no se halla el socio con el ID introducido.
     */
    public Activity findActivityById(int id) throws Exception {
        boolean isActivityFound = false;
        Activity activityFound = null;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isActivityFound; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getActivityId() == id) ){
                activityFound = actualSportCenter.getActivities()[i];
                isActivityFound = true;
            }
        }
        if (activityFound == null){
            throw new Exception("Error, no se ha encontrado el socio con el ID introducido.");
        }
        return activityFound;
    }

    /**
     * ---NO SE USA, YA QUE NO SE HA CONSIDERADO NECESARIO---
     * Función que devuelve la posición de un socio en el array del centro deportivo, lo busca mediante su ID.
     * @param id Entero con el ID del socio a buscar.
     * @return Devuelve la posición del socio en el array del centro deportivo.
     */
    public int findMemberPositionById (int id){
        boolean isMemberFound = false;
        int positionMemberFound = -1;
        for (int i = 0; i < actualSportCenter.getMembers().length && !isMemberFound; i++) {
            if(actualSportCenter.getMembers()[i] != null && (actualSportCenter.getMembers()[i].getMemberId() == id) ){
                positionMemberFound = i;
                isMemberFound = true;
            }
        }
        return positionMemberFound;
    }

    /**
     * Función que busca la posición de una actividad en el array de actividades del centro deportivo, a partir de su ID.
     * @param id Entero con el ID de la actividad a buscar.
     * @return Devuelve la posición de la actividad con respecto al array de actividades del centro.
     * @throws Exception Lanza excepción si el ID a buscar no se encuentra en el sistema.
     */
    public int findActivityPositionById (int id) throws Exception {
        boolean isActivityFound = false;
        int positionActivityFound = -1;
        for (int i = 0; i < actualSportCenter.getActivities().length && !isActivityFound; i++) {
            if(actualSportCenter.getActivities()[i] != null && (actualSportCenter.getActivities()[i].getActivityId() == id) ){
                positionActivityFound = i;
                isActivityFound = true;
            }
        }
        if(positionActivityFound == -1) {
            throw new Exception("Error, el ID de la actividad a buscar no está en el sistema, por lo que no se puede determinar su posición.");
        }
        return positionActivityFound;
    }

    /**
     * Función que busca una actividad por ID devolviéndola para operar con ella.
     * @return Devuelve la actividad encontrada o null si no se encuentra.
     * @throws Exception Lanza excepción si el ID no existe en el sistema.
     */
    public Activity searchActivityById() throws Exception {
        Activity activityToFind = null;
        boolean activityFound = false;
        int activityId = ConsoleView.askIdSearchActivity();
        if (!existsActivityWithId(activityId)){
            throw new Exception("Error, ID introducido no existe en el sistema.");
        }else{
            for (int i = 0; i < actualSportCenter.getActivities().length && !activityFound; i++) {
                if (actualSportCenter.getActivities()[i].getActivityId() == activityId){
                    activityToFind = actualSportCenter.getActivities()[i];
                    activityFound = true;
                }
            }
        }
        return activityToFind;
    }

    /**
     * Función que busca un socio mediante su ID.
     * @return Devuelve el socio encontrado o null si no se encuentra.
     * @throws Exception Lanza excepción cuando el socio a buscar con el ID introducido no existe.
     */
    public Member searchMemberById() throws Exception {
        Member memberToFind = null;
        int memberId = ConsoleView.askIdSearchMember();
        for (int i = 0; i < this.actualSportCenter.getMembers().length; i++) {
            if(this.actualSportCenter.getMembers()[i] != null && this.actualSportCenter.getMembers()[i].getMemberId() == memberId){
                memberToFind = this.actualSportCenter.getMembers()[i];
            }
        }
        if(memberToFind == null){
            throw new Exception("El socio a buscar con el ID introducido no existe.");
        }
        return memberToFind;
    }

    /**
     * Función que agrupa en una cadena de texto una lista con los socios de una actividad concreta.
     * @return Devuelve la cadena con los socios encontrados, si no halla ninguno devuelve un comentario con ello.
     * @throws Exception Lanza excepción si no encuentra la actividad por su ID.
     */
    public String listMembersOfActivity() throws Exception {
        boolean isAnybodyFound = false;
        int activityId = ConsoleView.askIdSearchActivity();
        String listMembersOfActivity = "";
        Activity foundActivity = findActivityById(activityId);
        for (int i = 0; i < foundActivity.getMembersInscribed().length; i++) {
            if(foundActivity.getMembersInscribed()[i] != null) {
                listMembersOfActivity += foundActivity.getMembersInscribed()[i];
                isAnybodyFound = true;
            }
        }
        if (!isAnybodyFound){
            listMembersOfActivity = "No se ha encontrado ningún socio inscrito en la actividad.";
        }
        return listMembersOfActivity;
    }

    /**
     * Función que agrupa en una cadena de texto la lista de actividades del centro deportivo.
     * @return Una cadena de texto con la lista de actividades del centro deportivo.
     * @throws Exception Lanza excepción si no hay actividades en el centro deportivo.
     */
    public String listActivities() throws Exception {
        String listActivities = "";
        if(obtainActivitiesNotNull().length == 0){
            throw new Exception("Error, no hay actividades en el centro deportivo.");
        }
        for (int i = 0; i < obtainActivitiesNotNull().length; i++) {
            if(obtainActivitiesNotNull()[i] != null){
                listActivities += obtainActivitiesNotNull()[i].toString();
            }
        }
        if (listActivities.isEmpty()){
            throw new Exception("Error, no hay actividades en el centro deportivo.");
        }
        return listActivities;
    }

    /**
     * Función que agrupa una lista con los socios del Centro Deportivo en una cadena de texto.
     * @return La cadena de texto con la lista de los socios del centro deportivo.
     * @throws Exception Lanza excepción si no encuentra socios inscritos en el centro deportivo (en su array).
     */
    public String listMembers() throws Exception {
        String listMembers = "";
        if(obtainMembersNotNull() == null ) {
            throw new Exception("Error, no hay socios en el registro del centro deportivo.");
        }
        for (int i = 0; i < obtainMembersNotNull().length; i++) {
            if(obtainMembersNotNull()[i] != null){
                listMembers += obtainMembersNotNull()[i].toString();
            }
        }
        if (listMembers.isEmpty()){
            throw new Exception("Error, no hay socios en el registro del centro deportivo.");
        }
        return listMembers;
    }

    /**
     * Función que elimina una actividad del centro deportivo.
     * @return Devuelve True si se ha eliminado sin problemas y False si se produce un fallo.
     * @throws Exception Lanza excepción cuando no existe el ID de la actividad.
     */
    public boolean removeActivity() throws Exception {
        boolean activityRemoved = false;
        int activityId = ConsoleView.askIdSearchActivity();
        if(existsActivityWithId(activityId)) {
            for (int i = 0; i < this.actualSportCenter.getActivities().length && !activityRemoved; i++) {
                if (this.actualSportCenter.getActivities()[i] != null && this.actualSportCenter.getActivities()[i].getActivityId() == activityId) {
                    this.actualSportCenter.getActivities()[i] = null;
                    activityRemoved = true;
                }
            }
        }else{
            throw new Exception("Error, no se puede eliminar la actividad con el ID introducido, no se encuentra en el sistema.");
        }
        return activityRemoved;
    }
}