package com.daviddev.mise_en_oeuvre_recherche_balise;

public class CGeocache {

    int id;
    int numAnswer;
    String question;
    String[] propositions;
    String[] clues;

    ///////////TODO
    String[] images;
    long x, y; // -> struct , Array  ou tableau
    ////////

    CGeocache(int p_id, int p_num_answer, String p_question, String[] p_propositions, String[] p_clues){
        id = p_id;
        numAnswer = p_num_answer;
        question = p_question;
        propositions = p_propositions;
        clues = p_clues;
    }

    public int getId(){
        return id;
    }

    public int getPropositionsNbr(){
        return propositions.length;
    }

    public int getAnswerNbr(){
        return numAnswer;
    }

    public String getQuestion(){
        return question;
    }

    public String getProposition(int nbr){
        return propositions[nbr];
    }

    public String getClue(int nbr){
        return clues[nbr];
    }
}
