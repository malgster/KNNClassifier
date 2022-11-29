package titanic;


import both.ClassColor;
import com.opencsv.bean.CsvBindByName;
import dataInterfaces.IColumn;
import dataInterfaces.IPoint;

/**
 * a titanic passenger
 */
public class TitanicRawData implements IPoint {
    //attributes from the csv
    @CsvBindByName(column = "PassengerId")
    protected int passengerId;
    @CsvBindByName(column = "Survived")
    protected boolean survived;
    @CsvBindByName(column = "Pclass")
    protected int passengerClass;
    @CsvBindByName(column = "Name")
    protected String name;
    @CsvBindByName(column = "Sex")
    protected Gender gen;
    @CsvBindByName(column = "Age")
    protected double age;
    @CsvBindByName(column = "SibSp")
    protected int sibSp;
    @CsvBindByName(column = "Parch")
    protected int parch;
    @CsvBindByName(column = "Ticket")
    protected String ticket;
    @CsvBindByName(column = "Cabin")
    protected String cabin;
    @CsvBindByName(column = "Fare")
    protected double fare;
    @CsvBindByName(column = "Embarked")
    protected Embarked embarked;
    protected ClassColor survivedColor = ClassColor.NULL;

    public TitanicRawData() {
    }

    public TitanicRawData(int passengerId, boolean survived, int passengerClass, String name, Gender gen, double age, int sibSp, int parch, String ticket, String cabin, double fare, Embarked embarked) {
        this.passengerId = passengerId;
        this.survived = survived;
        this.passengerClass = passengerClass;
        this.name = name;
        this.gen = gen;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.cabin = cabin;
        this.fare = fare;
        this.embarked = embarked;
    }

    /**
     * gets the value of the point for a column given as a parameter
     * @param col
     * @return point's value for the given attribute
     */
    @Override
    public Object getValue(IColumn col) {
        switch (col.getName().toLowerCase()) {
            case "passengerid":
                return this.passengerId;
            case "survived":
                return this.survived;
            case "passengerclass":
                return this.passengerClass;
            case "name":
                return this.name;
            case "gen":
                return (this.gen != null) ? this.gen : Gender.OTHER;
            case "age":
                return this.age;
            case "sibsp":
                return this.sibSp;
            case "parch":
                return this.parch;
            case "ticket":
                return this.ticket;
            case "cabin":
                return this.cabin;
            case "fare":
                return this.fare;
            case "embarked":
                return (this.embarked != null) ? this.embarked : Embarked.NULL;

            case "color":
                return this.survivedColor;
            default:
                return null;
        }

    }

    /**
     * gets a point's normalized value for the column given in parameters
     * @param xcol
     * @return normalized value
     */
    @Override
    public double getNormalizedValue(IColumn xcol) {
        return xcol.getNormalizedValue(this);
    }

    @Override
    public void setColor() {
        int ordinal = (this.survived) ? 1 : 0;
        this.survivedColor = ClassColor.values()[ordinal];
    }

    @Override
    public ClassColor getColor() {
        return survivedColor;
    }

    /**
     * set the color given the class value
     */
    @Override
    public void setColor(ClassColor color) {
        this.survivedColor = color;
    }

    /**
     * sets the class value given the point's color
     */
    @Override
    public void setClassFromColor() {
        survived = (survivedColor == ClassColor.BLUE);
    }

    @Override
    public String toString() {
        return passengerId + ", " + survived + ", " + passengerClass + ", " + name + ", " + gen + ", " + age + ", " + sibSp + ", "
                + parch + ", " + ticket + ", " + cabin + ", " + fare + ", " + embarked;
    }

}
