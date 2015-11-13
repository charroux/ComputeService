package dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import model.Car;

public class CarDTO implements Serializable{
	
	String plateNumber;

	public CarDTO(Car car) {
		plateNumber = car.getPlateNumber();
	}
	
	public CarDTO(){
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Override
	public String toString() {
		return "CarDTO [plateNumber=" + plateNumber + "]";
	}

}
