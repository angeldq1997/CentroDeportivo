package controller;

import model.Activity;
import model.Member;
import utils.Utils;

public class ActivityController {
    private Activity actualActivity;

    public Activity getActualActivity() {
        return actualActivity;
    }

    public void updateActivity(Activity activityToUpdate){
        this.actualActivity = activityToUpdate;
    }

    /**
     * Función que crea una nueva actividad a partir de todos los atributos necesarios que recibe.
     * @param activityName Nombre de la actividad a crear.
     * @param minuteDuration Minutos de duración de la actividad a crear.
     * @param level Nivel de intensidad (Iniciación/Intermedio/Avanzado) de la actividad a crear.
     * @param monthlyPrice Precio mensual de la actividad a crear.
     * @param SIZE_MEMBERS_INSCRIBED Capacidad del array de los socios que se pueden inscribir en la actividad a crear.
     * @return La actividad creada con todos los datos para trabajar con ella.
     */
    public Activity activityCreated(String activityName, int minuteDuration, String level, double monthlyPrice,int SIZE_MEMBERS_INSCRIBED){
        return new Activity(activityName, minuteDuration, level, monthlyPrice, SIZE_MEMBERS_INSCRIBED);
    }

    /**
     * Función que comprueba si un socio/a está ya inscrito en la actividad.
     * @param memberToSearch Socio/a a buscar.
     * @return True si está inscrito y False si no se haya inscrito/a en la actividad.
     */
    public boolean memberIsAlreadyInscribed(Member memberToSearch){
        boolean isAlreadyInscribed = false;
        if(this.actualActivity != null) {
            for (int i = 0; i < this.actualActivity.getMembersInscribed().length; i++) {
                if (this.actualActivity.getMembersInscribed()[i] != null && this.actualActivity.getMembersInscribed()[i].equals(memberToSearch)) {
                    isAlreadyInscribed = true;
                }
            }
        }
        return isAlreadyInscribed;
    }

    /**
     * ---NO SE ACABA UTILIZANDO---
     * Función que permite suscribir a un socio en una actividad concreta.
     * @param memberToSubscribe Socio a registrar en la actividad.
     * @throws Exception Lanza excepción cuando la actividad está completa.
     */
    public void subscribeMemberToActivity (Member memberToSubscribe) throws Exception {
        boolean subscribedSuccessful = false;
        if( !this.memberIsAlreadyInscribed(memberToSubscribe) && this.actualActivity != null){
            if(Utils.countArrayFilled(this.actualActivity.getMembersInscribed()) == this.actualActivity.getMembersInscribed().length){
                throw new Exception("Error, no puede inscribirse el socio, la actividad está completa.");
            }else if (this.actualActivity != null){
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
    }

    /**
     * Función que anula la suscripción a un socio/a de una actividad concreta.
     * @param memberToSearch Socio/a a buscar dentro de los inscritos en una actividad concreta.
     * @return Devuelve True si ha podido anular el socio/a y False si no ha sido posible.
     * @throws Exception Lanza excepción cuando no está inscrito en la actividad el socio, por tanto, no es posible darle de baja.
     */
    public boolean unsubscribeMemberToActivity(Member memberToSearch, Activity activityToUpdate) throws Exception {
        boolean unsubscribeMemberSuccessful = false;
        updateActivity(activityToUpdate);
        if(this.actualActivity == null){
            throw new Exception("Error, la actividad no existe.");
        }
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