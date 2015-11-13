package messageBroker;

import model.Car;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainTopicPublisher {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(messageBroker.MainTopicPublisher.class);
		
		try{
			
			RentMessageTopicPublisher rentMessageTopicPublisher = new RentMessageTopicPublisher();
			
			Car car = new Car();
			car.setPlateNumber("11AA22");
			car.setRented(true);
			
			rentMessageTopicPublisher.publish(car);
			
			logger.log(Level.INFO, "Message sent: " + car);
			
		}catch(Exception e){
			logger.log(Level.ERROR, e.getLocalizedMessage(), e);
		}
		


	}

}
