package com.daviddev.mise_en_oeuvre_recherche_balise;

public class CCourse {

    CGeocache[] geocaches;

    CCourse(CGeocache[] p_géocaches){
        int i;
        geocaches = p_géocaches;
    }

    public CGeocache getGeocache(int id){

        int i;

        for(i = 0; i < geocaches.length; i++){
            if (geocaches[i].getId() == id)
                break;
        }
        return geocaches[i];
    }

    public int getGeocachesNbr(){
        return geocaches.length;
    }

}