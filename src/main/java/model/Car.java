package model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class Car implements Serializable{
	
	@Id
	String plateNumber;
	boolean rented;

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;

	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	@Override
	public String toString() {
		return "Car [plateNumber=" + plateNumber + ", rented=" + rented + "]";
	}

}
