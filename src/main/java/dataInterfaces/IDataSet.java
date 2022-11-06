package dataInterfaces;

import java.util.List;

public interface IDataSet extends Iterable<IPoint> {

    /**
            * Le nom du DataSet ex: Titanic, Iris, Pokemon, ...
            */
    public String getTitle();
    /**
     * Nombre de lignes (ou donnees ou points) dans le DataSet
     */
    public int getNbLines();
    /**
     * stocke dans le DataSet la collection de donnees passee en parametre
     */
    public void setLines(List<IPoint> lines);
    /**
     * Ajoute une donnee dans le DataSet
     */
    void addLine(IPoint element);
    /**
     * Ajoute une collection de donnees dans le DataSet
     */
    void addAllLine(List<IPoint> element);

}
