package messageBroker;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class MainAsynchronousQueueReceiver {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(messageBroker.MainAsynchronousQueueReceiver.class);
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			QueueConnection connection = factory.createQueueConnection() ;
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) applicationContext.getBean("queue");
			QueueReceiver receiver = session.createReceiver(queue);
			
			MongoOperations mongoOperation = (MongoOperations)applicationContext.getBean("mongoTemplate");
			
			receiver.setMessageListener( new RentMessageListener(mongoOperation) ) ;
			connection.start();
			
		}catch(Exception e){
			logger.log(Level.ERROR, e.getLocalizedMessage(), e);
		}
		


	}

}
