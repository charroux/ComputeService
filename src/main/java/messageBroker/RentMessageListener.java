package messageBroker;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

import dto.CarDTO;

public class RentMessageListener implements MessageListener {

	private transient Logger logger = Logger.getLogger(messageBroker.RentMessageListener.class);
	MongoOperations mongoOperation;
	
	public RentMessageListener(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	@Override
	public void onMessage(Message message) {
		try {
			
			ObjectMessage object = (ObjectMessage)message;
			CarDTO car = (CarDTO)object.getObject();
			
			logger.log(Level.INFO, "Message received: " + car);
			
			mongoOperation.save(car);

			logger.log(Level.INFO, "Car saved into mongoDB");
			
		} catch (JMSException e) {
			logger.log(Level.ERROR, e.getLocalizedMessage(), e);
		}
	}

}
