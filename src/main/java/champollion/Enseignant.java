package champollion;

import java.util.HashMap;
import java.util.HashSet;

public class Enseignant extends Personne {

    private ServicePrevu myService;
    private HashMap<UE, HashMap<TypeIntervention, Integer>> map = new HashMap<>();
    private HashSet<Intervention> myIntervention = new HashSet<>();

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
    public float heuresPrevues() {
        float heures_totales = 0f;
        throw new UnsupportedOperationException("Pas encore implémenté");
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
    public float heuresPrevuesPourUE(UE ue) {
        HashMap<TypeIntervention, Integer> Heures = map.get(ue);
        float heuresCM = Heures.get(TypeIntervention.CM) * 1.5f;
        int heuresTD = Heures.
        // TODO: Implémenter cette méthode
        throw new UnsupportedOperationException("Pas encore implémenté");
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
        }
        else{
            HashMap<TypeIntervention, Integer> Hours = new HashMap<>();
            Hours.put(TypeIntervention.CM, Hours.get(TypeIntervention.CM)+ volumeCM);
            Hours.put(TypeIntervention.TD, Hours.get(TypeIntervention.TD)+ volumeTD);
            Hours.put(TypeIntervention.TP, Hours.get(TypeIntervention.TP)+ volumeTP);
        }
    }
    
    
    public void ajouteIntervention(Intervention e) {
        myIntervention.add(e);

    }
    
    public int HeuresPlanifiees(){
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
    
    
    public boolean enSousService(){
        throw new UnsupportedOperationException("Pas encore implémenté");
    }

}
