package messageBroker;

import model.Car;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainQueueSender {

public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(messageBroker.MainQueueSender.class);
		
		try{
			
			RentMessageQueueSender rentMessageSender = new RentMessageQueueSender();
			
			Car car = new Car();
			car.setPlateNumber("11AA22");
			car.setRented(true);
			
			rentMessageSender.send(car);
			
			logger.log(Level.INFO, "Message sent: " + car);
			
		}catch(Exception e){
			logger.log(Level.ERROR, e.getLocalizedMessage(), e);
		}

	}

}
