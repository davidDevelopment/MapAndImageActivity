package com.daviddev.mise_en_oeuvre_recherche_balise;

public class DataHolder {

    CGeocache[] geocaches;
    CCourse parcours;

    DataHolder(){

        geocaches = new CGeocache[2];

        int id = 100;
        int numAnswer = 0;
        String question = "Combien de filiéres générales il y as à Saint Aubin La Salle?";

        String[] propositions = new String[4];
        propositions[0] = "blanc";
        propositions[1] = "rouge";
        propositions[2] = "vert";
        propositions[3] = "orange";

        String[] indices = new String[2];
        indices[0] = "il n'est pas rouge";
        indices[1] = "il n'est pas vert";

        geocaches[0] = new CGeocache(id, numAnswer, question, propositions, indices);

        id = 200;
        numAnswer = 2;
        question = "Combien de filières générales il y as à Saint Aubin La Salle?";

        propositions[0] = "Une";
        propositions[1] = "Deux";
        propositions[2] = "Trois";
        propositions[3] = "Aucune";

        indices[0] = "Au moin une";
        indices[1] = "Plus de deux";

        geocaches[1] = new CGeocache(id, numAnswer, question, propositions, indices);

        parcours = new CCourse(geocaches);

    }

    public CCourse getParcours(){
        return parcours;
    }


}
