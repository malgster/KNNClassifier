package dataInterfaces;

import both.ClassColor;

public interface IPoint {


    /**
     * Retourne la valeur de ce point pour la colonne en parametre.
     * <p>
     * Note, on aurait pu utiliser une interface generique (parametree avec
     * un type), mais cela complique significativement d'autres parties
     * du code.
     */
    Object getValue(IColumn col);

    /**
     * Retourne la valeur de ce point normalisee pour la colonne en parametre.
     * <p>
     * La normalisation se fait avec le <i>normaliseur</i> de la colonne.
     * Si la colonne n'est pas normalisable, le comportement n'est pas defini.
     */
    double getNormalizedValue(IColumn xcol);

    void setColor();

    ClassColor getColor();

    void setColor(ClassColor color);

    void setClassFromColor();

}
