package titanic;


import com.opencsv.bean.CsvBindByName;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;

public class TitanicRawData implements IPoint {

    @CsvBindByName(column = "PassengerId")
    int passengerId;
	@CsvBindByName(column = "Survived")
    boolean survived;
	@CsvBindByName(column = "Pclass")
    int passengerClass;
	@CsvBindByName(column = "Name")
    String name;
	@CsvBindByName(column = "Sex")
    Gender gen;
	@CsvBindByName(column = "Age")
    double age;
	@CsvBindByName(column = "SibSp")
    int sibSp;
	@CsvBindByName(column = "Parch")
    int parch;
	@CsvBindByName(column = "Ticket")
    String ticket;
	@CsvBindByName(column = "Cabin")
    String cabin;
	@CsvBindByName(column = "Fare")
	double fare;
	@CsvBindByName(column = "Embarked")
    char embarked;
	
	@Override
	public Object getValue(IColumn col) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getNormalizedValue(IColumn xcol) {
		return xcol.getNormalizedValue(this);
	}

	@Override
	public String toString() {
		return "TitanicRawData [passengerId=" + passengerId + ", survived=" + survived + ", passengerClass="
				+ passengerClass + ", name=" + name + ", gen=" + gen + ", age=" + age + ", sibSp=" + sibSp + ", parch="
				+ parch + ", ticket=" + ticket + ", cabin=" + cabin + ", fare=" + fare + ", embarked=" + embarked + "]";
	}
	public int getPassengerId() {
		return passengerId;
	}
	public boolean hasSurvived() {
		return survived;
	}
	public int getPassengerClass() {
		return passengerClass;
	}
	public String getName() {
		return name;
	}
	public Gender getGen() {
		return gen;
	}
	public double getAge() {
		return age;
	}
	public int getSibSp() {
		return sibSp;
	}
	public int getParch() {
		return parch;
	}
	public String getTicket() {
		return ticket;
	}
	public String getCabin() {
		return cabin;
	}
	public double getFare() {
		return fare;
	}
	public char getEmbarked() {
		return embarked;
	}
}