package controller;

import model.Activity;
import model.Member;
import view.ConsoleView;

public class MemberController {
    private Member actualMember;

    public MemberController(){
        actualMember = new Member();
    }

    public Member getActualMember (){
        return this.actualMember;
    }

    public void updateMemberData(Member member) throws Exception {
        if(member != null) {
            member.setDni(ConsoleView.askDniMember());
            member.setName(ConsoleView.askNameMember());
            member.setAge(ConsoleView.askAge());
            ConsoleView.showMessage("DATOS ACTUALIZADOS DEL SOCIO CON ID: " + member.getMemberId() + ".");
        }else{
            throw new Exception("El socio seleccionado no se encuentra en la base de datos.");
        }
    }

    /**
     * Función para actualizar el socio del controlador al que se va a utilizar, trabajando con métodos mediante sus datos.
     * @param member Socio del centro deportivo a trabajar.
     */
    public void updateActualMember(Member member){
        if(member != null && member.getMemberId() != -1) {
            actualMember = member;
        }
    }

    /**
     * Función para crear un nuevo socio/a en el sistema del centro deportivo.
     * @param dni DNI del socio/a a crear.
     * @param name Nombre del socio/a a crear.
     * @param age Edad del socio/a
     * @param activitiesInscribed Actividades en las que está inscrito el socio.
     * @return El socio con todos los datos rellenos.
     */
    public Member memberCreated(String dni, String name, int age, int activitiesInscribed){
        return new Member(dni, name, age, activitiesInscribed);
    }

    /**
     * Función para anular la suscripción a un socio de una actividad concreta.
     * @return True si se ha podido anular y false si no ha sido posible.
     * @throws Exception Lanza excepción si se introduce un ID de actividad inválida.
     */
    public boolean unsubscribeActivityOnMember() throws Exception {
        boolean unsubscribedSuccessful = false;
        int activityId = ConsoleView.askIdSearchActivity();
        if( activityId > -1){
            if(this.findActivityOnInscribed(activityId) != null){
                this.actualMember.getActivitiesInscribed()[this.findExactActivityPosition(activityId)] = null;
                unsubscribedSuccessful = true;
            }
        }else{
            throw new Exception("Error, el ID de Actividad introducida es inválida, debe ser mayor de -1.");
        }
        return unsubscribedSuccessful;
    }

    /**
     * Función para encontrar la posición de una actividad concreta (a partir de su ID) dentro de las que está suscrito.
     * @param activityId El ID de la actividad de la que se quiere conocer la posición en el array.
     * @return La posición de la actividad si la encuentra, si no devuelve -1.
     */
    public int findExactActivityPosition(int activityId){
        int foundActivity = -1;
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if(!(this.actualMember.getActivitiesInscribed()[i] == null || this.actualMember.getActivitiesInscribed()[i].getActivityId() == -1) && this.actualMember.getActivitiesInscribed()[i].getActivityId() == activityId){
                foundActivity = i;
            }
        }
        return foundActivity;
    }

    /**
     * Encuentra una actividad dentro de las que uno está suscrito y la devuelve.
     * @param activityIdToFind El ID de la actividad a encontrar dentro de las que está suscrito.
     * @return La actividad ya encontrada, si no la encuentra devuelve null.
     */
    public Activity findActivityOnInscribed(int activityIdToFind){
        Activity foundActivity = null;
        if( activityIdToFind > -1){
            for (Activity activity : actualMember.getActivitiesInscribed()) {
                if ( activity != null && activity.getActivityId() != -1 && activity.getActivityId() == activityIdToFind) {
                    foundActivity = activity;
                }
            }
        }
        return foundActivity;
    }

    /**
     * Función que realiza un cálculo con las cuotas mensuales para conocer el nuevo total mensual.
     * @param actualMonth Mes actual para realizar el cálculo.
     */
    public void recalculateMonthlyFees(int actualMonth){
        double totalMonth = 0.0;
        actualMonth--;
        for (int i = actualMonth; i < this.actualMember.getMonthlyFees().length; i++) {
            for (Activity activity : this.actualMember.getActivitiesInscribed()) {
                if(activity != null) {
                    totalMonth += activity.getMonthlyPrice();
                }
            }
            this.actualMember.getMonthlyFees()[i] = totalMonth;
        }
    }

    /**
     * Función que calcula el total de la cuota mensual y lo devuelve.
     * @return Devuelve el total sumado de las actividades suscritas.
     */
    public double actualFee () {
        double actualFee = 0.0;
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if(this.actualMember.getActivitiesInscribed()[i] !=null){
                actualFee += this.actualMember.getActivitiesInscribed()[i].getMonthlyPrice();
            }
        }
        return actualFee;
    }

    /**
     * Función que calcula la cuota anual de todas las mensualidades.
     * @return Devuelve el total pendiente de las cuotas no pagadas.
     */
    public double yearlyFee (){
        double total = 0.0;
        for (int i = 0; i < this.actualMember.getMonthlyFees().length; i++) {
            if(!this.actualMember.getPayedFees()[i]){
                total += this.actualMember.getMonthlyFees()[i];
            }
        }
        return total;
    }

    /**
     * Función que calcula la cuota de un mes concreto.
     * @return El precio de la cuota del mes seleccionado.
     * @throws Exception Lanza excepción si el mes introducido no es válido (0 y 11).
     */
    public double feeOfExactMonth () throws Exception {
        double exactFee = 0.0;
        //En el array queremos buscar un mes que han introducido, para facilitar al usuario que se introduzca 1 = enero,
        // reducimos ese número una vez para que corresponda con el array.
        int monthToSearch = ConsoleView.askMonth();
        monthToSearch--;
        if (monthToSearch < 0 || monthToSearch > 11){
            throw new Exception("Error, mes introducido inválido, debe introducir un número entre 1 y 12.");
        } else{
            for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
                if(this.actualMember.getActivitiesInscribed()[i] != null) {
                    exactFee += this.actualMember.getActivitiesInscribed()[i].getMonthlyPrice();
                }
            }
        }
        return exactFee;
    }

    /**
     * Función que calcula el importe pendiente anual del precio de las actividades.
     * @return El total del importe anual (descontando los pagados).
     */
    public double yearLeftFee (){
        double yearLeftTotal = 0.0;
        int monthToSearch = ConsoleView.askMonth();
        for (int i = monthToSearch; i < this.actualMember.getMonthlyFees().length; i++) {
            if(!this.actualMember.getPayedFees()[i]) {
                yearLeftTotal += this.actualMember.getMonthlyFees()[i];
            }
        }
        return yearLeftTotal;
    }

    /**
     * Función que marca un mes seleccionado como pagado.
     * @return True si ha podido realizarse la operación y false si no ha sido posible.
     * @throws Exception Lanza excepción si ya se había pagado el mes.
     */
    public boolean markPayedMonth() throws Exception {
        int monthToCheck = ConsoleView.askMonth();
        monthToCheck--;
        if(this.actualMember.getPayedFees()[monthToCheck]){
            throw new Exception("Error, el mes seleccionado ya había sido pagado.");
        }else {
            this.actualMember.getPayedFees()[monthToCheck] = true;
        }
        return this.actualMember.getPayedFees()[monthToCheck];
    }

    /**
     * Función que muestra solo las actividades en las que uno está inscrito.
     * @return Una cadena de texto con los nombres y los precios de las actividades suscritas.
     */
    public String showOnlyInscribedActivities(){
        String onlyInscribedActivities = "";
        for (Activity activity : this.actualMember.getActivitiesInscribed()) {
            if (activity != null) {
                onlyInscribedActivities += activity.getName() + " " + activity.getMonthlyPrice() + "\n";
            }
        }
        return onlyInscribedActivities;
    }

    /**
     * Función que devuelve una cadena de texto con las cuotas pagadas y no pagadas.
     * @return SI NO HAY ninguna pagada devuelve una cadena comentándolo, si no devuelve por orden las cuotas pagadas y no pagadas con el número del mes.
     */
    public String getPayedFeesOnString() {
        String listOfFees = "";
        for (int i = 0; i < this.actualMember.getPayedFees().length; i++) {
            if(!this.actualMember.getPayedFees()[i]){
                listOfFees = (i+1) + " NO PAGADO" + " | ";
            }else{
                listOfFees = (i+1) + " PAGADO" + " | ";
            }
        }
        return listOfFees;
    }

    /**
     * Función que recoge las actividades que no sean nulas a las que está inscrito un socio/a.
     * @return Una cadena con las actividades no nulas a las que está inscrito un socio/a.
     */
    public String getNotNullActivitiesInscribed() {
        String onlyNotNullActivities = "";
        for (int i = 0; i < this.actualMember.getActivitiesInscribed().length; i++) {
            if (this.actualMember.getActivitiesInscribed()[i] != null) {
                onlyNotNullActivities = String.valueOf(this.actualMember.getActivitiesInscribed()[i]);
            }
        }
        return onlyNotNullActivities;
    }

    /**
     * Función que junta en una cadena de texto las cuotas mensuales.
     * @return La cadena de texto con todas las cuotas ya agrupadas.
     */
    public String getMonthlyFeesOnString() {
        String feesCollected = "";
        for (int i = 0; i < this.actualMember.getMonthlyFees().length; i++) {
            if(this.actualMember.getMonthlyFees()[i] != 0.0){
                feesCollected = (i+1) + this.actualMember.getMonthlyFees()[i] + " | ";
            }
        }
        return feesCollected;
    }
}