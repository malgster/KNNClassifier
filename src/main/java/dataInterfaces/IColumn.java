package dataInterfaces;

public interface IColumn {

    /**
     * stocke le <i>normaliseur</i> en parametre dans la colonne.
     */
    public void setNormalizer(IValueNormalizer valueNormalizer);
    /**
     * Recupere la valeur de cette colonne dans la donnee en parametre,
     * puis normalise cette valeur )entre 0 et 1) et la retourne normalisee.
     * Si la colonne n'est pas normalisable, le comportement n'est pas
     * definit.
     */
    
    public IValueNormalizer getNormalizer();
    
    public double getNormalizedValue(IPoint point);
    /**
     * "Denormalise" une valeur entre 0 et 1 en une "vraie" valeur pour
     * cette colonne.
     * Si la colonne n'est pas normalisable, le comportement n'est pas
     * definit.
     */
    public Object getDenormalizedValue(double value);
    /**
     * Retourne le nom de la colonne.
     */
    public String getName();
    /**
     * Retourne le DataSet auquel cette colonne appartient.
     */
    public IDataSet getDataset();
    /**
     * Indique si cette colonne est normalisable (a un <i>normaliseur</i>).
     */
    public boolean isNormalizable();

}
