package dataInterfaces;

public interface IColumn {

    /**
     * Recupere la valeur de cette colonne dans la donnee en parametre,
     * puis normalise cette valeur )entre 0 et 1) et la retourne normalisee.
     * Si la colonne n'est pas normalisable, le comportement n'est pas
     * definit.
     */

    IValueNormalizer getNormalizer();

    /**
     * stocke le <i>normaliseur</i> en parametre dans la colonne.
     */
    void setNormalizer(IValueNormalizer valueNormalizer);

    double getNormalizedValue(IPoint point);

    public String getNormalizerType();

    /**
     * "Denormalise" une valeur entre 0 et 1 en une "vraie" valeur pour
     * cette colonne.
     * Si la colonne n'est pas normalisable, le comportement n'est pas
     * definit.
     */
    Object getDenormalizedValue(double value);

    /**
     * Retourne le nom de la colonne.
     */
    String getName();

    /**
     * Retourne le DataSet auquel cette colonne appartient.
     */
    IDataSet getDataset();

    /**
     * Indique si cette colonne est normalisable (a un <i>normaliseur</i>).
     */
    boolean isNormalizable();

}
