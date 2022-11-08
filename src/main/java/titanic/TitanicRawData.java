package titanic;


import both.DataSet;
import com.opencsv.bean.CsvBindByName;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;

public class TitanicRawData  implements IPoint {
	@CsvBindByName(column = "PassengerId")
	int passengerId;
	@CsvBindByName(column = "Survived")
	boolean survived;
	public int getPassengerId() {
		return passengerId;
	}

	public boolean isSurvived() {
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
		switch(col.getName()) {
		case "passengerId":
			return this.passengerId;
		case "survived":
			return this.survived;
		case "passengerClass":
			return this.passengerClass;
		case "name":
			return this.name;
		case "gen":
			return this.gen;
		case "age":
			return this.age;
		case "sibSp":
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
			return this.embarked;
		default:
			return null;
		}

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
}
