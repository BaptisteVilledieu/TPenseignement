package champollion;

import static java.lang.Math.round;
import java.util.HashMap;
import java.util.HashSet;

public class Enseignant extends Personne {

    private final ServicePrevu myService = new ServicePrevu(0,0,0);
    private final HashMap<UE, HashMap<TypeIntervention, Integer>> map = new HashMap<>();
    private final HashSet<Intervention> myIntervention = new HashSet<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
        
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        int heures_totales = 0;
        for(UE mapKey : map.keySet()){
            heures_totales += heuresPrevuesPourUE(mapKey);   
        }
        return heures_totales;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        HashMap<TypeIntervention, Integer> Heures = map.get(ue);
        float heuresCM = Heures.get(TypeIntervention.CM) * 1.5f;
        int heuresTD = Heures.get(TypeIntervention.TD);
        float heuresTP =Heures.get(TypeIntervention.TP) * 0.75f ;
        int heuretotale = round (heuresCM + heuresTD + heuresTP);
        return heuretotale;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        myService.setVolumeCM(myService.getVolumeCM()+volumeCM);
        myService.setVolumeTD(myService.getVolumeTD()+volumeTD);
        myService.setVolumeTP(myService.getVolumeTP()+volumeTP);
        if(map.get(ue)==null){
            HashMap<TypeIntervention, Integer> Hours = new HashMap<>();
            Hours.put(TypeIntervention.CM, volumeCM);
            Hours.put(TypeIntervention.TD, volumeTD);
            Hours.put(TypeIntervention.TP, volumeTP);
            map.put(ue, Hours);
        }
        else{
            HashMap<TypeIntervention, Integer> Hours = map.get(ue);
            Hours.put(TypeIntervention.CM, Hours.get(TypeIntervention.CM)+ volumeCM);
            Hours.put(TypeIntervention.TD, Hours.get(TypeIntervention.TD)+ volumeTD);
            Hours.put(TypeIntervention.TP, Hours.get(TypeIntervention.TP)+ volumeTP);
            map.put(ue, Hours);
        }
    }
    
    
    public void ajouteIntervention(Intervention e) {
        myIntervention.add(e);

    }
    
    public int HeuresPlanifiees(){
        int heuresPlan = 0;
        for(Intervention intervention : myIntervention){
            heuresPlan += intervention.getDuree();
        }
        return heuresPlan;
    }
    
    
    public boolean enSousService(){
        if(heuresPrevues()<HeuresPlanifiees()){
            return true; 
        }
        else{
            return false;
        }
    }

}
